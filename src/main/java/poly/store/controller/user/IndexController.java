package poly.store.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import poly.store.common.Constants;
import poly.store.entity.Blog;
import poly.store.entity.Manufacturer;
import poly.store.entity.Product;
import poly.store.model.BestSellerModel;
import poly.store.model.ShowProduct;
import poly.store.service.BlogService;
import poly.store.service.CommentService;
import poly.store.service.FavoriteService;
import poly.store.service.ManufacturerService;
import poly.store.service.OrderService;
import poly.store.service.ParamService;
import poly.store.service.ProductService;
import poly.store.service.UserRoleService;

@Controller
public class IndexController {
	// Dịch vụ quản lý vai trò người dùng
	@Autowired
	UserRoleService userRoleService;

	// Dịch vụ quản lý sản phẩm
	@Autowired
	ProductService productService;

	// Dịch vụ quản lý nhà sản xuất
	@Autowired
	ManufacturerService manufacturerService;

	// Dịch vụ quản lý bình luận
	@Autowired
	CommentService commentService;

	// Dịch vụ quản lý blog
	@Autowired
	BlogService blogService;

	// Dịch vụ tham số
	@Autowired
	ParamService paramService;

	// Dịch vụ quản lý đơn hàng
	@Autowired
	OrderService orderService;

	// Dịch vụ quản lý mục yêu thích
	@Autowired
	FavoriteService favoriteService;

	// Trang chủ của người dùng
	@GetMapping({ "/", "/index" })
	public String index(Model model) {
		return Constants.USER_DISPLAY_INDEX; // Trả về tên của trang chủ
	}

	// Lấy danh sách tất cả các nhà sản xuất
	@ModelAttribute("manufacturer")
	public List<Manufacturer> manufacture(Model model) {
		List<Manufacturer> list = manufacturerService.findAll(); // Gọi dịch vụ để lấy danh sách tất cả nhà sản xuất
		return list; // Trả về danh sách nhà sản xuất cho view
	}

	// Lấy danh sách sản phẩm mới nhất
	@ModelAttribute("latestProduct")
	public List<List<ShowProduct>> getLatestProduct(Model model) {
		List<Product> list = productService.getListLatestProduct(); // Gọi dịch vụ để lấy danh sách sản phẩm mới nhất

		List<ShowProduct> temp = new ArrayList<>(); // Tạo danh sách tạm thời để chứa từng cặp sản phẩm (2 sản phẩm mỗi cặp)
		List<List<ShowProduct>> result = new ArrayList<List<ShowProduct>>(); // Danh sách kết quả sẽ chứa các danh sách sản phẩm tạm thời

		for (int i = 0; i < list.size(); i++) {
			int totalStar = commentService.getAllStarCommentByProductNameSearch(list.get(i).getNamesearch()); // Gọi dịch vụ để tính tổng số sao đánh giá của sản phẩm

			ShowProduct showProduct = new ShowProduct(); // Tạo đối tượng ShowProduct để chứa thông tin sản phẩm và số sao đánh giá
			showProduct.setProduct(list.get(i)); // Đặt thông tin sản phẩm cho đối tượng ShowProduct
			showProduct.setTotalStar(totalStar); // Đặt số sao đánh giá cho đối tượng ShowProduct
			temp.add(showProduct); // Thêm đối tượng ShowProduct vào danh sách tạm thời
			if (i % 2 != 0) { // Nếu đã thêm đủ 2 sản phẩm vào danh sách tạm thời
				result.add(temp); // Thêm danh sách tạm thời vào danh sách kết quả
				temp = new ArrayList<>(); // Khởi tạo danh sách tạm thời mới
			}
			if (i == (list.size() - 1)) { // Nếu đã duyệt hết danh sách sản phẩm
				if (i % 2 == 0) { // Nếu danh sách có số lượng lẻ, cần thêm sản phẩm cuối cùng vào danh sách kết quả
					result.add(temp); // Thêm danh sách tạm thời vào danh sách kết quả
					temp = new ArrayList<>(); // Khởi tạo danh sách tạm thời mới
				}
			}
		}

		return result; // Trả về danh sách sản phẩm theo cặp cho view
	}

	// Lấy danh sách sản phẩm được xem nhiều nhất
	@ModelAttribute("viewsProduct")
	public List<ShowProduct> getViewsProduct(Model model) {
		List<Product> list = productService.getListViewsProduct(); // Gọi dịch vụ để lấy danh sách sản phẩm được xem nhiều nhất
		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		for (Product product : list) { // Duyệt qua từng sản phẩm trong danh sách
			ShowProduct showProduct = new ShowProduct(); // Tạo đối tượng ShowProduct để chứa thông tin sản phẩm và số sao đánh giá
			int totalStar = commentService.getAllStarCommentByProductNameSearch(product.getNamesearch()); // Gọi dịch vụ để tính tổng số sao đánh giá của sản phẩm
			showProduct.setProduct(product); // Đặt thông tin sản phẩm cho đối tượng ShowProduct
			showProduct.setTotalStar(totalStar); // Đặt số sao đánh giá cho đối tượng ShowProduct
			listProduct.add(showProduct); // Thêm đối tượng ShowProduct vào danh sách sản phẩm
		}

		return listProduct; // Trả về danh sách sản phẩm cho view
	}

