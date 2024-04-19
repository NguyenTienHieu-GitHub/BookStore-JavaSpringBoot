// Khai báo package cho controller
package poly.store.controller.user;

// Import các thư viện và annotation từ Spring Framework
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Import các class khác trong dự án
import poly.store.common.Constants;
import poly.store.entity.User;
import poly.store.model.ResetPassword;
import poly.store.service.UserService;
import poly.store.validator.user.ResetPassValidator;

// Đánh dấu đây là một Spring MVC Controller
@Controller
public class ResetPasswordController {

	// Tự động inject validator để kiểm tra các trường trong form
	@Autowired
	ResetPassValidator resetPassValidator;

	// Tự động inject UserService để thực hiện các thao tác liên quan đến người dùng
	@Autowired
	UserService userService;

	// Tự động inject PasswordEncoder để mã hóa và giải mã mật khẩu
	@Autowired
	PasswordEncoder passwordEncoder;

	// Phương thức được gắn với InitBinder để đăng ký validator cho ResetPassword class
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		Object target = binder.getTarget();
		if (target == null) {
			return;
		}
		if (target.getClass() == ResetPassword.class) {
			binder.setValidator(resetPassValidator);
		}
	}

	// Xử lý GET request đến /reset-password
	@GetMapping("/reset-password")
	public String displayFormResetPassword(Model model, @RequestParam(value = "code", required = true) String code,
										   @RequestParam(value = "email", required = true) String email) {
		// Tạo một đối tượng ResetPassword mới để binding với form
		ResetPassword userForm = new ResetPassword();
		model.addAttribute("userForm", userForm);
		// Tìm người dùng theo email được cung cấp từ request
		User user = userService.findUserByEmail(email);
		// Nếu không tìm thấy người dùng, chuyển hướng đến trang lỗi 404
		if (user == null) {
			return "redirect:/404page";
		} else {
			// Kiểm tra xem mã code có khớp với mật khẩu đã lưu của người dùng không
			if (passwordEncoder.matches(user.getPassword(), code) == false) {
				return "redirect:/404page";
			}
		}
		// Trả về view để reset mật khẩu
		return Constants.USER_DISPLAY_RESET_PASSWORD;
	}

	// Xử lý POST request đến /reset-password
	@PostMapping("/reset-password")
	public String handlerFormResetPassword(Model model, @RequestParam(value = "code", required = true) String code,
										   @RequestParam(value = "email", required = true) String email,
										   @ModelAttribute("userForm") @Validated ResetPassword userForm, BindingResult result) {
		// Kiểm tra nếu có lỗi trong quá trình binding form
		if (result.hasErrors()) {
			return Constants.USER_DISPLAY_RESET_PASSWORD;
		} else {
			// Tìm người dùng theo email được cung cấp từ request
			User user = userService.findUserByEmail(email);
			// Nếu không tìm thấy người dùng, chuyển hướng đến trang lỗi 404
			if (user == null) {
				return "redirect:/404page";
			} else {
				// Kiểm tra xem mã code có khớp với mật khẩu đã lưu của người dùng không
				if (passwordEncoder.matches(user.getPassword(), code)) {
					// Cập nhật mật khẩu mới cho người dùng và lưu vào cơ sở dữ liệu
					user.setPassword(userForm.getPassword());
					userService.save(user);
					// Hiển thị thông báo thành công
					model.addAttribute("alert", "Chúc mừng!");
					model.addAttribute("message", "Cập nhật tài khoản thành công!");
					return Constants.USER_DISPLAY_ALERT_STATUS;
				} else {
					// Nếu mã code không khớp, chuyển hướng đến trang lỗi 404
					return "redirect:/404page";
				}
			}
		}
	}
}
