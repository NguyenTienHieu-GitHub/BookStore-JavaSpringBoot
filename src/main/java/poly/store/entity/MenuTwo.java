
package poly.store.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * Lớp `MenuTwo` đại diện cho một danh mục cấp độ hai trong hệ thống menu.
 *
 * Thuộc tính:
 * - id: Định danh duy nhất của danh mục cấp độ hai.
 * - name: Tên của danh mục cấp độ hai.
 * - Namesearch: Tên tìm kiếm của danh mục cấp độ hai.
 * - menuOne: Danh mục cấp độ một mà danh mục cấp độ hai thuộc về.
 * - Createday: Ngày tạo danh mục cấp độ hai.
 * - Personcreate: Người tạo danh mục cấp độ hai.
 * - Deleteday: Ngày xóa danh mục cấp độ hai.
 * - Persondelete: Người xóa danh mục cấp độ hai.
 * - Updateday: Ngày cập nhật danh mục cấp độ hai.
 * - Personupdate: Người cập nhật danh mục cấp độ hai.
 *
 * Các annotations:
 * - @Entity: Đánh dấu lớp này là một entity trong cơ sở dữ liệu.
 * - @Table(name = "Menutwo"): Xác định tên bảng tương ứng trong cơ sở dữ liệu.
 * - @Data: Tạo các phương thức getter, setter, equals, hashCode và toString tự động.
 * - @NoArgsConstructor: Tạo constructor mặc định không có tham số.
 * - @AllArgsConstructor: Tạo constructor có tham số cho tất cả các thuộc tính.
 * - @Id: Xác định thuộc tính id là khóa chính của entity.
 * - @GeneratedValue(strategy = GenerationType.IDENTITY): Xác định cách sinh giá trị cho id là tự động tăng.
 * - @ManyToOne(fetch = FetchType.EAGER): Xác định mối quan hệ nhiều-đến-một với entity MenuOne thông qua trường menuOne.
 * - @JoinColumn(name = "Menu1_Id"): Xác định tên cột tham chiếu đến khóa chính của entity MenuOne trong bảng Menutwo.
 */

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Menutwo")
public class MenuTwo implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String Namesearch;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Menu1_Id")
	private MenuOne menuOne;

	private String Createday;

	private int Personcreate;

	private String Deleteday;

	private int Persondelete;

	private String Updateday;

	private int Personupdate;
}
