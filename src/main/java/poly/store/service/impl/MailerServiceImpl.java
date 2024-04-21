package poly.store.service.impl;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import poly.store.model.MailInfo;
import poly.store.service.MailerService;

@Service
public class MailerServiceImpl implements MailerService {
	@Autowired
	JavaMailSender sender;

	// Danh sách chứa các thông tin về email chờ gửi đi
	List<MailInfo> list = new ArrayList<>();

	// Phương thức gửi email thông qua đối tượng JavaMailSender
	@Override
	public void send(MailInfo mail) throws MessagingException {
		// Tạo đối tượng MimeMessage
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		// Thiết lập thông tin email
		helper.setFrom(mail.getFrom());
		helper.setTo(mail.getTo());
		helper.setSubject(mail.getSubject());
		helper.setText(mail.getBody(),true);
		helper.setReplyTo(mail.getFrom());

		// Thiết lập danh sách cc (nếu có)
		String[] cc = mail.getCc();
		if(cc!=null && cc.length > 0) {
			helper.setCc(cc);
		}

		// Thiết lập danh sách bcc (nếu có)
		String[] bcc = mail.getBcc();
		if(bcc!=null && bcc.length > 0) {
			helper.setBcc(bcc);
		}

		// Đính kèm các tệp (nếu có)
		String[] attachments = mail.getAttachment();
		if(attachments!=null && attachments.length>0) {
			for(String path: attachments) {
				File file = new File(path);
				helper.addAttachment(file.getName(), file);
			}
		}

		// Gửi email
		sender.send(message);
	}

	// Phương thức gửi email (rút gọn)
	@Override
	public void send(String to, String subject, String body) throws MessagingException {
		this.send(new MailInfo(to, subject, body));
	}

	// Thêm thông tin email vào danh sách chờ
	@Override
	public void queue(MailInfo mail) {
		list.add(mail);
	}

	// Thêm thông tin email vào danh sách chờ (rút gọn)
	@Override
	public void queue(String to, String subject, String body) {
		queue(new MailInfo(to, subject, body));
	}

	// Phương thức được gọi theo lịch trình để gửi email từ danh sách chờ
	@Scheduled(fixedDelay = 1000)
	public void run() {
		while(!list.isEmpty()) {
			MailInfo mail = list.remove(0);
			try {
				this.send(mail);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
