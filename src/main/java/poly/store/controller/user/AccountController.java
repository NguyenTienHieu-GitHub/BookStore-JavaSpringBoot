package poly.store.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import poly.store.common.Constants;
import poly.store.entity.Favorite;
import poly.store.entity.Order;
import poly.store.model.AlertModel;
import poly.store.model.OrderModel;
import poly.store.service.FavoriteService;
import poly.store.service.OrderService;
import poly.store.service.ParamService;
import poly.store.service.UserService;
import poly.store.service.impl.ShoppingCartServiceImpl;

@Controller
public class AccountController {
	@Autowired
	UserService userService;

	@Autowired
	FavoriteService favoriteService;

	@Autowired
	OrderService orderService;

	@Autowired
	ShoppingCartServiceImpl cartService;

	@Autowired
	ParamService paramService;

	@GetMapping("/account")
	public String index() {
		return Constants.USER_DISPLAY_ACCOUNT_PAGE;
	}

	@GetMapping("/account/information")
	public String information(Model model) {
		return Constants.USER_DISPLAY_ACCOUNT_INFORMATION;
	}

	@GetMapping("/account/favorite")
	public String favorite(Model model) {

		List<Favorite> listFavorite = favoriteService.getListFavoriteByEmail();

		model.addAttribute("listFavorite", listFavorite);
		return Constants.USER_DISPLAY_ACCOUNT_FAVORITE;
	}

	@GetMapping("/account/favorite/delete/{id}")
	public String deleteFavorite(@PathVariable("id") int id, Model model) {

		favoriteService.delete(id);

		return "redirect:/account/favorite";
	}

	@GetMapping("/account/order")
	public String order(Model model) {

		List<OrderModel> listOrderHistory = orderService.listOrderHistory();

		for (OrderModel list : listOrderHistory) {
			Order order = orderService.getOrderByName(list.getId()).get(0);
			if (order != null) {
				list.setDiscount(order.getDiscount());
			}
		}

		model.addAttribute("listOrder", listOrderHistory);

		return Constants.USER_DISPLAY_ACCOUNT_ORDER;
	}

	@GetMapping("/account/order/invoice/{id}")
	public String invoice(@PathVariable("id") String id, Model model) {
		List<Order> list = orderService.listOrderByCodeAndUsername(id);
		if (list.isEmpty()) {
			return Constants.USER_DISPLAY_404_PAGE;
		} else {
			int total = 0;
			int discount = 0;
			for (Order order : list) {
				total = total + order.getProduct().getPrice() * order.getQuality();
			}
			if (list.get(0).getDiscount() != null) {
				discount = list.get(0).getDiscount().getPrice();
			}
			model.addAttribute("listProduct", list);
			model.addAttribute("total", total);
			model.addAttribute("discount", discount);
		}
		return Constants.USER_DISPLAY_ACCOUNT_INVOICE;
	}

	@GetMapping("/account/order/cancel/{id}")
	public String cancel(@PathVariable("id") String id, Model model) {
		orderService.cancelOrder(id);
		return "redirect:/account/order";
	}

	@GetMapping("/account/order/search")
	public String search(Model model) {
		AlertModel alertModel = new AlertModel();
		model.addAttribute("alertModel", alertModel);
		return Constants.USER_DISPLAY_ACCOUNT_ORDER_SEARCH;
	}

	@PostMapping("/account/order/search")
	public String searchByCode(Model model) {
		AlertModel alertModel = new AlertModel();
		String code = paramService.getString("code", "");

		if (code.trim().isEmpty()) {
			alertModel.setAlert("alert-warning");
			alertModel.setContent("Vui lòng nhập mã đơn hàng!");
			alertModel.setDisplay(true);
		} else {
			List<Order> list = orderService.listOrderByCodeAndUsername(code);
			if (list.isEmpty()) {
				alertModel.setAlert("alert-warning");
				alertModel.setContent("Mã đơn hàng không tồn tại!");
				alertModel.setDisplay(true);
			} else {
				return "redirect:/account/order/invoice/" + code;
			}
		}

		model.addAttribute("code", code.trim());
		model.addAttribute("alertModel", alertModel);

		return Constants.USER_DISPLAY_ACCOUNT_ORDER_SEARCH;
	}
}
