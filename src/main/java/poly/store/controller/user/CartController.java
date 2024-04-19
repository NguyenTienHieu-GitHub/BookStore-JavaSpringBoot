package poly.store.controller.user;


import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;

import poly.store.common.Constants;
import poly.store.entity.Discount;
import poly.store.entity.Product;
import poly.store.model.AlertModel;
import poly.store.model.BestSellerModel;
import poly.store.model.CartModel;
import poly.store.model.ProductModel;
import poly.store.model.ShowProduct;
import poly.store.service.CommentService;
import poly.store.service.DiscountService;
import poly.store.service.OrderService;
import poly.store.service.ParamService;
import poly.store.service.ProductService;
import poly.store.service.SessionService;
import poly.store.service.impl.ShoppingCartServiceImpl;

@Controller
public class CartController {
	@Autowired
	ShoppingCartServiceImpl cartService; // Dịch vụ quản lý giỏ hàng

	@Autowired
	DiscountService discountService; // Dịch vụ quản lý mã giảm giá

	@Autowired
	SessionService sessionService; // Dịch vụ quản lý phiên

	@Autowired
	ParamService paramService; // Dịch vụ quản lý tham số

	@Autowired
	OrderService orderService; // Dịch vụ quản lý đơn hàng

	@Autowired
	CommentService commentService; // Dịch vụ quản lý bình luận

	@Autowired
	ProductService productService; // Dịch vụ quản lý sản phẩm

	// Hiển thị trang giỏ hàng
	@GetMapping("/shop/cart")
	public String index(Model model) {
		model.addAttribute("showDiscount", false); // Không hiển thị thông tin về mã giảm giá

		cartService.clearDiscount(); // Xóa mã giảm giá khỏi giỏ hàng
		model.addAttribute("cart", cartService); // Truyền thông tin giỏ hàng vào model

		AlertModel alertModel = new AlertModel(); // Tạo đối tượng AlertModel
		model.addAttribute("alertModel", alertModel); // Truyền thông tin về alert vào model

		return Constants.USER_DISPLAY_SHOPPING_CART; // Trả về trang giỏ hàng
	}

	// Xử lý cập nhật số lượng sản phẩm trong giỏ hàng
	@PostMapping("/cart/update/{id}")
	public String update(@PathVariable("id") Integer id, HttpServletRequest req) {
		String qty = req.getParameter("quantity"); // Lấy số lượng sản phẩm từ request
		ProductModel product = productService.getOneProductById(id); // Lấy thông tin sản phẩm

		if (Integer.parseInt(qty) <= product.getQuality()) { // Nếu số lượng mới không vượt quá số lượng tồn kho
			cartService.update(id, Integer.parseInt(qty)); // Cập nhật số lượng sản phẩm trong giỏ hàng
		}

		return "redirect:/shop/cart"; // Chuyển hướng về trang giỏ hàng
	}

	// Xử lý xóa sản phẩm khỏi giỏ hàng
	@RequestMapping("/cart/remove/{id}")
	public String remove(@PathVariable("id") Integer id) {
		cartService.remove(id); // Xóa sản phẩm khỏi giỏ hàng
		sessionService.set("sessionProduct", cartService); // Lưu thông tin giỏ hàng vào phiên

		return "redirect:/shop/cart"; // Chuyển hướng về trang giỏ hàng
	}

	// Xử lý áp dụng mã giảm giá
	@PostMapping("/shop/cart/discount")
	public String discount(Model model) {
		String code = paramService.getString("discount", ""); // Lấy mã giảm giá từ request

		Discount discount = discountService.getDiscountByCode(code); // Lấy thông tin mã giảm giá

		AlertModel alertModel = new AlertModel(); // Tạo đối tượng AlertModel

		if (discount == null) { // Nếu mã giảm giá không tồn tại
			cartService.clearDiscount(); // Xóa mã giảm giá khỏi giỏ hàng
			cartService.getAmount(); // Tính lại tổng tiền trong giỏ hàng
			alertModel.setAlert("alert-warning"); // Đặt loại alert là cảnh báo
			alertModel.setContent("Mã giảm giá không tồn tại!"); // Nội dung cảnh báo
			alertModel.setDisplay(true); // Hiển thị alert
		} else {
			if (cartService.getAmount() >= discount.getMoneylimit()) { // Nếu tổng tiền trong giỏ hàng đủ để áp dụng mã giảm giá
				cartService.addDiscount(discount.getId(), discount); // Thêm mã giảm giá vào giỏ hàng
				cartService.getAmount(); // Tính lại tổng tiền trong giỏ hàng
				alertModel.setAlert("alert-success"); // Đặt loại alert là thông báo thành công
				alertModel.setContent("Bạn đã áp dụng mã giảm giá thành công!"); // Nội dung thông báo
				alertModel.setDisplay(true); // Hiển thị alert
			} else {
				cartService.clearDiscount(); // Xóa mã giảm giá khỏi giỏ hàng
				cartService.getAmount(); // Tính lại tổng tiền trong giỏ hàng
				alertModel.setAlert("alert-warning"); // Đặt loại alert là cảnh báo
				alertModel.setContent("Mã giảm giá không tồn tại!"); // Nội dung cảnh báo
				alertModel.setDisplay(true); // Hiển thị alert
			}
		}

		model.addAttribute("showDiscount", true); // Hiển thị thông tin về mã giảm giá
		model.addAttribute("discount", code); // Truyền mã giảm giá vào model
		model.addAttribute("alertModel", alertModel); // Truyền thông tin alert vào model

		model.addAttribute("cart", cartService); // Truyền thông tin giỏ hàng vào model
		return Constants.USER_DISPLAY_SHOPPING_CART; // Trả về trang giỏ hàng
	}

	// Tính tổng số tiền của giỏ hàng
	@ModelAttribute("total")
	public int total() {
		List<CartModel> list = new ArrayList<>(cartService.getItems());
		int total = 0;
		for (CartModel i : list) {
			total = total + i.getProduct().getPrice() * i.getQuality(); // Tính tổng số tiền
		}
		return total;
	}

	// Lấy danh sách sản phẩm bán chạy
	@ModelAttribute("listBestSeller")
	public List<ShowProduct> getListBestSeller(Model model) {
		Pageable topFour = PageRequest.of(0, 4); // Lấy top 4 sản phẩm
		List<BestSellerModel> list = orderService.getListBestSellerProduct(topFour); // Lấy danh sách sản phẩm bán chạy

		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		for (BestSellerModel bestSeller : list) {
			ShowProduct showProduct = new ShowProduct();
			int totalStar = commentService.getAllStarCommentByProductNameSearch(bestSeller.getProduct().getNamesearch()); // Lấy tổng số sao của sản phẩm
			showProduct.setProduct(bestSeller.getProduct()); // Truyền thông tin sản phẩm vào đối tượng ShowProduct
			showProduct.setTotalStar(totalStar); // Truyền tổng số sao vào đối tượng ShowProduct
			listProduct.add(showProduct); // Thêm đối tượng ShowProduct vào danh sách
		}
		return listProduct; // Trả về danh sách sản phẩm
	}
}
