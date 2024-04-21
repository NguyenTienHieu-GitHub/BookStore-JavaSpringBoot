package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp ResetPassword là một model được sử dụng để đại diện cho thông tin của quá trình đặt lại mật khẩu của người dùng.
 *
 * Các trường dữ liệu trong lớp này bao gồm:
 * - password: Mật khẩu mới.
 * - confirmPassword: Xác nhận lại mật khẩu mới.
 *
 * Lớp này được sử dụng để truyền thông tin về quá trình đặt lại mật khẩu giữa các thành phần khác nhau trong ứng dụng.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPassword {
	// Thong tin mat khau
	private String password;
	// Thong tin xac nhan mat khau
	private String confirmPassword;
}
