package poly.store.controller.user;

import java.sql.Timestamp;

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
import poly.store.entity.Role;
import poly.store.entity.User;
import poly.store.entity.UserRole;
import poly.store.model.UserRegister;
import poly.store.service.RoleService;
import poly.store.service.SessionService;
import poly.store.service.UserRoleService;
import poly.store.service.UserService;
import poly.store.service.impl.MailerServiceImpl;
import poly.store.validator.user.RegisterFormValidator;

@Controller
public class RegisterController {
	@Autowired
	RegisterFormValidator registerFormValidator; // Validator để kiểm tra định dạng của dữ liệu đăng ký

	@Autowired
	UserService userService; // Service để thao tác với thông tin người dùng

	@Autowired
	SessionService sessionService; // Service để quản lý session của người dùng

	@Autowired
	MailerServiceImpl mailerService; // Service để gửi email

	@Autowired
	RoleService roleService; // Service để quản lý vai trò của người dùng

	@Autowired
	UserRoleService userRoleService; // Service để quản lý mối quan hệ giữa người dùng và vai trò

	// Thiết lập Validator cho đối tượng UserRegister
	@InitBinder
	public void initBinder(WebDataBinder binder) { // Khai báo phương thức initBinder với tham số là WebDataBinder
		Object target = binder.getTarget(); // Lấy đối tượng mục tiêu từ binder
		if (target == null) { // Nếu mục tiêu là null
			return; // Kết thúc phương thức
		}
		if (target.getClass() == UserRegister.class) { // Nếu lớp của mục tiêu là UserRegister
			binder.setValidator(registerFormValidator); // Thiết lập validator cho binder
		}
	}

	// Xử lý yêu cầu GET để hiển thị form đăng ký
	@GetMapping("/register")
	public String displayFormRegister(Model model) { // Phương thức hiển thị form đăng ký, nhận một Model làm tham số
		UserRegister userRegister = new UserRegister(); // Tạo một đối tượng UserRegister mới
		model.addAttribute("userRegister", userRegister); // Thêm đối tượng UserRegister vào model với tên là "userRegister"

		return Constants.USER_DISPLAY_REGISTER; // Trả về view cho form đăng ký
	}

	// Xử lý yêu cầu POST từ form đăng ký
	@PostMapping("/register")
	public String handlerRegisterForm(Model model,
									  @ModelAttribute("userRegister") @Validated UserRegister userRegister,
									  BindingResult result) {
		if (result.hasErrors()) {
			return Constants.USER_DISPLAY_REGISTER; // Nếu có lỗi, hiển thị lại form đăng ký
		} else {
			if (userRegister.isConfirmTerm() == false) {
				model.addAttribute("checkTerm", true);
				return Constants.USER_DISPLAY_REGISTER; // Nếu không đồng ý điều khoản, hiển thị lại form đăng ký
			} else {
				// Tạo mã xác nhận và gửi qua email
				int code = (int) Math.floor(((Math.random() * 899999) + 100000));
				userRegister.setCode(code);
				mailerService.queue(userRegister.getEmail(), "Xác nhận email!", "Code xác nhận của bạn là: " + code);

				sessionService.set("user", userRegister); // Lưu thông tin người dùng vào session
			}
		}
		return Constants.USER_DISPLAY_CONFIRM_CODE; // Chuyển hướng đến trang xác nhận mã
	}

	// Xử lý yêu cầu GET để hiển thị form xác nhận mã
	@GetMapping("/register/confirm-code")
	public String displayFormConfirmMail(Model model) {
		return "redirect:/register"; // Chuyển hướng về trang đăng ký nếu truy cập trực tiếp vào đường dẫn này
	}

	// Xử lý yêu cầu POST từ form xác nhận mã
	@PostMapping("/register/confirm-code")
	public String handlerFormConfirmMail(Model model,
										 @ModelAttribute("userRegister") @Validated UserRegister userRegisterForm, BindingResult result) {
		UserRegister userRegister = sessionService.get("user"); // Lấy thông tin đăng ký từ session
		if (userRegisterForm.getConfirmCode().isEmpty()) { // Nếu mã xác nhận trống
			result.rejectValue("confirmCode", "NotBlank.userRegister.confirmCode"); // Thêm lỗi vào BindingResult
		} else {
			if (!userRegisterForm.getConfirmCode().equals(String.valueOf(userRegister.getCode()))) { // Nếu mã xác nhận không đúng
				result.rejectValue("confirmCode", "NotDuplicate.userRegister.confirmCode"); // Thêm lỗi vào BindingResult
			} else {
				// Tạo mới thông tin người dùng và lưu vào cơ sở dữ liệu
				Timestamp timestamp = new Timestamp(System.currentTimeMillis()); // Lấy thời gian hiện tại
				User user = new User();
				user.setEmail(userRegister.getEmail());
				user.setFullname(userRegister.getFullName());
				user.setPassword(userRegister.getPassword());
				user.setCreateday(timestamp.toString());
				user.setSubscribe(userRegister.getSubscribe());
				userService.save(user);

				// Lấy vai trò mặc định và lưu mối quan hệ người dùng-vai trò vào cơ sở dữ liệu
				Role role = roleService.findRoleById(1); // Tìm vai trò mặc định
				UserRole userRole = new UserRole();
				userRole.setUser(user);
				userRole.setRole(role);
				userRoleService.save(userRole);
				sessionService.remove("user"); // Xóa thông tin người dùng khỏi session

				// Hiển thị thông báo đăng ký thành công
				model.addAttribute("alert", "Đăng ký thành công!");
				model.addAttribute("message", "Chúc mừng bạn đã tạo mới một tài khoản thành công!");
				return Constants.USER_DISPLAY_ALERT_STATUS;
			}
		}

		return Constants.USER_DISPLAY_CONFIRM_CODE; // Hiển thị lại form xác nhận mã nếu có lỗi
	}
}
