
package poly.store.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp `Category` đại diện cho thông tin về danh mục sản phẩm.
 *
 * Thuộc tính:
 * - id: Định danh duy nhất của danh mục.
 * - name: Tên của danh mục.
 * - logo: Đường dẫn đến hình ảnh logo của danh mục.
 * - banner: Đường dẫn đến banner của danh mục.
 * - description: Mô tả về danh mục.
 * - Createday: Ngày tạo danh mục.
 * - Personcreate: Người tạo danh mục.
 * - Deleteday: Ngày danh mục bị xóa.
 * - Persondelete: Người xóa danh mục.
 * - Updateday: Ngày cập nhật danh mục.
 * - Personupdate: Người cập nhật danh mục.
 * - Namesearch: Tên tìm kiếm của danh mục.
 * - listMenuOne: Danh sách các menu một của danh mục.
 * - listProduct: Danh sách các sản phẩm thuộc danh mục.
 *
 * Các annotations:
 * - @Entity: Đánh dấu lớp này là một entity trong cơ sở dữ liệu.
 * - @Table(name = "Categories"): Xác định tên bảng tương ứng trong cơ sở dữ liệu.
 * - @Data: Tạo các phương thức getter, setter, equals, hashCode và toString tự động.
 * - @NoArgsConstructor: Tạo constructor mặc định không có tham số.
 * - @AllArgsConstructor: Tạo constructor có tham số cho tất cả các thuộc tính.
 * - @Id: Xác định thuộc tính id là khóa chính của entity.
 * - @GeneratedValue(strategy = GenerationType.IDENTITY): Xác định cách sinh giá trị cho id là tự động tăng.
 * - @JsonIgnore: Ngăn không cho trường listMenuOne và listProduct được trả về khi chuyển đối tượng Category thành JSON.
 * - @OneToMany(mappedBy = "category"): Xác định mối quan hệ một-đến-nhiều với entity MenuOne và Product thông qua trường category.
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Categories")
public class Category implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String logo;

	private String banner;

	private String description;

	private String Createday;

	private int Personcreate;

	private String Deleteday;

	private int Persondelete;

	private String Updateday;

	private int Personupdate;

	private String Namesearch;

	@JsonIgnore
	@OneToMany(mappedBy = "category")
	List<MenuOne> listMenuOne;

	@JsonIgnore
	@OneToMany(mappedBy = "category")
	List<Product> listProduct;
}
