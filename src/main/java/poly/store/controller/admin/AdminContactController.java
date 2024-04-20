package poly.store.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import poly.store.common.Constants;

@Controller
public class AdminContactController {

	// Phương thức này xử lý khi người dùng truy cập vào trang danh sách các liên hệ chờ duyệt
	@GetMapping("/admin/contact/list/pending")
	public String listPending(Model model) {
		// Trả về tên của view để hiển thị danh sách các liên hệ chờ duyệt
		return Constants.USER_DISPLAY_ADMIN_CONTACT_PENDING;
	}

	// Phương thức này xử lý khi người dùng truy cập vào trang danh sách các liên hệ đã được duyệt
	@GetMapping("/admin/contact/list/approved")
	public String listApproved(Model model) {
		// Trả về tên của view để hiển thị danh sách các liên hệ đã được duyệt
		return Constants.USER_DISPLAY_ADMIN_CONTACT_APPROVED;
	}
}
