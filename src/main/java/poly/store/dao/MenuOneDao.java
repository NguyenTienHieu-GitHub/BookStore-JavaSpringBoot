package poly.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import poly.store.entity.MenuOne;

// Giao diện này mở rộng từ JpaRepository, cung cấp các phương thức để thực hiện các thao tác CRUD trên các đối tượng MenuOne
public interface MenuOneDao extends JpaRepository<MenuOne, Integer>{

	// Truy vấn tùy chỉnh để lấy danh sách các mục MenuOne chưa bị xóa và chưa bị xóa danh mục của chúng
	@Query("SELECT m FROM MenuOne m WHERE m.Deleteday = null and m.category.Deleteday = null")
	List<MenuOne> getListMenuOne();

	// Truy vấn tùy chỉnh để lấy danh sách các mục MenuOne dựa trên ID danh mục
	@Query("SELECT m FROM MenuOne m WHERE m.Deleteday = null and m.category.id = :uid")
	List<MenuOne> getListCategoryById(@Param("uid") int categoryId);
}
