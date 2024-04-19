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
	UserService userService; // Service đối với người dùng

	@Autowired
	FavoriteService favoriteService; // Service đối với danh sách yêu thích

	@Autowired
	OrderService orderService; // Service đối với đơn hàng

	@Autowired
	ShoppingCartServiceImpl cartService; // Service đối với giỏ hàng

	@Autowired
	ParamService paramService; // Service đối với tham số

	// Phương thức này đại diện cho trang tài khoản của người dùng
	@GetMapping("/account")
	public String index() {
		return Constants.USER_DISPLAY_ACCOUNT_PAGE; // Trả về view để hiển thị trang tài khoản người dùng
	}

	// Phương thức này đại diện cho trang hiển thị thông tin cá nhân
	@GetMapping("/account/information")
	public String information(Model model) {
		return Constants.USER_DISPLAY_ACCOUNT_INFORMATION; // Trả về view để hiển thị thông tin cá nhân
	}

	// Phương thức này đại diện cho trang hiển thị danh sách sản phẩm yêu thích
	@GetMapping("/account/favorite")
	public String favorite(Model model) {
		// Lấy danh sách sản phẩm yêu thích của người dùng và thêm vào model
		List<Favorite> listFavorite = favoriteService.getListFavoriteByEmail();
		model.addAttribute("listFavorite", listFavorite);
		return Constants.USER_DISPLAY_ACCOUNT_FAVORITE; // Trả về view để hiển thị danh sách yêu thích
	}

	// Phương thức này để xóa một sản phẩm yêu thích
	@GetMapping("/account/favorite/delete/{id}")
	public String deleteFavorite(@PathVariable("id") int id, Model model) {
		favoriteService.delete(id); // Xóa sản phẩm yêu thích với ID cụ thể
		return "redirect:/account/favorite"; // Chuyển hướng người dùng về trang yêu thích
	}

	// Phương thức này đại diện cho trang hiển thị lịch sử đơn hàng
	@GetMapping("/account/order")
	public String order(Model model) {
		// Lấy lịch sử đơn hàng của người dùng và tính toán giảm giá (nếu có)
		List<OrderModel> listOrderHistory = orderService.listOrderHistory();
		for (OrderModel list : listOrderHistory) {
			Order order = orderService.getOrderByName(list.getId()).get(0);
			if (order != null) {
				list.setDiscount(order.getDiscount());
			}
		}
		// Thêm danh sách đơn hàng vào model
		model.addAttribute("listOrder", listOrderHistory);
		return Constants.USER_DISPLAY_ACCOUNT_ORDER; // Trả về view để hiển thị lịch sử đơn hàng
	}

	// Phương thức này đại diện cho trang hiển thị hóa đơn của một đơn hàng cụ thể
	@GetMapping("/account/order/invoice/{id}")
	public String invoice(@PathVariable("id") String id, Model model) {
		// Lấy thông tin đơn hàng và tính tổng và giảm giá
		List<Order> list = orderService.listOrderByCodeAndUsername(id);
		if (list.isEmpty()) {
			return Constants.USER_DISPLAY_404_PAGE; // Nếu không tìm thấy đơn hàng, trả về trang lỗi 404
		} else {
			int total = 0;
			int discount = 0;
			for (Order order : list) {
				total = total + order.getProduct().getPrice() * order.getQuality();
			}
			if (list.get(0).getDiscount() != null) {
				discount = list.get(0).getDiscount().getPrice();
			}
			// Thêm thông tin vào model
			model.addAttribute("listProduct", list);
			model.addAttribute("total", total);
			model.addAttribute("discount", discount);
		}
		return Constants.USER_DISPLAY_ACCOUNT_INVOICE; // Trả về view để hiển thị hóa đơn
	}

	// Phương thức này để hủy một đơn hàng
	@GetMapping("/account/order/cancel/{id}")
	public String cancel(@PathVariable("id") String id, Model model) {
		orderService.cancelOrder(id); // Hủy đơn hàng với ID cụ thể
		return "redirect:/account/order"; // Chuyển hướng người dùng về trang lịch sử đơn hàng
	}

	// Phương thức này đại diện cho trang tìm kiếm đơn hàng
	@GetMapping("/account/order/search")
	public String search(Model model) {
		AlertModel alertModel = new AlertModel(); // Tạo một model cho cảnh báo
		model.addAttribute("alertModel", alertModel);
		return Constants.USER_DISPLAY_ACCOUNT_ORDER_SEARCH; // Trả về view để hiển thị trang tìm kiếm đơn hàng
	}

	// Phương thức này để tìm kiếm đơn hàng bằng mã
	@PostMapping("/account/order/search")
	public String searchByCode(Model model) {
		AlertModel alertModel = new AlertModel(); // Tạo một model cho cảnh báo
		String code = paramService.getString("code", "");

		if (code.trim().isEmpty()) {
			// Nếu mã trống, hiển thị cảnh báo
			alertModel.setAlert("alert-warning");
			alertModel.setContent("Vui lòng nhập mã đơn hàng!");
			alertModel.setDisplay(true);
		} else {
			List<Order> list = orderService.listOrderByCodeAndUsername(code);
			if (list.isEmpty()) {
				// Nếu không tìm thấy đơn hàng, hiển thị cảnh báo
				alertModel.setAlert("alert-warning");
				alertModel.setContent("Mã đơn hàng không tồn tại!");
				alertModel.setDisplay(true);
			} else {
				// Nếu tìm thấy đơn hàng, chuyển hướng đến trang hóa đơn
				return "redirect:/account/order/invoice/" + code;
			}
		}

		// Thêm mã và cảnh báo vào model
		model.addAttribute("code", code.trim());
		model.addAttribute("alertModel", alertModel);

		return Constants.USER_DISPLAY_ACCOUNT_ORDER_SEARCH; // Trả về view để hiển thị trang tìm kiếm đơn hàng
	}
}
