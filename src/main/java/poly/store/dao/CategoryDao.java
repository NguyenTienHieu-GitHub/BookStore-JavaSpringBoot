package poly.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.store.entity.Category;

// Giao diện này mở rộng từ JpaRepository, cung cấp các phương thức để thực hiện các thao tác CRUD trên các đối tượng Category
public interface CategoryDao extends JpaRepository<Category, Integer>{

	// Phương thức truy vấn tùy chỉnh để lấy danh sách các danh mục chưa bị xóa
	@Query("SELECT c FROM Category c WHERE c.Deleteday = null")
	List<Category> getListCategory();

	// Phương thức truy vấn tùy chỉnh để lấy một danh mục bằng tên tìm kiếm
	@Query("SELECT c FROM Category c WHERE c.Deleteday = null AND c.Namesearch LIKE ?1")
	Category getCategoryByNameSearch(String nameSearch);
}
