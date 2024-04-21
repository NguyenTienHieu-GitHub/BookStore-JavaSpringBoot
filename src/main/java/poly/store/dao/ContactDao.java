package poly.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import poly.store.entity.Contact;

// Giao diện này mở rộng từ JpaRepository, cung cấp các phương thức để thực hiện các thao tác CRUD trên các đối tượng Contact
public interface ContactDao extends JpaRepository<Contact, Integer> {

	//	@Query("SELECT c FROM C c WHERE c.status = 1 AND c.product.id = :uid")
	//	List<Comment> getListCommentByProductId(@Param("uid") Integer id);

	// Phương thức truy vấn tùy chỉnh để lấy danh sách các liên hệ chưa kiểm tra
	@Query("SELECT c FROM Contact c WHERE c.status = 0")
	List<Contact> getListContactPending();

	// Phương thức truy vấn tùy chỉnh để lấy danh sách các liên hệ đã kiểm tra
	@Query("SELECT c FROM Contact c WHERE c.status = 1")
	List<Contact> getListContactChecked();

	// Phương thức truy vấn tùy chỉnh để lấy một liên hệ dựa trên ID liên hệ
	@Query("SELECT c FROM Contact c WHERE c.id = :uid")
	Contact getContactByContactId(@Param("uid") Integer id);
}
