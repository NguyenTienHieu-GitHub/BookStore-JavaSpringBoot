package poly.store.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.store.entity.Blog;

// Giao diện này mở rộng từ JpaRepository, cung cấp các phương thức để thực hiện các thao tác CRUD trên các đối tượng Blog
public interface BlogDao extends JpaRepository<Blog, Integer> {

	// Phương thức truy vấn tùy chỉnh để lấy danh sách các bài đăng Blog chưa bị xóa
	@Query("SELECT c FROM Blog c WHERE c.Deleteday = null")
	List<Blog> getListBlog();

	// Phương thức truy vấn tùy chỉnh để lấy trang các bài đăng Blog đang hoạt động, sử dụng phân trang
	@Query("SELECT c FROM Blog c WHERE c.Deleteday = null AND c.active = 1")
	Page<Blog> findAllBlogActive(Pageable pageable);

	// Phương thức truy vấn tùy chỉnh để tìm bài đăng Blog bằng tên tìm kiếm
	@Query("SELECT c FROM Blog c WHERE c.Namesearch = ?1")
	Blog findBlogByNameSearch(String nameSearch);

	// Phương thức truy vấn tùy chỉnh sử dụng truy vấn SQL Native để lấy 6 bài đăng Blog đang hoạt động, sắp xếp theo thứ tự giảm dần của ngày đăng
	@Query(value="select TOP(6) * from Blogs Where Active = 1 AND DeleteDay is null order by UploadDay DESC", nativeQuery = true)
	List<Blog> getSixBlogs();
}
