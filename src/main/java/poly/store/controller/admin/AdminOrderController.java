package poly.store.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import poly.store.common.Constants;

@Controller
public class AdminOrderController {

	// Phương thức này xử lý khi người dùng truy cập vào trang danh sách các đơn hàng đang chờ xử lý.
	@GetMapping("/admin/order/list/pending")
	public String pending(Model model) {
		// Trả về tên của view để hiển thị danh sách các đơn hàng đang chờ xử lý
		return Constants.USER_DISPLAY_ADMIN_ORDER_PENDING;
	}

	// Phương thức này xử lý khi người dùng truy cập vào trang danh sách các đơn hàng đang giao hàng.
	@GetMapping("/admin/order/list/shipping")
	public String shipping(Model model) {
		// Trả về tên của view để hiển thị danh sách các đơn hàng đang giao hàng
		return Constants.USER_DISPLAY_ADMIN_ORDER_SHIPPING;
	}

	// Phương thức này xử lý khi người dùng truy cập vào trang danh sách các đơn hàng đã giao thành công.
	@GetMapping("/admin/order/list/success")
	public String success(Model model) {
		// Trả về tên của view để hiển thị danh sách các đơn hàng đã giao thành công
		return Constants.USER_DISPLAY_ADMIN_ORDER_SUCCESS;
	}

	// Phương thức này xử lý khi người dùng truy cập vào trang danh sách các đơn hàng đã hủy.
	@GetMapping("/admin/order/list/cancel")
	public String cancel(Model model) {
		// Trả về tên của view để hiển thị danh sách các đơn hàng đã hủy
		return Constants.USER_DISPLAY_ADMIN_ORDER_CANCEL;
	}
}
