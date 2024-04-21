package poly.store.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import poly.store.entity.Blog;
import poly.store.model.BlogModel;

public interface BlogService {

	// Tạo mới một bài viết từ một đối tượng BlogModel
	BlogModel createBlog(BlogModel blogModel);

	// Lấy danh sách tất cả các bài viết
	List<Blog> findAll();

	// Xóa một bài viết dựa trên ID
	void delete(Integer id);

	// Lấy thông tin một bài viết dựa trên ID và chuyển đổi sang đối tượng BlogModel
	BlogModel getOneBlogById(Integer id);

	// Cập nhật thông tin về danh mục của một bài viết từ một đối tượng BlogModel
	BlogModel updateCategory(BlogModel blogModel);

	// Tìm kiếm một bài viết dựa trên ID
	Blog findById(Integer id);

	// Lấy danh sách các bài viết có sẵn trong trang và phân trang
	Page<Blog> findAllBlogActive(Pageable pageable);

	// Tìm kiếm một bài viết dựa trên tên
	Blog findBlogByNameSearch(String nameSearch);

	// Lấy danh sách sáu bài viết
	List<Blog> getSixBlog();
}
