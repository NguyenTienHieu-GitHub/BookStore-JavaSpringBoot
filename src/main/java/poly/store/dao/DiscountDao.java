package poly.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.store.entity.Discount;

// Giao diện này mở rộng từ JpaRepository, cung cấp các phương thức để thực hiện các thao tác CRUD trên các đối tượng Discount
public interface DiscountDao extends JpaRepository<Discount, Integer>{

	// Phương thức truy vấn tùy chỉnh để lấy danh sách các mã giảm giá chưa bị xóa
	@Query("SELECT d FROM Discount d WHERE d.Deleteday = null")
	List<Discount> getListDiscount();

	// Phương thức truy vấn tùy chỉnh để lấy mã giảm giá dựa trên mã code và thời điểm hiện tại
	@Query(value="Select * From Discount Where GETDATE() >= ApplyDay and GETDATE() <= Expiration and DeleteDay is null and Code LIKE ?1", nativeQuery = true)
	Discount getDiscountByCode(String code);

	// Phương thức truy vấn tùy chỉnh để lấy danh sách các mã giảm giá có sẵn và có thể áp dụng
	@Query(value="Select * From Discount Where GETDATE() >= ApplyDay and GETDATE() <= Expiration and DeleteDay is null", nativeQuery = true)
	List<Discount> getListDiscountAvailable();
}
