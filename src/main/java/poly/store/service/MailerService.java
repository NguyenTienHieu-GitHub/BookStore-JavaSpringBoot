package poly.store.service;

import javax.mail.MessagingException;

import poly.store.model.MailInfo;

public interface MailerService {

	// Gửi email từ đối tượng MailInfo đã được cung cấp
	void send(MailInfo mail) throws MessagingException;

	// Gửi email đến một địa chỉ cụ thể với tiêu đề và nội dung đã được cung cấp
	void send(String to, String subject, String body) throws MessagingException;

	// Đưa email vào hàng đợi để gửi sau
	void queue(MailInfo mail);

	// Đưa email vào hàng đợi để gửi sau đến một địa chỉ cụ thể với tiêu đề và nội dung đã được cung cấp
	void queue(String to, String subject, String body);
}
