package poly.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.store.entity.MenuTwo;

// Giao diện này mở rộng từ JpaRepository, cung cấp các phương thức để thực hiện các thao tác CRUD trên các đối tượng MenuTwo
public interface MenuTwoDao extends JpaRepository<MenuTwo, Integer>{

	// Truy vấn tùy chỉnh để lấy danh sách các mục MenuTwo chưa bị xóa và chưa bị xóa MenuOne cũng như danh mục của chúng
	@Query("SELECT m FROM MenuTwo m WHERE m.Deleteday = null and m.menuOne.Deleteday = null and m.menuOne.category.Deleteday = null")
	List<MenuTwo> getListMenuTwo();
}
