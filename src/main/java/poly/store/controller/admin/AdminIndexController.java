/**
 * Controller này điều khiển các hoạt động liên quan đến trang chính của trang quản trị.
 */
package poly.store.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import poly.store.common.Constants;
import poly.store.model.StatisticalRevenue;
import poly.store.service.OrderService;
import poly.store.service.ProductService;
import poly.store.service.UserService;

@Controller
public class AdminIndexController {

	@Autowired
	OrderService orderService;

	@Autowired
	UserService userService;

	@Autowired
	ProductService productService;

	/**
	 * Phương thức này xử lý khi người dùng truy cập vào trang chính của trang quản trị.
	 * Nó lấy thông tin thống kê về số đơn hàng, doanh thu, số lượng khách hàng, và số lượng sản phẩm.
	 * Sau đó, nó thêm các thông tin này vào model để hiển thị trên giao diện.
	 */
	@GetMapping("/admin/index")
	public String index(Model model) {
		// Lấy ngày hiện tại
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String[] today = formatter.format(date).toString().split("-");

		// Thống kê số đơn hàng đã thành công trong tháng
		long order = orderService.getStatisticalTotalOrderOnMonth(Integer.parseInt(today[1]), Integer.parseInt(today[0])).getOrderSuccess();

		// Tính tổng doanh thu trong tháng
		double revenue = 0;
		List<StatisticalRevenue> listRevenue = orderService.listStatisticalRevenue(Integer.parseInt(today[1]), Integer.parseInt(today[0]));
		for(StatisticalRevenue item: listRevenue) {
			revenue = revenue + item.getPrice();
		}

		// Đếm số lượng khách hàng
		long customer = userService.findAllUserNotRoleAdmin().size();

		// Đếm số lượng sản phẩm
		long product = productService.findAll().size();

		// Thêm các thông tin vào model để hiển thị trên giao diện
		model.addAttribute("product", product);
		model.addAttribute("customer", customer);
		model.addAttribute("revenue", revenue);
		model.addAttribute("order", order);

		// Trả về tên của view để hiển thị trang chính của trang quản trị
		return Constants.USER_DISPLAY_ADMIN_INDEX;
	}
}
