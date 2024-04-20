package poly.store.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import poly.store.common.Constants;

@Controller
public class AdminExtendController {

	// Phương thức này xử lý khi người dùng truy cập vào trang mở rộng để quản lý các đặc tính (specification).
	@GetMapping("/admin/extend/specification")
	public String specification(Model model) {
		// Trả về tên của view để hiển thị trang quản lý đặc tính
		return Constants.USER_DISPLAY_ADMIN_EXTEND_SPECIFICATION;
	}

	// Phương thức này xử lý khi người dùng truy cập vào trang mở rộng để cắt ảnh (crop image).
	@GetMapping("/admin/extend/crop-image")
	public String cropImage(Model model) {
		// Trả về tên của view để hiển thị trang cắt ảnh
		return Constants.USER_DISPLAY_ADMIN_EXTEND_CROP_IMAGE;
	}
}
