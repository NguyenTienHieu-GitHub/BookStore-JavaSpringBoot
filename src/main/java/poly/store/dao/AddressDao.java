package poly.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.store.entity.Address;

// Giao diện này mở rộng từ JpaRepository, cung cấp các phương thức để thực hiện các thao tác CRUD trên các đối tượng Address
public interface AddressDao extends JpaRepository<Address, Integer>{

	// Phương thức truy vấn tùy chỉnh để tìm danh sách địa chỉ dựa trên email của người dùng
	@Query("SELECT a FROM Address a WHERE a.user.email = ?1")
	List<Address> findListAddressByEmail(String email);

	// Phương thức truy vấn tùy chỉnh để tìm một địa chỉ dựa trên email của người dùng và id của địa chỉ
	@Query("SELECT a FROM Address a WHERE a.user.email = ?1 and a.id = ?2")
	Address findAddressById(String email, int id);
}
