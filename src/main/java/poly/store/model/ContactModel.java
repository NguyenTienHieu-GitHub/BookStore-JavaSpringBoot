package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Đây là một lớp model để đại diện cho thông tin liên hệ từ người dùng.
 *
 * Các trường dữ liệu trong lớp này bao gồm:
 * - name: Tên của người dùng hoặc người gửi liên hệ.
 * - email: Địa chỉ email của người gửi liên hệ.
 * - content: Nội dung của thông điệp liên hệ.
 *
 * Lớp này được sử dụng để truyền thông tin cần thiết khi người dùng gửi một thông điệp liên hệ từ giao diện người dùng đến tầng xử lý logic của ứng dụng.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactModel {
	private String name;
	private String email;
	private String content;
}
