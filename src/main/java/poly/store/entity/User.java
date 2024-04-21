
package poly.store.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * Lớp `User` đại diện cho người dùng trong hệ thống.
 *
 * Thuộc tính:
 * - id: Định danh duy nhất của người dùng.
 * - email: Địa chỉ email của người dùng.
 * - password: Mật khẩu của người dùng.
 * - Fullname: Họ và tên của người dùng.
 * - sex: Giới tính của người dùng.
 * - birthday: Ngày sinh của người dùng.
 * - subscribe: Trạng thái đăng ký nhận thông báo của người dùng.
 * - Createday: Ngày tạo tài khoản.
 * - Deleteday: Ngày xóa tài khoản.
 * - Persondelete: Người thực hiện việc xóa tài khoản.
 * - listUserRole: Danh sách các mối quan hệ giữa người dùng và vai trò.
 * - listEmployee: Danh sách các nhân viên tương ứng với người dùng (nếu có).
 * - listAddress: Danh sách các địa chỉ của người dùng.
 * - listFavorite: Danh sách các sản phẩm được người dùng yêu thích.
 *
 * Các annotations:
 * - @Data: Tạo các phương thức getter, setter, equals, hashCode và toString tự động.
 * - @NoArgsConstructor: Tạo constructor mặc định không có tham số.
 * - @AllArgsConstructor: Tạo constructor có tham số cho tất cả các thuộc tính.
 * - @Entity: Đánh dấu lớp này là một Entity, tương ứng với một bảng trong cơ sở dữ liệu.
 * - @Table: Xác định tên của bảng trong cơ sở dữ liệu tương ứng với Entity này.
 * - @Id: Đánh dấu thuộc tính này là khóa chính của bảng.
 * - @GeneratedValue: Xác định cách sinh giá trị cho thuộc tính được đánh dấu là khóa chính.
 * - @OneToMany: Xác định mối quan hệ một-nhiều với Entity khác, trong trường hợp này là `UserRole`, `Employee`, `Address`, và `Favorite`.
 * - @JsonIgnore: Chỉ định rằng thuộc tính này không nên được serialize/deserialize khi sử dụng JSON.
 */

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String email;

	private String password;

	private String Fullname;

	private int sex;

	private String birthday;

	private int subscribe;

	private String Createday;

	private String Deleteday;

	private int Persondelete;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	List<UserRole> listUserRole;

	// @JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	List<Employee> listEmployee;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	List<Address> listAddress;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	List<Favorite> listFavorite;
}
