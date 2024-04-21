
package poly.store.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp `Role` đại diện cho vai trò của người dùng trong hệ thống.
 *
 * Thuộc tính:
 * - id: Định danh duy nhất của vai trò.
 * - name: Tên của vai trò.
 * - listUserRole: Danh sách các mối quan hệ giữa vai trò và người dùng.
 *
 * Các annotations:
 * - @Data: Tạo các phương thức getter, setter, equals, hashCode và toString tự động.
 * - @NoArgsConstructor: Tạo constructor mặc định không có tham số.
 * - @AllArgsConstructor: Tạo constructor có tham số cho tất cả các thuộc tính.
 * - @Entity: Đánh dấu lớp này là một Entity, tương ứng với một bảng trong cơ sở dữ liệu.
 * - @Table: Xác định tên của bảng trong cơ sở dữ liệu tương ứng với Entity này.
 * - @Id: Đánh dấu thuộc tính này là khóa chính của bảng.
 * - @GeneratedValue: Xác định cách sinh giá trị cho thuộc tính được đánh dấu là khóa chính.
 * - @OneToMany: Xác định mối quan hệ một-nhiều với Entity khác, trong trường hợp này là `UserRole`.
 * - @JsonIgnore: Chỉ định rằng thuộc tính này không nên được serialize/deserialize khi sử dụng JSON.
 */

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Roles")
public class Role implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "role")
	List<UserRole> listUserRole;
}
