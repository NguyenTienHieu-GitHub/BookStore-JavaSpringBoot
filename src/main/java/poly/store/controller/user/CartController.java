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
	ShoppingCartServiceImpl cartService;
	
	@Autowired
	DiscountService discountService;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	ParamService paramService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/shop/cart")
	public String index(Model model) {
		model.addAttribute("showDiscount", false);
				
		cartService.clearDiscount();
		model.addAttribute("cart", cartService);
		
		AlertModel alertModel = new AlertModel();
		model.addAttribute("alertModel", alertModel);
		
		return Constants.USER_DISPLAY_SHOPPING_CART;
	}
	
	@PostMapping("/cart/update/{id}")
	public String update(@PathVariable("id") Integer id, HttpServletRequest req) {
		String qty = req.getParameter("quantity");	
		ProductModel product = productService.getOneProductById(id);
		
		if(Integer.parseInt(qty) <= product.getQuality()) {
			cartService.update(id, Integer.parseInt(qty));
		}
		
		return "redirect:/shop/cart";
	}
	
	@RequestMapping("/cart/remove/{id}")
	public String remove(@PathVariable("id") Integer id) {
		cartService.remove(id);	
		sessionService.set("sessionProduct", cartService);
		return "redirect:/shop/cart";
	}
	
	@GetMapping("/shop/cart/discount")
	public String getDiscount() {
		return "redirect:/shop/cart";
	}
	
	@PostMapping("/shop/cart/discount")
	public String discount(Model model) {
		String code = paramService.getString("discount", "");
		
		Discount discount = discountService.getDiscountByCode(code);
		
		AlertModel alertModel = new AlertModel();
		
		if(discount == null) {
			cartService.clearDiscount();
			cartService.getAmount();
			alertModel.setAlert("alert-warning");
			alertModel.setContent("Mã giảm giá không tồn tại!");
			alertModel.setDisplay(true);
		}
		
		else {
			if(cartService.getAmount() >= discount.getMoneylimit()) {
				cartService.addDiscount(discount.getId(), discount);
				cartService.getAmount();
				alertModel.setAlert("alert-success");
				alertModel.setContent("Bạn đã áp dụng mã giảm giá thành công!");
				alertModel.setDisplay(true);
			}
			else {
				cartService.clearDiscount();
				cartService.getAmount();
				alertModel.setAlert("alert-warning");
				alertModel.setContent("Mã giảm giá không tồn tại!");
				alertModel.setDisplay(true);			
			}
		}
		
		model.addAttribute("showDiscount", true);
		model.addAttribute("discount", code);
		model.addAttribute("alertModel", alertModel);
		
		model.addAttribute("cart", cartService);
		return Constants.USER_DISPLAY_SHOPPING_CART;
	}
	
	@ModelAttribute("total")
	public int tolal() {
		List<CartModel> list = new ArrayList<>(cartService.getItems());
		int total = 0;
		for(CartModel i: list) {
			total = total + i.getProduct().getPrice() * i.getQuality();
		}
		return total;
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
}
