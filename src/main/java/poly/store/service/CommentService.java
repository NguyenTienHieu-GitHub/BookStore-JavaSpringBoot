package poly.store.service;

import java.util.List;

import poly.store.entity.Comment;
import poly.store.model.CommentModel;

public interface CommentService {

	// Lấy danh sách các bình luận của một sản phẩm dựa trên ID sản phẩm
	List<Comment> getListCommentByProductId(Integer id);

	// Tạo mới một bình luận từ một đối tượng CommentModel
	CommentModel createComment(CommentModel commentModel);

	// Lấy danh sách các bình luận đang chờ duyệt
	List<Comment> getListCommentPending();

	// Lấy thông tin của một bình luận dựa trên ID bình luận
	Comment getCommentByCommentId(Integer id);

	// Duyệt một bình luận dựa trên ID bình luận
	void approveComment(Integer id);

	// Xóa một bình luận dựa trên ID bình luận
	void delete(Integer id);

	// Lấy danh sách các bình luận đã được kiểm tra
	List<Comment> getListCommentChecked();

	// Đếm số lượng bình luận dựa trên tên sản phẩm tìm kiếm
	int getCountCommentByProductNameSearch(String nameSearch);

	// Tính tổng số sao của các bình luận dựa trên tên sản phẩm tìm kiếm
	int getAllStarCommentByProductNameSearch(String nameSearch);
}
