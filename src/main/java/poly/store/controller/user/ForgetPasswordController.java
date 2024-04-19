package poly.store.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import poly.store.common.Constants;
import poly.store.entity.User;
import poly.store.model.UserRegister;
import poly.store.service.UserService;
import poly.store.service.impl.MailerServiceImpl;

@Controller
public class ForgetPasswordController {

	@Autowired
	UserService userService; // Dịch vụ quản lý người dùng

	@Autowired
	MailerServiceImpl mailerService; // Dịch vụ gửi email

	@Autowired
	PasswordEncoder pe; // Đối tượng mã hóa mật khẩu

	// Hiển thị form quên mật khẩu
	@GetMapping("/forget-password")
	public String displayFormForgetPassword(Model model) {
		UserRegister userForm = new UserRegister();
		model.addAttribute("userForm", userForm); // Truyền đối tượng UserRegister vào model để binding dữ liệu
		return Constants.USER_DISPLAY_FORGET_PASSWORD; // Trả về trang hiển thị form quên mật khẩu
	}

	// Xử lý form quên mật khẩu
	@PostMapping("/forget-password")
	public String handlerFormForgetPassword(Model model, @ModelAttribute("userForm") @Validated UserRegister userForm,
											BindingResult result) {
		if (userForm.getEmail().isEmpty()) { // Nếu email rỗng
			result.rejectValue("email", "NotBlank.userRegister.email"); // Thêm lỗi vào BindingResult
		} else {
			User user = userService.findUserByEmail(userForm.getEmail()); // Tìm kiếm người dùng bằng email
			if (user == null) { // Nếu không tìm thấy người dùng
				result.rejectValue("email", "NotExist.userLogin.username"); // Thêm lỗi vào BindingResult
			}
			else {
				String password = pe.encode(user.getPassword()); // Mã hóa mật khẩu của người dùng
				// Gửi email chứa link reset mật khẩu
				mailerService.queue(userForm.getEmail(), "Làm mới mật khẩu!", "Vui lòng click vào link này: "+ "http://localhost:8080/reset-password?code="+password+"&email="+user.getEmail() +" để reset mật khẩu.");
			}
		}

		if (result.hasErrors()) { // Nếu có lỗi trong quá trình xử lý form
			return Constants.USER_DISPLAY_FORGET_PASSWORD; // Trả về trang hiển thị form quên mật khẩu
		}

		model.addAttribute("alert", "Thông báo!"); // Truyền thông tin alert vào model
		model.addAttribute("message", "Vui lòng kiểm tra email để thay đổi mật khẩu!"); // Truyền thông điệp vào model
		return Constants.USER_DISPLAY_ALERT_STATUS; // Trả về trang hiển thị thông báo thành công
	}
}
