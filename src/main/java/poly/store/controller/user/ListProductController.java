
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
import poly.store.entity.Category;
import poly.store.entity.Manufacturer;
import poly.store.model.BestSellerModel;
import poly.store.model.ShowProduct;
import poly.store.service.CategoryService;
import poly.store.service.CommentService;
import poly.store.service.ManufacturerService;
import poly.store.service.OrderService;
import poly.store.service.ProductService;
import poly.store.service.SessionService;

@Controller
public class ListProductController {
	@Autowired
	ProductService productService; // Dịch vụ quản lý sản phẩm

	@Autowired
	CategoryService categoryService; // Dịch vụ quản lý danh mục

	@Autowired
	SessionService sessionService; // Dịch vụ quản lý phiên đăng nhập của người dùng

	@Autowired
	ManufacturerService manufacturerService; // Dịch vụ quản lý nhà sản xuất

	@Autowired
	OrderService orderService; // Dịch vụ quản lý đơn hàng

	@Autowired
	CommentService commentService; // Dịch vụ quản lý bình luận của người dùng

	@GetMapping("/danh-sach/{nameSearch}")
	public String index(@PathVariable("nameSearch") String nameSearch, Model model,
						@RequestParam("p") Optional<Integer> p, @RequestParam(name = "gia", required = false) String price,
						@RequestParam(name = "hang", required = false) String manu,
						@RequestParam(name = "xep", required = false) String sort) {
		try {
			Pageable pageable = PageRequest.of(p.orElse(0), 15);

			// Lấy danh sách sản phẩm dựa trên bộ lọc và từ khóa tìm kiếm
			Page<ShowProduct> listProduct = productService.getListProductByFilter(nameSearch, price, manu, sort,
					pageable, "danh-sach", "", "");

			model.addAttribute("listProduct", listProduct); // Thêm danh sách sản phẩm vào model
			model.addAttribute("price", price); // Thêm giá vào model
			model.addAttribute("manu", manu); // Thêm nhà sản xuất vào model
			model.addAttribute("sort", sort); // Thêm tiêu chí sắp xếp vào model
			model.addAttribute("nameSearch", nameSearch); // Thêm từ khóa tìm kiếm vào model

			// Lấy thông tin danh mục sản phẩm dựa trên từ khóa tìm kiếm
			Category category = categoryService.getCategoryByNameSearch(nameSearch);
			model.addAttribute("inforCategory", category); // Thêm thông tin danh mục vào model
		} catch (Exception e) {
			return "redirect:/index";
		}

		return Constants.USER_DISPLAY_LIST_PRODUCT_BY_CATEGORY; // Trả về view hiển thị danh sách sản phẩm theo danh mục
	}

	@GetMapping("/uu-dai")
	public String sales(Model model, @RequestParam("p") Optional<Integer> p,
						@RequestParam(name = "gia", required = false) String price,
						@RequestParam(name = "hang", required = false) String manu,
						@RequestParam(name = "xep", required = false) String sort) {

		Pageable pageable = PageRequest.of(p.orElse(0), 15);

		// Lấy danh sách sản phẩm đang giảm giá dựa trên bộ lọc
		Page<ShowProduct> listProduct = productService.getListProductByFilter("", price, manu, sort, pageable, "uu-dai",
				"", "");
		model.addAttribute("listProduct", listProduct); // Thêm danh sách sản phẩm vào model
		model.addAttribute("price", price); // Thêm giá vào model
		model.addAttribute("manu", manu); // Thêm nhà sản xuất vào model
		model.addAttribute("sort", sort); // Thêm tiêu chí sắp xếp vào model

		return Constants.USER_DISPLAY_LIST_PRODUCT_BY_SALES; // Trả về view hiển thị danh sách sản phẩm đang giảm giá
	}

	@GetMapping("/tim-kiem")
	public String searcch(Model model, @RequestParam(name = "q", required = false) String name,
						  @RequestParam("p") Optional<Integer> p, @RequestParam(name = "gia", required = false) String price,
						  @RequestParam(name = "hang", required = false) String manu,
						  @RequestParam(name = "xep", required = false) String sort,
						  @RequestParam(name = "category", required = false) String category) {
		try {
			Pageable pageable = PageRequest.of(p.orElse(0), 15);

			if (category == null) {
				category = "";
			}

			if (name == null) {
				name = "";
			}

			// Tìm kiếm sản phẩm dựa trên bộ lọc và từ khóa
			Page<ShowProduct> listProduct = productService.getListProductByFilter("", price, manu, sort, pageable,
					"tim-kiem", name.trim(), category);
			if (!name.trim().isEmpty()) {
				model.addAttribute("title", "- " + name.trim()); // Thêm tiêu đề tìm kiếm vào model
				model.addAttribute("name", name.trim()); // Thêm từ khóa tìm kiếm vào model
			}
			model.addAttribute("cate", category); // Thêm danh mục vào model
			model.addAttribute("listProduct", listProduct); // Thêm danh sách sản phẩm vào model
			model.addAttribute("price", price); // Thêm giá vào model
			model.addAttribute("manu", manu); // Thêm nhà sản xuất vào model
			model.addAttribute("sort", sort); // Thêm tiêu chí sắp xếp vào model

			// Lấy danh sách danh mục sản phẩm
			List<Category> listCategory = categoryService.findAll();
			model.addAttribute("listCategory", listCategory); // Thêm danh sách danh mục vào model

		} catch (Exception e) {
			return "redirect:/index";
		}

		return Constants.USER_DISPLAY_LIST_PRODUCT_BY_SEARCH; // Trả về view hiển thị kết quả tìm kiếm sản phẩm
	}

	@ModelAttribute("listManu")
	public List<Manufacturer> listManu() {
		// Lấy danh sách nhà sản xuất
		List<Manufacturer> list = manufacturerService.findAll();
		return list;
	}

	@ModelAttribute("listBestSeller")
	public List<ShowProduct> getListBestSeller(Model model) {
		Pageable topFour = PageRequest.of(0, 4);

		// Lấy danh sách sản phẩm bán chạy nhất
		List<BestSellerModel> list = orderService.getListBestSellerProduct(topFour);

		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		for (BestSellerModel bestSeller : list) {
			ShowProduct showProduct = new ShowProduct();
			// Lấy tổng số sao đánh giá của sản phẩm
			int totalStar = commentService
					.getAllStarCommentByProductNameSearch(bestSeller.getProduct().getNamesearch());
			showProduct.setProduct(bestSeller.getProduct()); // Thiết lập sản phẩm
			showProduct.setTotalStar(totalStar); // Thiết lập tổng số sao
			listProduct.add(showProduct); // Thêm sản phẩm vào danh sách
		}
		return listProduct; // Trả về danh sách sản phẩm bán chạy nhất
	}
}
