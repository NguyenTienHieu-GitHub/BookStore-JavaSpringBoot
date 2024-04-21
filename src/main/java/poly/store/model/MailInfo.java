package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp MailInfo là một model được sử dụng để đại diện cho thông tin của một email, bao gồm các trường thông tin cần thiết như: người gửi, người nhận, tiêu đề, nội dung, và các tệp đính kèm.
 *
 * Các trường dữ liệu trong lớp này bao gồm:
 * - from: Địa chỉ email của người gửi.
 * - to: Địa chỉ email của người nhận.
 * - cc: Mảng chứa các địa chỉ email của các người nhận được sao chép (CC).
 * - bcc: Mảng chứa các địa chỉ email của các người nhận được sao chép ẩn (BCC).
 * - subject: Tiêu đề của email.
 * - body: Nội dung của email.
 * - attachment: Mảng chứa các đường dẫn tới các tệp đính kèm trong email.
 *
 * Lớp này cũng cung cấp một constructor để dễ dàng khởi tạo các thông tin cần thiết cho một email gửi đi, bao gồm người gửi, người nhận, tiêu đề và nội dung.
 *
 * Lớp MailInfo được sử dụng để truyền thông tin về email giữa các thành phần khác nhau trong ứng dụng, như gửi email từ hệ thống.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo {
	String from;
	String to;
	String[] cc;
	String[] bcc;
	String subject;
	String body;
	String[] attachment;
	public MailInfo(String to, String subject, String body) {
		this.from = "BookStore FAHASHA <poly@fpt.edu.vn>";
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
}
