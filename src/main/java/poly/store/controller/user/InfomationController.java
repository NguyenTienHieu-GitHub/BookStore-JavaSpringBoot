package poly.store.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import poly.store.common.Constants;
@Controller
public class InfomationController {
	// Phương thức xử lý yêu cầu trang "about us"
	@GetMapping("/aboutUs")
	public String AboutUS() {
		return Constants.USER_DISPLAY_IMFORMATION_ABOUT_US; // Trả về view hiển thị thông tin về chúng tôi
	}

	// Phương thức xử lý yêu cầu trang "delivery"
	@GetMapping("/delivery")
	public String Delivery() {
		return Constants.USER_DISPLAY_IMFORMATION_DELIVERY; // Trả về view hiển thị thông tin về giao hàng
	}

	// Phương thức xử lý yêu cầu trang "policy"
	@GetMapping("/policy")
	public String Policy() {
		return Constants.USER_DISPLAY_IMFORMATION_POLICY; // Trả về view hiển thị chính sách
	}

	// Phương thức xử lý yêu cầu trang "termCondition"
	@GetMapping("/termCondition")
	public String TermCondition() {
		return Constants.USER_DISPLAY_IMFORMATION_TERM_CONDITION; // Trả về view hiển thị điều khoản và điều kiện
	}
}

