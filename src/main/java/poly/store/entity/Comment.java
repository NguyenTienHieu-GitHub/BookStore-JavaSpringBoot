package poly.store.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Lớp `Comment` đại diện cho thông tin về bình luận của người dùng về sản phẩm.
 *
 * Thuộc tính:
 * - id: Định danh duy nhất của bình luận.
 * - content: Nội dung của bình luận.
 * - star: Số sao đánh giá của bình luận.
 * - date: Ngày tạo bình luận.
 * - status: Trạng thái của bình luận.
 * - user: Thông tin người dùng tạo bình luận.
 * - product: Thông tin sản phẩm mà bình luận được tạo cho.
 *
 * Các annotations:
 * - @Entity: Đánh dấu lớp này là một entity trong cơ sở dữ liệu.
 * - @Table(name = "Comments"): Xác định tên bảng tương ứng trong cơ sở dữ liệu.
 * - @Data: Tạo các phương thức getter, setter, equals, hashCode và toString tự động.
 * - @NoArgsConstructor: Tạo constructor mặc định không có tham số.
 * - @AllArgsConstructor: Tạo constructor có tham số cho tất cả các thuộc tính.
 * - @Id: Xác định thuộc tính id là khóa chính của entity.
 * - @GeneratedValue(strategy = GenerationType.IDENTITY): Xác định cách sinh giá trị cho id là tự động tăng.
 * - @ManyToOne: Xác định mối quan hệ nhiều-đến-một với entity User và Product.
 * - @JoinColumn(name = "User_Id"): Xác định tên cột tham chiếu đến khóa chính của entity User trong bảng Comments.
 * - @JoinColumn(name = "Product_Id"): Xác định tên cột tham chiếu đến khóa chính của entity Product trong bảng Comments.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String content;
	private int star;
	private String date;
	private String status;

	//@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "User_Id")
	User user;

	//@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "Product_Id")
	Product product;
}
