package poly.store.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.store.dao.ContactDao;
import poly.store.dao.UserDao;
import poly.store.entity.Contact;
import poly.store.model.ContactModel;
import poly.store.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	ContactDao dao;

	@Autowired
	UserDao userDao;

	@Autowired
	MailerServiceImpl mailerService;

	// Phương thức tạo mới một liên hệ
	@Override
	public ContactModel createContact(ContactModel contactModel) {
		// Lấy ngày hiện tại và định dạng thành chuỗi "yyyy-MM-dd"
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(date);

		// Tạo một đối tượng Contact từ dữ liệu trong ContactModel
		Contact contact = new Contact();
		contact.setName(contactModel.getName());
		contact.setEmail(contactModel.getEmail());
		contact.setContent(contactModel.getContent());
		String name = contactModel.getName();
		contact.setDate(strDate);
		contact.setStatus("0"); // Đánh dấu liên hệ chưa được duyệt
		dao.save(contact);

		// Gửi email cảm ơn cho khách hàng
		mailerService.queue(contactModel.getEmail(), "Thông Báo BookStore Fahasha",
				"Kính chào " + name +",<br>"
						+ "Đại diện Fahasa shop xin chân thành cảm ơn bạn đã ghé qua và để lại đánh giá ý kiến cá nhân về shop. "
						+ "Ý kiến đóng góp của bạn shop sẽ ghi nhận để góp phần phát triển shop hơn.<br>"
						+ "<br><br>"
						+ "Trân trọng,<br>"
						+ "BOOKSTORE FASAHA");

		return contactModel;
	}

	// Phương thức lấy danh sách các liên hệ chưa được duyệt
	@Override
	public List<Contact> getListContactPending() {
		return dao.getListContactPending();
	}

	// Phương thức lấy thông tin liên hệ dựa trên ID liên hệ
	@Override
	public Contact getContactByContactId(Integer id) {
		return dao.getContactByContactId(id);
	}

	// Phương thức duyệt một liên hệ
	@Override
	public void approveContact(Integer id) {
		// Tìm liên hệ dựa trên ID và đổi trạng thái sang "1" để đánh dấu đã duyệt
		Contact contact = dao.findById(id).get();
		contact.setStatus("1");
		dao.save(contact);
	}

	// Phương thức xóa một liên hệ
	@Override
	public void delete(Integer id) {
		// Xóa liên hệ dựa trên ID
		Contact contact = dao.findById(id).get();
		dao.delete(contact);
	}

	// Phương thức lấy danh sách các liên hệ đã được duyệt
	@Override
	public List<Contact> getListContactChecked() {
		return dao.getListContactChecked();
	}

	// Phương thức lấy tất cả các liên hệ
	@Override
	public List<Contact> findAll() {
		return dao.findAll();
	}

}
