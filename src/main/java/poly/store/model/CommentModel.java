package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Đây là một lớp model để đại diện cho thông tin của một bình luận của người dùng về một sản phẩm cụ thể.
 *
 * Các trường dữ liệu trong lớp này bao gồm:
 * - productId: ID của sản phẩm mà bình luận được đính kèm.
 * - content: Nội dung của bình luận.
 * - star: Số lượng sao được đánh giá cho sản phẩm, thường từ 1 đến 5.
 *
 * Lớp này được sử dụng để truyền thông tin cần thiết khi người dùng thực hiện việc bình luận về một sản phẩm từ giao diện người dùng đến tầng xử lý logic của ứng dụng.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentModel {
	private int productId;
	private String content;
	private int star;
}
