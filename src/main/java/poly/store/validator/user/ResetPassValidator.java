/**
 * Validator để kiểm tra tính hợp lệ của dữ liệu nhập vào khi người dùng thực hiện reset mật khẩu.
 */
package poly.store.validator.user;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import poly.store.model.ResetPassword;

@Component
public class ResetPassValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == ResetPassword.class; // Xác định lớp mà validator này hỗ trợ, trong trường hợp này là ResetPassword
	}

	@Override
	public void validate(Object target, Errors errors) {
		ResetPassword userRegister = (ResetPassword) target; // Ép kiểu target sang ResetPassword để có thể sử dụng

		// Kiểm tra các trường password và confirmPassword có trống không
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotBlank.userRegister.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotBlank.userRegister.confirmPassword");

		// Kiểm tra xác nhận mật khẩu
		if(!errors.hasFieldErrors()) {
			if(!userRegister.getConfirmPassword().equals(userRegister.getPassword())) {
				errors.rejectValue("confirmPassword", "NotDuplicate.userRegister.confirmPassword");
			}
		}

		// Kiểm tra độ dài của mật khẩu
		if(!errors.hasFieldErrors("password")) {
			if(userRegister.getPassword().length() < 7) {
				errors.rejectValue("password", "Min.userRegister.password");
			}
			if(userRegister.getPassword().length() > 15) {
				errors.rejectValue("password", "Max.userRegister.password");
			}
		}
	}

}
