package poly.store.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import poly.store.common.Constants;

@Controller
public class AdminManufacturerController {

	// Phương thức này xử lý khi người dùng truy cập vào trang form để thêm mới hoặc chỉnh sửa nhà sản xuất.
	@GetMapping("/admin/manufactures/form")
	public String form(Model model) {
		// Thêm một thuộc tính vào model để đặt giá trị cho button cập nhật
		// Trong trường hợp này, chúng ta không cho phép cập nhật, nên giá trị là false
		model.addAttribute("enableBtnUpdate", false);
		// Trả về tên của view để hiển thị form thêm mới hoặc chỉnh sửa nhà sản xuất
		return Constants.USER_DISPLAY_ADMIN_MANUFACTURER_FORM;
	}

	// Phương thức này xử lý khi người dùng truy cập vào trang danh sách nhà sản xuất.
	@GetMapping("/admin/manufactures/list")
	public String list(Model model) {
		// Trả về tên của view để hiển thị danh sách nhà sản xuất
		return Constants.USER_DISPLAY_ADMIN_MANUFACTURER_LIST;
	}

	// Phương thức này xử lý khi người dùng truy cập vào trang form để cập nhật thông tin nhà sản xuất có id là {id}.
	@GetMapping("/admin/manufactures/update/{id}")
	public String update(Model model, @PathVariable("id") Integer id) {
		// Thêm một thuộc tính vào model để đặt giá trị cho button cập nhật
		// Trong trường hợp này, chúng ta cho phép cập nhật, nên giá trị là true
		model.addAttribute("manufacturerId", id);
		model.addAttribute("enableBtnUpdate", true);
		// Trả về tên của view để hiển thị form cập nhật thông tin nhà sản xuất
		return Constants.USER_DISPLAY_ADMIN_MANUFACTURER_FORM;
	}
}
