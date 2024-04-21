
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
 * Lớp `Manufacturer` đại diện cho thông tin về một nhà sản xuất.
 *
 * Thuộc tính:
 * - id: Định danh duy nhất của nhà sản xuất.
 * - name: Tên của nhà sản xuất.
 * - logo: Đường dẫn đến logo của nhà sản xuất.
 * - banner: Đường dẫn đến banner của nhà sản xuất.
 * - description: Mô tả về nhà sản xuất.
 * - Createday: Ngày tạo thông tin về nhà sản xuất.
 * - Personcreate: Người tạo thông tin về nhà sản xuất.
 * - Deleteday: Ngày xóa thông tin về nhà sản xuất.
 * - Persondelete: Người xóa thông tin về nhà sản xuất.
 * - Updateday: Ngày cập nhật thông tin về nhà sản xuất.
 * - Personupdate: Người cập nhật thông tin về nhà sản xuất.
 * - listProduct: Danh sách các sản phẩm thuộc nhà sản xuất.
 *
 * Các annotations:
 * - @Entity: Đánh dấu lớp này là một entity trong cơ sở dữ liệu.
 * - @Table(name = "Manufactures"): Xác định tên bảng tương ứng trong cơ sở dữ liệu.
 * - @Data: Tạo các phương thức getter, setter, equals, hashCode và toString tự động.
 * - @NoArgsConstructor: Tạo constructor mặc định không có tham số.
 * - @AllArgsConstructor: Tạo constructor có tham số cho tất cả các thuộc tính.
 * - @Id: Xác định thuộc tính id là khóa chính của entity.
 * - @GeneratedValue(strategy = GenerationType.IDENTITY): Xác định cách sinh giá trị cho id là tự động tăng.
 * - @JsonIgnore: Ngăn không cho trường listProduct được trả về khi chuyển đối tượng Manufacturer thành JSON.
 * - @OneToMany(mappedBy = "manufacturer"): Xác định mối quan hệ một-đến-nhiều với entity Product thông qua trường manufacturer.
 */

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Manufactures")
public class Manufacturer implements Serializable {
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

	@JsonIgnore
	@OneToMany(mappedBy = "manufacturer")
	List<Product> listProduct;
}
