package poly.store.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import poly.store.common.Constants;
import poly.store.entity.Product;
import poly.store.model.BestSellerModel;
import poly.store.model.CartModel;
import poly.store.model.ShowProduct;
import poly.store.service.CategoryService;
import poly.store.service.CommentService;
import poly.store.service.OrderService;
import poly.store.service.ProductService;
import poly.store.service.SessionService;
import poly.store.service.impl.ShoppingCartServiceImpl;

@Controller
public class DetailProductController {
	@Autowired
	ProductService productService; // Dịch vụ quản lý sản phẩm

	@Autowired
	CategoryService categoryService; // Dịch vụ quản lý danh mục

	@Autowired
	SessionService sessionService; // Dịch vụ quản lý phiên

	@Autowired
	CommentService commentService; // Dịch vụ quản lý bình luận

	@Autowired
	ShoppingCartServiceImpl cartService; // Dịch vụ quản lý giỏ hàng

	@Autowired
	OrderService orderService; // Dịch vụ quản lý đơn hàng

	// Hiển thị thông tin chi tiết sản phẩm
	@GetMapping("/san-pham/{nameSearch}")
	public String index(@PathVariable("nameSearch") String nameSearch, Model model) {
		productService.updateView(nameSearch); // Cập nhật lượt xem của sản phẩm
		model.addAttribute("infor", false); // Truyền thông tin biến infor vào model
		sessionService.set("sessionProduct", cartService); // Lưu giỏ hàng vào phiên
		return Constants.USER_DISPLAY_DETAIL_PRODUCT; // Trả về trang hiển thị chi tiết sản phẩm
	}

	@SuppressWarnings("static-access")
	@PostMapping("/san-pham/{nameSearch}")
	public String orderProduct(@PathVariable("nameSearch") String nameSearch, Model model, HttpServletRequest req) {
		Product product = productService.getProductByNameSearch(nameSearch); // Lấy thông tin sản phẩm theo tên tìm kiếm

		Map<Integer, CartModel> map = cartService.map; // Lấy danh sách sản phẩm trong giỏ hàng
		CartModel cartModel = map.get(product.getId()); // Lấy sản phẩm trong giỏ hàng dựa trên ID

		if (cartModel == null) { // Nếu sản phẩm chưa có trong giỏ hàng
			cartModel = new CartModel(); // Tạo mới đối tượng CartModel
			cartModel.setId(product.getId()); // Đặt ID cho đối tượng CartModel
			cartModel.setProduct(product); // Đặt thông tin sản phẩm cho đối tượng CartModel
			cartModel.setQuality(1); // Đặt số lượng mua là 1 cho đối tượng CartModel
			cartService.add(product.getId(), cartModel); // Thêm sản phẩm vào giỏ hàng
		} else { // Nếu sản phẩm đã có trong giỏ hàng
			cartService.update(cartModel.getId(), cartModel.getQuality() + 1); // Cập nhật số lượng mua của sản phẩm trong giỏ hàng
		}

		model.addAttribute("infor", true); // Truyền thông tin biến infor vào model
		sessionService.set("sessionProduct", cartService); // Lưu giỏ hàng vào phiên

		return Constants.USER_DISPLAY_DETAIL_PRODUCT; // Trả về trang hiển thị chi tiết sản phẩm
	}

	// Truyền thông tin sản phẩm vào model
	@ModelAttribute("inforProduct")
	public Product inforCategory(@PathVariable("nameSearch") String nameSearch) {
		Product product = productService.getProductByNameSearch(nameSearch); // Lấy thông tin sản phẩm theo tên tìm kiếm
		return product; // Trả về thông tin sản phẩm
	}

	// Truyền danh sách sản phẩm liên quan vào model
	@ModelAttribute("listProductRelated")
	public List<ShowProduct> listProductRelated(@PathVariable("nameSearch") String nameSearch) {
		Product product = productService.getProductByNameSearch(nameSearch); // Lấy thông tin sản phẩm theo tên tìm kiếm
		List<Product> list = productService.getListProductRelated(product.getCategory().getId()); // Lấy danh sách sản phẩm liên quan

		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		for (Product item : list) { // Duyệt qua từng sản phẩm trong danh sách
			ShowProduct showProduct = new ShowProduct(); // Tạo đối tượng ShowProduct để chứa thông tin sản phẩm và số sao đánh giá
			int totalStar = commentService.getAllStarCommentByProductNameSearch(item.getNamesearch()); // Lấy tổng số sao đánh giá của sản phẩm
			showProduct.setProduct(item); // Đặt thông tin sản phẩm cho đối tượng ShowProduct
			showProduct.setTotalStar(totalStar); // Đặt số sao đánh giá cho đối tượng ShowProduct
			listProduct.add(showProduct); // Thêm đối tượng ShowProduct vào danh sách sản phẩm
		}

		return listProduct; // Trả về danh sách sản phẩm liên quan
	}

	// Truyền số lượng bình luận của sản phẩm vào model
	@ModelAttribute("countComment")
	public int countComment(@PathVariable("nameSearch") String nameSearch) {
		int result = commentService.getCountCommentByProductNameSearch(nameSearch); // Đếm số lượng bình luận của sản phẩm
		return result; // Trả về số lượng bình luận
	}

	// Truyền tổng số sao đánh giá của sản phẩm vào model
	@ModelAttribute("totalStar")
	public int totalStar(@PathVariable("nameSearch") String nameSearch) {
		int result = commentService.getAllStarCommentByProductNameSearch(nameSearch); // Lấy tổng số sao đánh giá của sản phẩm
		return result; // Trả về tổng số sao đánh giá
	}

	// Truyền danh sách 4 sản phẩm bán chạy nhất vào model
	@ModelAttribute("listBestSeller")
	public List<ShowProduct> getListBestSeller(Model model) {
		Pageable topFour = PageRequest.of(0, 4); // Lấy 4 sản phẩm đầu tiên
		List<BestSellerModel> list = orderService.getListBestSellerProduct(topFour); // Lấy danh sách 4 sản phẩm bán chạy nhất

		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		for (BestSellerModel bestSeller : list) { // Duyệt qua từng sản phẩm trong danh sách
			ShowProduct showProduct = new ShowProduct(); // Tạo đối tượng ShowProduct để chứa thông tin sản phẩm và số sao đánh giá
			int totalStar = commentService.getAllStarCommentByProductNameSearch(bestSeller.getProduct().getNamesearch()); // Lấy tổng số sao đánh giá của sản phẩm
			showProduct.setProduct(bestSeller.getProduct()); // Đặt thông tin sản phẩm cho đối tượng ShowProduct
			showProduct.setTotalStar(totalStar); // Đặt số sao đánh giá cho đối tượng ShowProduct
			listProduct.add(showProduct); // Thêm đối tượng ShowProduct vào danh sách sản phẩm
		}

		return listProduct; // Trả về danh sách sản phẩm bán chạy nhất
	}
}
