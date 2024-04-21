package poly.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.store.entity.Manufacturer;

// Giao diện này mở rộng từ JpaRepository, cung cấp các phương thức để thực hiện các thao tác CRUD trên các đối tượng Manufacturer
public interface ManufacturerDao extends JpaRepository<Manufacturer, Integer>{

	// Truy vấn tùy chỉnh để lấy danh sách các nhà sản xuất chưa bị xóa
	@Query("SELECT m FROM Manufacturer m WHERE m.Deleteday = null")
	List<Manufacturer> getListManufacturer();
}
