package poly.store.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.store.entity.Comment;
import poly.store.model.CommentModel;
import poly.store.service.CommentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/comment")
public class CommentRestController {

	@Autowired
	CommentService commentService;

	// API để lấy danh sách các comment của một sản phẩm dựa trên id sản phẩm
	@GetMapping("/form/product/{id}")
	public List<Comment> getListCommentByProductId(@PathVariable("id") Integer id) {
		return commentService.getListCommentByProductId(id);
	}

	// API để tạo mới một comment
	@PostMapping("/form")
	public CommentModel create(@RequestBody CommentModel commentModel) {
		return commentService.createComment(commentModel);
	}

	// API để lấy danh sách các comment chưa được duyệt
	@GetMapping("/pending")
	public List<Comment> getListCommentPending(){
		return commentService.getListCommentPending();
	}

	// API để lấy thông tin của một comment dựa trên id comment
	@GetMapping("/pending/{id}")
	public Comment getCommentByCommentId(@PathVariable("id") Integer id) {
		return commentService.getCommentByCommentId(id);
	}

	// API để duyệt một comment dựa trên id comment
	@PutMapping("/form/approve/{id}")
	public void approve(@PathVariable("id") Integer id) {
		commentService.approveComment(id);
	}

	// API để xóa một comment dựa trên id comment
	@DeleteMapping("/form/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		commentService.delete(id);
	}

	// API để lấy danh sách các comment đã được duyệt
	@GetMapping("/approved")
	public List<Comment> getListCommentChecked(){
		return commentService.getListCommentChecked();
	}
}
