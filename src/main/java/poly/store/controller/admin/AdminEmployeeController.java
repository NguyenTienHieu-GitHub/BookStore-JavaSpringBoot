/**
 * Controller này điều khiển các hoạt động liên quan đến quản lý nhân viên trong trang quản trị.
 */
package poly.store.controller.admin;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import poly.store.common.Constants;

@Controller
public class AdminEmployeeController {

	/**
	 * Phương thức này xử lý khi người dùng truy cập vào trang danh sách nhân viên.
	 * Nó cũng truy xuất tên người dùng đang đăng nhập và thêm vào model để hiển thị trên giao diện.
	 */
	@GetMapping("/admin/employees/list")
	public String list(Model model) {
		// Lấy thông tin người dùng hiện tại từ SecurityContextHolder
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// Lấy tên người dùng từ UserDetails
		String username = ((UserDetails) principal).getUsername();
		// Thêm tên người dùng vào model
		model.addAttribute("username", username);
		// Trả về tên của view để hiển thị danh sách nhân viên
		return Constants.USER_DISPLAY_ADMIN_EMPLOYEE_LIST;
	}

	/**
	 * Phương thức này xử lý khi người dùng truy cập vào trang form để thêm mới hoặc chỉnh sửa nhân viên.
	 */
	@GetMapping("/admin/employees/form")
	public String form(Model model) {
		// Thêm một thuộc tính vào model để đặt giá trị cho button cập nhật
		// Trong trường hợp này, chúng ta không cho phép cập nhật, nên giá trị là false
		model.addAttribute("enableBtnUpdate", false);
		// Trả về tên của view để hiển thị form thêm mới nhân viên
		return Constants.USER_DISPLAY_ADMIN_EMPLOYEE_FORM;
	}

	/**
	 * Phương thức này xử lý khi người dùng truy cập vào trang form để cập nhật thông tin nhân viên có id là {id}.
	 */
	@GetMapping("/admin/employees/update/{id}")
	public String update(Model model, @PathVariable("id") Integer id) {
		// Hiển thị id của nhân viên để cập nhật thông tin
		System.out.println(id);
		// Thêm id của nhân viên và thuộc tính enableBtnUpdate vào model
		model.addAttribute("userId", id);
		model.addAttribute("enableBtnUpdate", true);
		// Trả về tên của view để hiển thị form cập nhật thông tin nhân viên
		return Constants.USER_DISPLAY_ADMIN_EMPLOYEE_FORM;
	}
}
