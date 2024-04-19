package poly.store.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import poly.store.common.Constants;
import poly.store.model.UserLogin;
import poly.store.service.UserService;
import poly.store.validator.user.LoginFormValidator;

@Controller
public class LoginController {

	@Autowired
	LoginFormValidator loginFormValidator;

	@Autowired
	UserService userService;

	// Phương thức initBinder được gọi trước mỗi request để cấu hình WebDataBinder
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Lấy đối tượng target (đối tượng được binding)
		Object target = binder.getTarget();
		if (target == null) {
			return;
		}
		// Nếu đối tượng là UserLogin, thiết lập trình kiểm tra lỗi
		if (target.getClass() == UserLogin.class) {
			binder.setValidator(loginFormValidator);
		}
	}

	// Xử lý request GET đến "/login"
	@GetMapping("/login")
	public String login(Model model) {
		// Khởi tạo đối tượng UserLogin và thêm vào model để binding với form
		UserLogin userLogin = new UserLogin();
		model.addAttribute("userLogin", userLogin);

		// Trả về trang đăng nhập
		return Constants.USER_DISPLAY_LOGIN;
	}

	// Xử lý request POST đến "/login"
	@PostMapping("/login")
	public String handlerLoginForm(Model model, @ModelAttribute("userLogin") @Validated UserLogin userLogin,
								   BindingResult result) {
		// Kiểm tra tính hợp lệ của dữ liệu nhập vào
		if (result.hasErrors()) {
			// Nếu có lỗi, quay lại trang đăng nhập để hiển thị lỗi
			return Constants.USER_DISPLAY_LOGIN;
		}

		// Nếu không có lỗi, chuyển hướng đến trang chính của ứng dụng
		return Constants.USER_DISPLAY_INDEX;
	}

	// Xử lý request GET đến "/login/success"
	@GetMapping("/login/success")
	public String loginSuccess(Model model, HttpServletRequest request) {
		// Chuyển hướng người dùng đến trang chính của ứng dụng
		return "redirect:/index";
	}
}
