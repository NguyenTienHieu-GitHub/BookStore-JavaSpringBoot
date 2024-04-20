package poly.store.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import poly.store.common.Constants;

@Controller
public class AdminCategoryController {

	// Phương thức này xử lý khi người dùng truy cập vào trang form để tạo mới danh mục
	@GetMapping("/admin/categories/form")
	public String form(Model model) {
		// Thêm một thuộc tính vào model để đặt giá trị cho button cập nhật
		// Trong trường hợp này, chúng ta không cho phép cập nhật, nên giá trị là false
		model.addAttribute("enableBtnUpdate", false);
		// Trả về tên của view để hiển thị form tạo mới danh mục
		return Constants.USER_DISPLAY_ADMIN_CATEGORY_FORM;
	}

	// Phương thức này xử lý khi người dùng truy cập vào trang danh sách danh mục
	@GetMapping("/admin/categories/list")
	public String list(Model model) {
		// Trả về tên của view để hiển thị danh sách danh mục
		return Constants.USER_DISPLAY_ADMIN_CATEGORY_LIST;
	}

	// Phương thức này xử lý khi người dùng truy cập vào trang form để cập nhật danh mục có id là {id}
	@GetMapping("/admin/categories/update/{id}")
	public String update(Model model, @PathVariable("id") Integer id) {
		// Thêm một thuộc tính vào model để đặt giá trị cho button cập nhật
		// Trong trường hợp này, chúng ta cho phép cập nhật, nên giá trị là true
		model.addAttribute("categoryId", id);
		model.addAttribute("enableBtnUpdate", true);
		// Trả về tên của view để hiển thị form cập nhật danh mục
		return Constants.USER_DISPLAY_ADMIN_CATEGORY_FORM;
	}
}
