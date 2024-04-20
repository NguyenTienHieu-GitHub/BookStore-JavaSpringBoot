package poly.store.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import poly.store.common.Constants;

@Controller
public class AdminDiscountController {

	// Phương thức này xử lý khi người dùng truy cập vào trang form để tạo mới một khuyến mãi
	@GetMapping("/admin/discount/form")
	public String form(Model model) {
		// Thêm một thuộc tính vào model để đặt giá trị cho button cập nhật
		// Trong trường hợp này, chúng ta không cho phép cập nhật, nên giá trị là false
		model.addAttribute("enableBtnUpdate", false);
		// Trả về tên của view để hiển thị form tạo mới một khuyến mãi
		return Constants.USER_DISPLAY_ADMIN_DISCOUNT_FORM;
	}

	// Phương thức này xử lý khi người dùng truy cập vào trang danh sách các khuyến mãi
	@GetMapping("/admin/discount/list")
	public String list(Model model) {
		// Trả về tên của view để hiển thị danh sách các khuyến mãi
		return Constants.USER_DISPLAY_ADMIN_DISCOUNT_LIST;
	}

	// Phương thức này xử lý khi người dùng truy cập vào trang form để cập nhật khuyến mãi có id là {id}
	@GetMapping("/admin/discount/update/{id}")
	public String update(Model model, @PathVariable("id") Integer id) {
		// Thêm một thuộc tính vào model để đặt giá trị cho button cập nhật
		// Trong trường hợp này, chúng ta cho phép cập nhật, nên giá trị là true
		model.addAttribute("discountId", id);
		model.addAttribute("enableBtnUpdate", true);
		// Trả về tên của view để hiển thị form cập nhật khuyến mãi
		return Constants.USER_DISPLAY_ADMIN_DISCOUNT_FORM;
	}

	// Phương thức này xử lý khi người dùng truy cập vào trang danh sách người dùng được áp dụng khuyến mãi
	@GetMapping("/admin/discount/user/list")
	public String userList(Model model) {
		// Trả về tên của view để hiển thị danh sách người dùng được áp dụng khuyến mãi
		return Constants.USER_DISPLAY_ADMIN_DISCOUNT_USER_LIST;
	}

}
