package poly.store.validator.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import poly.store.entity.User;
import poly.store.model.UserLogin;
import poly.store.service.UserService;

@Component
public class LoginFormValidator implements Validator {

	@Autowired
	UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == UserLogin.class; // Xác định lớp mà validator này hỗ trợ, trong trường hợp này là UserLogin
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserLogin userLogin = (UserLogin) target; // Ép kiểu target sang UserLogin để có thể sử dụng

		// Kiểm tra nếu username hoặc password trống thì thêm lỗi vào errors
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotBlank.userLogin.username");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotBlank.userLogin.password");

		if (!errors.hasFieldErrors()) { // Nếu không có lỗi từ các kiểm tra trên
			User user = userService.findUserByEmail(userLogin.getUsername()); // Tìm người dùng dựa trên username nhập vào

			if (user == null) { // Nếu không tìm thấy người dùng
				errors.rejectValue("username", "NotExist.userLogin.username"); // Thêm lỗi vào errors cho username

				errors.rejectValue("password", "NotExist.userLogin.password"); // Thêm lỗi vào errors cho password
			} else { // Nếu tìm thấy người dùng
				if (!userLogin.getPassword().equals(user.getPassword())) { // So sánh password nhập vào với password trong database
					errors.rejectValue("password", "NotExist.userLogin.password"); // Nếu không khớp, thêm lỗi vào errors cho password
				}
			}
		}
	}
}
