package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp UserLogin là một model được sử dụng để lưu trữ thông tin đăng nhập của người dùng.
 *
 * Trường dữ liệu trong lớp này bao gồm:
 * - username: Tên người dùng.
 * - password: Mật khẩu người dùng.
 *
 * Lớp này được sử dụng để truyền thông tin đăng nhập giữa các thành phần trong ứng dụng, chẳng hạn như từ giao diện người dùng đến backend.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {
	// Thong tin user name
	private String username;
	// Thong tin password
	private String password;
}
