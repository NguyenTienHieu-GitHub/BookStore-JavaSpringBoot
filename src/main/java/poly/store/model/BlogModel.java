package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Đây là một lớp model để đại diện cho thông tin về một bài viết trên blog trong hệ thống cửa hàng.
 *
 * Các trường dữ liệu trong lớp này bao gồm:
 * - id: Định danh duy nhất của bài viết trên blog.
 * - title: Tiêu đề của bài viết.
 * - content: Nội dung của bài viết.
 * - logo: Đường dẫn đến hình ảnh minh họa cho bài viết.
 * - uploadDay: Ngày bài viết được tải lên.
 * - banner: Đường dẫn đến hình ảnh banner của bài viết.
 * - active: Trạng thái hoạt động của bài viết.
 * - description: Mô tả ngắn gọn về bài viết.
 * - nameSearch: Chuỗi tìm kiếm của bài viết, được sử dụng cho việc tìm kiếm nhanh bài viết trong hệ thống.
 *
 * Lớp này được sử dụng để truyền thông tin về các bài viết từ tầng xử lý logic đến tầng giao diện người dùng để hiển thị thông tin tương ứng.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogModel {
	private int id;
	private String title;
	private String content;
	private String logo;
	private String uploadDay;
	private String banner;
	private boolean active;
	private String description;
	private String nameSearch;
}
