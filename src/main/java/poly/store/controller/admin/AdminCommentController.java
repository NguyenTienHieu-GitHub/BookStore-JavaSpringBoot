package poly.store.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import poly.store.common.Constants;

@Controller
public class AdminCommentController {

	// Phương thức này xử lý khi người dùng truy cập vào trang danh sách các comment chờ duyệt
	@GetMapping("/admin/comment/list/pending")
	public String listPending(Model model) {
		// Trả về tên của view để hiển thị danh sách các comment chờ duyệt
		return Constants.USER_DISPLAY_ADMIN_COMMENT_PENDING;
	}

	// Phương thức này xử lý khi người dùng truy cập vào trang danh sách các comment đã được duyệt
	@GetMapping("/admin/comment/list/approved")
	public String listApproved(Model model) {
		// Trả về tên của view để hiển thị danh sách các comment đã được duyệt
		return Constants.USER_DISPLAY_ADMIN_COMMENT_APPROVED;
	}
}
