package poly.store.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import poly.store.dao.CommentDao;
import poly.store.dao.ProductDao;
import poly.store.dao.UserDao;
import poly.store.entity.Comment;
import poly.store.entity.Product;
import poly.store.entity.User;
import poly.store.model.CommentModel;
import poly.store.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	CommentDao commentDao;

	@Autowired
	UserDao userDao;

	@Autowired
	ProductDao productDao;

	// Phương thức lấy danh sách comment dựa trên ID sản phẩm
	@Override
	public List<Comment> getListCommentByProductId(Integer id) {
		return commentDao.getListCommentByProductId(id);
	}

	// Phương thức tạo mới một comment
	@Override
	public CommentModel createComment(CommentModel commentModel) {
		// Lấy thông tin người dùng hiện tại từ SecurityContextHolder
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Tìm thông tin người dùng dựa trên email
		User temp = userDao.findUserByEmail(username);

		// Tìm thông tin sản phẩm dựa trên ID
		Product product = productDao.findById(commentModel.getProductId()).get();

		// Lấy thời gian hiện tại
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(date);

		// Tạo một đối tượng Comment và lưu vào cơ sở dữ liệu
		Comment comment = new Comment();
		comment.setContent(commentModel.getContent());
		comment.setStar(commentModel.getStar());
		comment.setDate(strDate);
		comment.setProduct(product);
		comment.setUser(temp);
		comment.setStatus("0"); // Status "0" cho comment chưa duyệt
		commentDao.save(comment);

		return commentModel;
	}

	// Phương thức lấy danh sách comment chờ duyệt
	@Override
	public List<Comment> getListCommentPending() {
		return commentDao.getListCommentPending();
	}

	// Phương thức lấy thông tin comment dựa trên ID comment
	@Override
	public Comment getCommentByCommentId(Integer id) {
		return commentDao.getCommentByCommentId(id);
	}

	// Phương thức duyệt comment
	@Override
	public void approveComment(Integer id) {
		// Tìm comment dựa trên ID và đổi status sang "1" để đánh dấu đã duyệt
		Comment comment = commentDao.findById(id).get();
		comment.setStatus("1");
		commentDao.save(comment);
	}

	// Phương thức xóa comment
	@Override
	public void delete(Integer id) {
		// Xóa comment dựa trên ID
		Comment comment = commentDao.findById(id).get();
		commentDao.delete(comment);
	}

	// Phương thức lấy danh sách comment đã duyệt
	@Override
	public List<Comment> getListCommentChecked() {
		return commentDao.getListCommentChecked();
	}

	// Phương thức đếm số lượng comment dựa trên tên sản phẩm
	@Override
	public int getCountCommentByProductNameSearch(String nameSearch) {
		return commentDao.getCountCommentByProductNameSearch(nameSearch);
	}

	// Phương thức tính tổng số sao của các comment dựa trên tên sản phẩm
	@Override
	public int getAllStarCommentByProductNameSearch(String nameSearch) {
		int result = 0;
		int totalStar = 0;
		List<Integer> listStar = commentDao.getAllStarCommentByProductNameSearch(nameSearch);
		if (!listStar.isEmpty()) {
			for (int i = 0; i < listStar.size(); i++) {
				totalStar = totalStar + listStar.get(i);
			}
			result = Math.round(totalStar / listStar.size());
		}
		return result;
	}
}
