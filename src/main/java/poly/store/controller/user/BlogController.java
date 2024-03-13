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
	BlogService blogService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ParamService paramService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ProductService productService;

	@GetMapping("/blog")
	public String index(Model model, @RequestParam("p") Optional<Integer> p) {
		
		Pageable pageable = PageRequest.of(p.orElse(0), 8);
		
		
		Page<Blog> list = blogService.findAllBlogActive(pageable);
		
		for(Blog blog: list) {
			String uploadDay = paramService.convertDate(blog.getUploadday());
			blog.setUploadday(uploadDay);
		}
		model.addAttribute("blogList", list);
		return Constants.USER_DISPLAY_BLOG;
	}

	@GetMapping("/blog/{nameSearch}")
	public String index(Model model, @PathVariable("nameSearch") String nameSearch) {
		Blog blog = blogService.findBlogByNameSearch(nameSearch);
		User user = userService.findById(blog.getPersoncreate());
		
		String uploadDay = paramService.convertDate(blog.getUploadday());
		
		blog.setUploadday(uploadDay);
		
		System.out.println(blog.getUploadday());
		
		model.addAttribute("blogInfor", blog);
		model.addAttribute("blogUser", user);
		return Constants.USER_DISPLAY_BLOG_DETAIL;
	}
	
	@ModelAttribute("listBestSeller")
	public List<ShowProduct> getListBestSeller(Model model){
		Pageable topFour = PageRequest.of(0, 4);
		
		List<BestSellerModel> list = orderService.getListBestSellerProduct(topFour);
		
		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();
		
		for(BestSellerModel bestSeller: list) {
			ShowProduct showProduct = new ShowProduct();
			int totalStar = commentService.getAllStarCommentByProductNameSearch(bestSeller.getProduct().getNamesearch());
			showProduct.setProduct(bestSeller.getProduct());
			showProduct.setTotalStar(totalStar);
			listProduct.add(showProduct);
		}		
		return listProduct;
	}
	
	@ModelAttribute("listSale")
	public List<ShowProduct> getListProductSale(Model model) {
		List<Product> list = productService.getListProductSales();

		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		for (Product product : list) {
			ShowProduct showProduct = new ShowProduct();
			int totalStar = commentService.getAllStarCommentByProductNameSearch(product.getNamesearch());
			showProduct.setProduct(product);
			showProduct.setTotalStar(totalStar);
			listProduct.add(showProduct);
		}

		return listProduct;
	}
}
