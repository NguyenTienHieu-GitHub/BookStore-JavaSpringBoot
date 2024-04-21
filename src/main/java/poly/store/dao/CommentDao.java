package poly.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import poly.store.entity.Comment;

// Giao diện này mở rộng từ JpaRepository, cung cấp các phương thức để thực hiện các thao tác CRUD trên các đối tượng Comment
public interface CommentDao extends JpaRepository<Comment, Integer>{

	// Phương thức truy vấn tùy chỉnh để lấy danh sách các nhận xét theo ID sản phẩm
	@Query("SELECT c FROM Comment c WHERE c.status = 1 AND c.product.id = :uid")
	List<Comment> getListCommentByProductId(@Param("uid") Integer id);

	// Phương thức truy vấn tùy chỉnh để lấy danh sách các nhận xét chưa kiểm tra
	@Query("SELECT c FROM Comment c WHERE c.status = 0")
	List<Comment> getListCommentPending();

	// Phương thức truy vấn tùy chỉnh để lấy danh sách các nhận xét đã kiểm tra
	@Query("SELECT c FROM Comment c WHERE c.status = 1")
	List<Comment> getListCommentChecked();

	// Phương thức truy vấn tùy chỉnh để lấy một nhận xét dựa trên ID nhận xét
	@Query("SELECT c FROM Comment c WHERE c.id = :uid")
	Comment getCommentByCommentId(@Param("uid") Integer id);

	// Phương thức truy vấn tùy chỉnh để đếm số lượng nhận xét theo tên sản phẩm và trạng thái đã kiểm tra
	@Query("SELECT COUNT(c) FROM Comment c WHERE c.product.Namesearch = :unameSearch AND c.status = 1")
	int getCountCommentByProductNameSearch(@Param("unameSearch") String nameSearch);

	// Phương thức truy vấn tùy chỉnh để lấy tất cả các điểm đánh giá từ các nhận xét của một sản phẩm đã kiểm tra
	@Query("SELECT c.star FROM Comment c WHERE c.product.Namesearch = :unameSearch AND c.status = 1")
	List<Integer> getAllStarCommentByProductNameSearch(@Param("unameSearch") String nameSearch);
}
