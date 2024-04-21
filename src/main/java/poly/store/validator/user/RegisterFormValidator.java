/**
 * Validator để kiểm tra tính hợp lệ của dữ liệu nhập vào khi người dùng đăng ký tài khoản.
 */
package poly.store.validator.user;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import poly.store.entity.User;
import poly.store.model.UserRegister;
import poly.store.service.UserService;

@Component
public class RegisterFormValidator implements Validator {

	@Autowired
	UserService userService;

	// Sử dụng thư viện EmailValidator từ Apache Commons để kiểm tra định dạng email
	private EmailValidator emailValidator = EmailValidator.getInstance();

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == UserRegister.class; // Xác định lớp mà validator này hỗ trợ, trong trường hợp này là UserRegister
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserRegister userRegister = (UserRegister) target; // Ép kiểu target sang UserRegister để có thể sử dụng

		// Kiểm tra các trường thông tin đăng ký (fullname, email, password, confirmPassword) có trống không
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "NotBlank.userRegister.fullname");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotBlank.userRegister.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotBlank.userRegister.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotBlank.userRegister.confirmPassword");

		// Kiểm tra tính hợp lệ của định dạng email
		if (!errors.hasFieldErrors("email")) {
			if (!this.emailValidator.isValid(userRegister.getEmail())) {
				errors.rejectValue("email", "NotPattern.userRegister.email");
			} else {
				// Kiểm tra xem email đã tồn tại trong cơ sở dữ liệu chưa
				User user = userService.findUserByEmail(userRegister.getEmail());
				if (user != null) {
					errors.rejectValue("email", "Duplicate.userRegister.email");
				}
			}
		}

		// Kiểm tra độ dài của mật khẩu
		if (!errors.hasFieldErrors("password")) {
			if (userRegister.getPassword().length() < 7) {
				errors.rejectValue("password", "Min.userRegister.password");
			}
			if (userRegister.getPassword().length() > 30) {
				errors.rejectValue("password", "Max.userRegister.password");
			}
		}

		// Kiểm tra xác nhận mật khẩu
		if (!errors.hasFieldErrors("confirmPassword")) {
			if (!userRegister.getPassword().equals(userRegister.getConfirmPassword())) {
				errors.rejectValue("confirmPassword", "NotDuplicate.userRegister.confirmPassword");
			}
		}

	}

}
