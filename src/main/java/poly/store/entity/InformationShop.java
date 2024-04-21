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
 * Lớp `InformationShop` đại diện cho thông tin về cửa hàng.
 *
 * Thuộc tính:
 * - id: Định danh duy nhất của cửa hàng.
 * - name: Tên của cửa hàng.
 * - active: Trạng thái hoạt động của cửa hàng.
 * - Timeopen: Thời gian mở cửa của cửa hàng.
 * - logo: Đường dẫn đến logo của cửa hàng.
 * - phone: Số điện thoại của cửa hàng.
 * - fax: Số fax của cửa hàng.
 * - email: Địa chỉ email của cửa hàng.
 * - Logofooter: Đường dẫn đến logo chân trang của cửa hàng.
 * - address: Địa chỉ của cửa hàng.
 * - Createday: Ngày tạo thông tin về cửa hàng.
 * - Personcreate: Người tạo thông tin về cửa hàng.
 * - Deleteday: Ngày xóa thông tin về cửa hàng.
 * - Persondelete: Người xóa thông tin về cửa hàng.
 * - Updateday: Ngày cập nhật thông tin về cửa hàng.
 * - Personupdate: Người cập nhật thông tin về cửa hàng.
 *
 * Các annotations:
 * - @Entity: Đánh dấu lớp này là một entity trong cơ sở dữ liệu.
 * - @Table(name = "Informationshop"): Xác định tên bảng tương ứng trong cơ sở dữ liệu.
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
@Table(name = "Informationshop")
public class InformationShop implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private boolean active;
	
	private String Timeopen;

	private String logo;

	private String phone;

	private String fax;

	private String email;

	private String Logofooter;

	private String address;

	private String Createday;

	private int Personcreate;

	private String Deleteday;

	private int Persondelete;

	private String Updateday;

	private int Personupdate;
}
