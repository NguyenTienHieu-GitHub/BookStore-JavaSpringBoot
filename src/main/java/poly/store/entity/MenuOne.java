
package poly.store.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp `MenuOne` đại diện cho một danh mục cấp độ một trong hệ thống menu.
 *
 * Thuộc tính:
 * - id: Định danh duy nhất của danh mục cấp độ một.
 * - name: Tên của danh mục cấp độ một.
 * - Namesearch: Tên tìm kiếm của danh mục cấp độ một.
 * - category: Danh mục cha của danh mục cấp độ một.
 * - Createday: Ngày tạo danh mục cấp độ một.
 * - Personcreate: Người tạo danh mục cấp độ một.
 * - Deleteday: Ngày xóa danh mục cấp độ một.
 * - Persondelete: Người xóa danh mục cấp độ một.
 * - Updateday: Ngày cập nhật danh mục cấp độ một.
 * - Personupdate: Người cập nhật danh mục cấp độ một.
 * - listMenuTwo: Danh sách các danh mục cấp độ hai thuộc danh mục cấp độ một.
 *
 * Các annotations:
 * - @Entity: Đánh dấu lớp này là một entity trong cơ sở dữ liệu.
 * - @Table(name = "Menuone"): Xác định tên bảng tương ứng trong cơ sở dữ liệu.
 * - @Data: Tạo các phương thức getter, setter, equals, hashCode và toString tự động.
 * - @NoArgsConstructor: Tạo constructor mặc định không có tham số.
 * - @AllArgsConstructor: Tạo constructor có tham số cho tất cả các thuộc tính.
 * - @Id: Xác định thuộc tính id là khóa chính của entity.
 * - @GeneratedValue(strategy = GenerationType.IDENTITY): Xác định cách sinh giá trị cho id là tự động tăng.
 * - @ManyToOne(fetch = FetchType.EAGER): Xác định mối quan hệ nhiều-đến-một với entity Category thông qua trường category.
 * - @JoinColumn(name = "Cate_Id"): Xác định tên cột tham chiếu đến khóa chính của entity Category trong bảng Menuone.
 * - @JsonIgnore: Ngăn không cho trường listMenuTwo được trả về khi chuyển đối tượng MenuOne thành JSON.
 * - @OneToMany(mappedBy = "menuOne"): Xác định mối quan hệ một-đến-nhiều với entity MenuTwo thông qua trường menuOne.
 */

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Menuone")
public class MenuOne implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String Namesearch;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Cate_Id")
	private Category category;

	private String Createday;

	private int Personcreate;

	private String Deleteday;

	private int Persondelete;

	private String Updateday;

	private int Personupdate;

	@JsonIgnore
	@OneToMany(mappedBy = "menuOne")
	List<MenuTwo> listMenuTwo;
}