	// Lấy danh sách 6 bài blog mới nhất
	@ModelAttribute("listBlog")
	public List<Blog> getListBlog(Model model) {
		List<Blog> listBlog = blogService.getSixBlog(); // Gọi dịch vụ để lấy danh sách 6 bài blog mới nhất
		for (Blog blog : listBlog) { // Duyệt qua từng bài blog trong danh sách
			String uploadDay = paramService.convertDate(blog.getUploadday()); // Chuyển đổi ngày upload sang định dạng mong muốn
			blog.setUploadday(uploadDay); // Cập nhật ngày upload mới cho blog
		}
		return listBlog; // Trả về danh sách blog cho view
	}

	// Lấy danh sách sản phẩm đang được giảm giá
	@ModelAttribute("listSale")
	public List<ShowProduct> getListProductSale(Model model) {
		List<Product> list = productService.getListProductSales(); // Gọi dịch vụ để lấy danh sách sản phẩm đang được giảm giá

		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		for (Product product : list) { // Duyệt qua từng sản phẩm trong danh sách
			ShowProduct showProduct = new ShowProduct(); // Tạo đối tượng ShowProduct để chứa thông tin sản phẩm và số sao đánh giá
			int totalStar = commentService.getAllStarCommentByProductNameSearch(product.getNamesearch()); // Gọi dịch vụ để tính tổng số sao đánh giá của sản phẩm
			showProduct.setProduct(product); // Đặt thông tin sản phẩm cho đối tượng ShowProduct
			showProduct.setTotalStar(totalStar); // Đặt số sao đánh giá cho đối tượng ShowProduct
			listProduct.add(showProduct); // Thêm đối tượng ShowProduct vào danh sách sản phẩm
		}

		return listProduct; // Trả về danh sách sản phẩm cho view
	}

	// Lấy danh sách 4 sản phẩm bán chạy nhất
	@ModelAttribute("listBestSeller")
	public List<ShowProduct> getListBestSeller(Model model){
		Pageable topFour = PageRequest.of(0, 4);

		List<BestSellerModel> list = orderService.getListBestSellerProduct(topFour); // Gọi dịch vụ để lấy danh sách 4 sản phẩm bán chạy nhất

		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		for(BestSellerModel bestSeller: list) { // Duyệt qua từng sản phẩm trong danh sách
			ShowProduct showProduct = new ShowProduct(); // Tạo đối tượng ShowProduct để chứa thông tin sản phẩm và số sao đánh giá
			int totalStar = commentService.getAllStarCommentByProductNameSearch(bestSeller.getProduct().getNamesearch()); // Gọi dịch vụ để tính tổng số sao đánh giá của sản phẩm
			showProduct.setProduct(bestSeller.getProduct()); // Đặt thông tin sản phẩm cho đối tượng ShowProduct
			showProduct.setTotalStar(totalStar); // Đặt số sao đánh giá cho đối tượng ShowProduct
			listProduct.add(showProduct); // Thêm đối tượng ShowProduct vào danh sách sản phẩm
		}
		return listProduct; // Trả về danh sách sản phẩm cho view
	}

	// Lấy danh sách 4 sản phẩm được yêu thích nhất
	@ModelAttribute("listFavorite")
	public List<ShowProduct> demo(Model model) {
		Pageable topFour = PageRequest.of(0, 4);
		List<BestSellerModel> list = favoriteService.getListBestSellerProduct(topFour); // Gọi dịch vụ để lấy danh sách 4 sản phẩm được yêu thích nhất

		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		for(BestSellerModel bestSeller: list) { // Duyệt qua từng sản phẩm trong danh sách
			ShowProduct showProduct = new ShowProduct(); // Tạo đối tượng ShowProduct để chứa thông tin sản phẩm và số sao đánh giá
			int totalStar = commentService.getAllStarCommentByProductNameSearch(bestSeller.getProduct().getNamesearch()); // Gọi dịch vụ để tính tổng số sao đánh giá của sản phẩm
			showProduct.setProduct(bestSeller.getProduct()); // Đặt thông tin sản phẩm cho đối tượng ShowProduct
			showProduct.setTotalStar(totalStar); // Đặt số sao đánh giá cho đối tượng ShowProduct
			listProduct.add(showProduct); // Thêm đối tượng ShowProduct vào danh sách sản phẩm
		}
		return listProduct; // Trả về danh sách sản phẩm cho view
	}

}
