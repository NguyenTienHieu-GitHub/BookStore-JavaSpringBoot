package poly.store.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import poly.store.common.Constants;
import poly.store.entity.Blog;
import poly.store.entity.Product;
import poly.store.entity.User;
import poly.store.model.BestSellerModel;
import poly.store.model.ShowProduct;
import poly.store.service.BlogService;
import poly.store.service.CommentService;
import poly.store.service.OrderService;
import poly.store.service.ParamService;
import poly.store.service.ProductService;
import poly.store.service.UserService;

@Controller
public class BlogController {
	@Autowired
	BlogService blogService; // Dịch vụ quản lý bài viết trong blog

	@Autowired
	UserService userService; // Dịch vụ quản lý người dùng

	@Autowired
	ParamService paramService; // Dịch vụ quản lý tham số

	@Autowired
	CommentService commentService; // Dịch vụ quản lý bình luận

	@Autowired
	OrderService orderService; // Dịch vụ quản lý đơn hàng

	@Autowired
	ProductService productService; // Dịch vụ quản lý sản phẩm

	// Hiển thị trang danh sách blog
	@GetMapping("/blog")
	public String index(Model model, @RequestParam("p") Optional<Integer> p) {
		Pageable pageable = PageRequest.of(p.orElse(0), 8); // Xác định số lượng bài viết trên mỗi trang

		Page<Blog> list = blogService.findAllBlogActive(pageable); // Lấy danh sách bài viết blog

		for (Blog blog : list) { // Duyệt qua từng bài viết
			String uploadDay = paramService.convertDate(blog.getUploadday()); // Chuyển định dạng ngày
			blog.setUploadday(uploadDay); // Cập nhật ngày upload đã chuyển định dạng
		}
		model.addAttribute("blogList", list); // Truyền danh sách bài viết vào model
		return Constants.USER_DISPLAY_BLOG; // Trả về trang hiển thị danh sách blog
	}

	// Hiển thị trang chi tiết blog
	@GetMapping("/blog/{nameSearch}")
	public String index(Model model, @PathVariable("nameSearch") String nameSearch) {
		Blog blog = blogService.findBlogByNameSearch(nameSearch); // Lấy thông tin của bài viết theo tên

		User user = userService.findById(blog.getPersoncreate()); // Lấy thông tin của người viết bài

		String uploadDay = paramService.convertDate(blog.getUploadday()); // Chuyển định dạng ngày
		blog.setUploadday(uploadDay); // Cập nhật ngày upload đã chuyển định dạng

		model.addAttribute("blogInfor", blog); // Truyền thông tin bài viết vào model
		model.addAttribute("blogUser", user); // Truyền thông tin người viết vào model
		return Constants.USER_DISPLAY_BLOG_DETAIL; // Trả về trang hiển thị chi tiết bài viết
	}

	// Lấy danh sách sản phẩm bán chạy để hiển thị trên sidebar
	@ModelAttribute("listBestSeller")
	public List<ShowProduct> getListBestSeller(Model model) {
		Pageable topFour = PageRequest.of(0, 4); // Lấy top 4 sản phẩm

		List<BestSellerModel> list = orderService.getListBestSellerProduct(topFour); // Lấy danh sách sản phẩm bán chạy

		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		for (BestSellerModel bestSeller : list) { // Duyệt qua từng sản phẩm
			ShowProduct showProduct = new ShowProduct();
			int totalStar = commentService.getAllStarCommentByProductNameSearch(bestSeller.getProduct().getNamesearch()); // Lấy tổng số sao của sản phẩm
			showProduct.setProduct(bestSeller.getProduct()); // Truyền thông tin sản phẩm vào đối tượng ShowProduct
			showProduct.setTotalStar(totalStar); // Truyền tổng số sao vào đối tượng ShowProduct
			listProduct.add(showProduct); // Thêm đối tượng ShowProduct vào danh sách
		}
		return listProduct; // Trả về danh sách sản phẩm
	}

	// Lấy danh sách sản phẩm đang giảm giá để hiển thị trên sidebar
	@ModelAttribute("listSale")
	public List<ShowProduct> getListProductSale(Model model) {
		List<Product> list = productService.getListProductSales(); // Lấy danh sách sản phẩm đang giảm giá

		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		for (Product product : list) { // Duyệt qua từng sản phẩm
			ShowProduct showProduct = new ShowProduct();
			int totalStar = commentService.getAllStarCommentByProductNameSearch(product.getNamesearch()); // Lấy tổng số sao của sản phẩm
			showProduct.setProduct(product); // Truyền thông tin sản phẩm vào đối tượng ShowProduct
			showProduct.setTotalStar(totalStar); // Truyền tổng số sao vào đối tượng ShowProduct
			listProduct.add(showProduct); // Thêm đối tượng ShowProduct vào danh sách
		}

		return listProduct; // Trả về danh sách sản phẩm
	}
}
