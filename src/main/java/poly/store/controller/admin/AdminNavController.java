package poly.store.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import poly.store.common.Constants;

@Controller
public class AdminNavController {

	// Phương thức này xử lý khi người dùng truy cập vào trang form để thêm mới hoặc chỉnh sửa mục nav1.
	@GetMapping("/admin/nav1/form")
	public String form(Model model) {
		// Thêm một thuộc tính vào model để đặt giá trị cho button cập nhật
		// Trong trường hợp này, chúng ta không cho phép cập nhật, nên giá trị là false
		model.addAttribute("enableBtnUpdate", false);
		// Trả về tên của view để hiển thị form thêm mới hoặc chỉnh sửa mục nav1
		return Constants.USER_DISPLAY_ADMIN_NAV_FORM;
	}

	// Phương thức này xử lý khi người dùng truy cập vào trang danh sách mục nav1.
	@GetMapping("/admin/nav1/list")
	public String list(Model model) {
		// Trả về tên của view để hiển thị danh sách mục nav1
		return Constants.USER_DISPLAY_ADMIN_NAV_LIST;
	}

	// Phương thức này xử lý khi người dùng truy cập vào trang form để cập nhật thông tin mục nav1 có id là {id}.
	@GetMapping("/admin/nav1/update/{id}")
	public String update(Model model, @PathVariable("id") Integer id) {
		// Thêm một thuộc tính vào model để đặt giá trị cho button cập nhật
		// Trong trường hợp này, chúng ta cho phép cập nhật, nên giá trị là true
		model.addAttribute("nav1Id", id);
		model.addAttribute("enableBtnUpdate", true);
		// Trả về tên của view để hiển thị form cập nhật thông tin mục nav1
		return Constants.USER_DISPLAY_ADMIN_NAV_FORM;
	}
}
