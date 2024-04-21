/**
 * @(#)UserRegister.java.
 *
 * Version 1.00.
 */
package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp UserRegister là một model được sử dụng để lưu trữ thông tin đăng ký của người dùng.
 *
 * Trường dữ liệu trong lớp này bao gồm:
 * - email: Địa chỉ email của người dùng.
 * - fullName: Họ và tên của người dùng.
 * - password: Mật khẩu của người dùng.
 * - confirmPassword: Xác nhận mật khẩu của người dùng.
 * - code: Mã xác nhận được gửi đến email của người dùng để xác thực đăng ký.
 * - confirmCode: Mã xác nhận được nhập bởi người dùng để hoàn thành quá trình đăng ký.
 * - subscribe: Thông báo cho người dùng có đăng ký nhận thông báo hay không.
 * - confirmTerm: Xác nhận điều khoản sử dụng từ phía người dùng.
 *
 * Lớp này được sử dụng để truyền thông tin đăng ký từ giao diện người dùng đến backend để xử lý quá trình đăng ký người dùng mới.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegister {
	// Thong tin email
	private String email;

	// Thong tin ho va ten
	private String fullName;

	// Thong tin mat khau
	private String password;

	// Thong tin xac nhan mat khau
	private String confirmPassword;

	// Thong tin ma xac nhan
	private int code;

	// Thong tin xac nhan ma
	private String confirmCode;
	
	// Thong tin nhan thong bao
	private int subscribe;
	
	// Thong tin xac nhan dieu khoan
	private boolean confirmTerm;
}
