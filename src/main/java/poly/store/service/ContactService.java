package poly.store.service;

import java.util.List;

import poly.store.entity.Contact;
import poly.store.model.ContactModel;

public interface ContactService {

	// Tạo mới một liên hệ từ một đối tượng ContactModel
	ContactModel createContact(ContactModel contactModel);

	// Lấy danh sách các liên hệ chưa được duyệt
	List<Contact> getListContactPending();

	// Lấy thông tin của một liên hệ dựa trên ID liên hệ
	Contact getContactByContactId(Integer id);

	// Duyệt một liên hệ dựa trên ID liên hệ
	void approveContact(Integer id);

	// Xóa một liên hệ dựa trên ID liên hệ
	void delete(Integer id);

	// Lấy danh sách các liên hệ đã được kiểm tra
	List<Contact> getListContactChecked();

	// Lấy danh sách tất cả các liên hệ
	List<Contact> findAll();
}
