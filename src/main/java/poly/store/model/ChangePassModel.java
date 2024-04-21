package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Đây là một lớp model để đại diện cho thông tin cần thiết khi người dùng muốn thay đổi mật khẩu.
 *
 * Các trường dữ liệu trong lớp này bao gồm:
 * - oldPass: Mật khẩu cũ của người dùng.
 * - newPass: Mật khẩu mới mà người dùng muốn thay đổi thành.
 * - confirmPass: Xác nhận lại mật khẩu mới của người dùng.
 *
 * Lớp này được sử dụng để truyền thông tin cần thiết khi người dùng thực hiện thao tác thay đổi mật khẩu từ giao diện người dùng đến tầng xử lý logic của ứng dụng.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePassModel {
	private String oldPass;
	private String newPass;
	private String confirmPass;
}
