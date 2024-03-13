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
	
	@Override
	public ContactModel createContact(ContactModel contactModel) {

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(date);

		Contact contact = new Contact();
		contact.setName(contactModel.getName());
		contact.setEmail(contactModel.getEmail());
		contact.setContent(contactModel.getContent());
		String name = contactModel.getName();
		contact.setDate(strDate);
		contact.setStatus("0");
		dao.save(contact);
//		send mail thanks guests
		mailerService.queue(contactModel.getEmail(), "Thông Báo Fahasa.com", 
				"Kính chào " + name +",<br>"
				+ "Đại diện Fahasa shop xin chân thành cảm ơn bạn đã ghé qua và để lại đánh giá ý kiến cá nhân về shop. "
				+ "Ý kiến đóng góp của bạn shop sẽ ghi nhận để góp phần phát triển shop hơn.<br>"
				+ "<br><br>"
				+ "Trân trọng,<br>"
				+ "FASAHA SHOP");
		return contactModel;
	}

	@Override
	public List<Contact> getListContactPending() {
		// TODO Auto-generated method stub
		return dao.getListContactPending();
	}

	@Override
	public Contact getContactByContactId(Integer id) {
		// TODO Auto-generated method stub
		return dao.getContactByContactId(id);
	}

	@Override
	public void approveContact(Integer id) {
		Contact contact = dao.findById(id).get();
		contact.setStatus("1");
		dao.save(contact);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		Contact contact = dao.findById(id).get();
		dao.delete(contact);
	}

	@Override
	public List<Contact> getListContactChecked() {
		// TODO Auto-generated method stub
		return dao.getListContactChecked();
	}

	@Override
	public List<Contact> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	
}
