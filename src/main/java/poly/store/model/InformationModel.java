package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp InformationModel được sử dụng để đại diện cho thông tin cá nhân của người dùng trong hệ thống.
 *
 * Các trường dữ liệu trong lớp này bao gồm:
 * - fullName: Họ và tên đầy đủ của người dùng.
 * - email: Địa chỉ email của người dùng.
 * - password: Mật khẩu của người dùng.
 * - birthday: Ngày sinh của người dùng.
 * - gender: Giới tính của người dùng (có thể là giá trị số, chẳng hạn 0 đại diện cho nam và 1 đại diện cho nữ).
 * - news: Cài đặt nhận thông báo tin tức, có thể là 0 (không nhận) hoặc 1 (nhận).
 *
 * Lớp này được sử dụng để truyền thông tin cá nhân của người dùng giữa các thành phần khác nhau trong ứng dụng, như giao diện và lớp xử lý dữ liệu.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InformationModel {
	private String fullName;
	private String email;
	private String password;
	private String birthday;
	private int gender;
	private int news;
}
