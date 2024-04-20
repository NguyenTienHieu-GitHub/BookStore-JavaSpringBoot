package poly.store.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import poly.store.common.Constants;

@Controller
public class AdminStatisticalController {

	// Phương thức này xử lý khi người dùng truy cập vào trang thống kê sản phẩm theo ngày.
	@GetMapping("/admin/statistical/product/day")
	public String product(Model model) {
		// Trả về tên của view để hiển thị thống kê sản phẩm theo ngày
		return Constants.USER_DISPLAY_ADMIN_STATISTICAL_PRODUCT_DAY;
	}

	// Phương thức này xử lý khi người dùng truy cập vào trang thống kê sản phẩm trong kho.
	@GetMapping("/admin/statistical/product/warehouse")
	public String warehouse(Model model) {
		// Trả về tên của view để hiển thị thống kê sản phẩm trong kho
		return Constants.USER_DISPLAY_ADMIN_STATISTICAL_WAREHOUSE_PRODUCT;
	}

	// Phương thức này xử lý khi người dùng truy cập vào trang thống kê doanh thu.
	@GetMapping("/admin/statistical/revenue")
	public String revenue(Model model) {
		// Trả về tên của view để hiển thị thống kê doanh thu
		return Constants.USER_DISPLAY_ADMIN_STATISTICAL_REVENUE;
	}

	// Phương thức này xử lý khi người dùng truy cập vào trang thống kê đơn hàng.
	@GetMapping("/admin/statistical/order")
	public String order(Model model) {
		// Trả về tên của view để hiển thị thống kê đơn hàng
		return Constants.USER_DISPLAY_ADMIN_STATISTICAL_ORDER;
	}

}
