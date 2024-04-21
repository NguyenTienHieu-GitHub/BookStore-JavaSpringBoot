package poly.store.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp `Blog` đại diện cho thông tin về bài viết trên blog.
 *
 * Thuộc tính:
 * - id: Định danh duy nhất của bài viết.
 * - title: Tiêu đề của bài viết.
 * - content: Nội dung của bài viết.
 * - logo: Đường dẫn đến hình ảnh đại diện cho bài viết.
 * - active: Trạng thái hoạt động của bài viết.
 * - Uploadday: Ngày bài viết được tải lên.
 * - Banner: Đường dẫn đến banner của bài viết.
 * - Namesearch: Tên tìm kiếm của bài viết.
 * - description: Mô tả về bài viết.
 * - Createday: Ngày tạo bài viết.
 * - Personcreate: Người tạo bài viết.
 * - Deleteday: Ngày bài viết bị xóa.
 * - Persondelete: Người xóa bài viết.
 * - Updateday: Ngày cập nhật bài viết.
 * - Personupdate: Người cập nhật bài viết.
 *
 * Các annotations:
 * - @Entity: Đánh dấu lớp này là một entity trong cơ sở dữ liệu.
 * - @Table(name = "Blogs"): Xác định tên bảng tương ứng trong cơ sở dữ liệu.
 * - @Data: Tạo các phương thức getter, setter, equals, hashCode và toString tự động.
 * - @NoArgsConstructor: Tạo constructor mặc định không có tham số.
 * - @AllArgsConstructor: Tạo constructor có tham số cho tất cả các thuộc tính.
 * - @Id: Xác định thuộc tính id là khóa chính của entity.
 * - @GeneratedValue(strategy = GenerationType.IDENTITY): Xác định cách sinh giá trị cho id là tự động tăng.
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Blogs")
public class Blog implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	
	private String content;
	
	private String logo;
	
	private boolean active;
	
	private String Uploadday;
	
	private String Banner;
	
	private String Namesearch;
	
	private String description;
	
	private String Createday;

	private int Personcreate;

	private String Deleteday;

	private int Persondelete;

	private String Updateday;

	private int Personupdate;
}
