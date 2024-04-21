
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
 * Lớp `UserRole` đại diện cho mối quan hệ giữa người dùng và vai trò trong hệ thống.
 *
 * Thuộc tính:
 * - id: Định danh duy nhất của mối quan hệ.
 * - user: Người dùng liên kết với vai trò.
 * - role: Vai trò của người dùng trong hệ thống.
 *
 * Constructors:
 * - UserRole(User user, Role role): Constructor để tạo một mối quan hệ mới giữa người dùng và vai trò.
 *
 * Các annotations:
 * - @Data: Tạo các phương thức getter, setter, equals, hashCode và toString tự động.
 * - @NoArgsConstructor: Tạo constructor mặc định không có tham số.
 * - @AllArgsConstructor: Tạo constructor có tham số cho tất cả các thuộc tính.
 * - @Entity: Đánh dấu lớp này là một Entity, tương ứng với một bảng trong cơ sở dữ liệu.
 * - @Table: Xác định tên của bảng trong cơ sở dữ liệu tương ứng với Entity này.
 * - @Id: Đánh dấu thuộc tính này là khóa chính của bảng.
 * - @GeneratedValue: Xác định cách sinh giá trị cho thuộc tính được đánh dấu là khóa chính.
 * - @ManyToOne: Xác định mối quan hệ nhiều-một với Entity khác, trong trường hợp này là `User` và `Role`.
 * - @JoinColumn: Xác định tên của cột trong bảng hiện tại làm khóa ngoại tham chiếu đến bảng liên kết.
 */

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="User_Role")
public class UserRole implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="User_Id")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="Role_Id")
	private Role role;

	public UserRole(User user, Role role) {
		this.user = user;
		this.role = role;
	}
}
