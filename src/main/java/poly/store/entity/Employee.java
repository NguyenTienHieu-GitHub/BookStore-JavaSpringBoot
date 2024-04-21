
package poly.store.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp `Employee` đại diện cho thông tin về một nhân viên trong hệ thống.
 *
 * Thuộc tính:
 * - id: Định danh duy nhất của nhân viên.
 * - department: Bộ phận làm việc của nhân viên.
 * - position: Chức vụ của nhân viên.
 * - phone: Số điện thoại của nhân viên.
 * - Startday: Ngày bắt đầu làm việc của nhân viên.
 * - salary: Mức lương của nhân viên.
 * - Createday: Ngày tạo thông tin nhân viên.
 * - Personcreate: Người tạo thông tin nhân viên.
 * - Deleteday: Ngày xóa thông tin nhân viên.
 * - Persondelete: Người xóa thông tin nhân viên.
 * - Updateday: Ngày cập nhật thông tin nhân viên.
 * - Personupdate: Người cập nhật thông tin nhân viên.
 * - image: Đường dẫn đến hình ảnh của nhân viên.
 * - user: Thông tin người dùng tương ứng với nhân viên.
 *
 * Các annotations:
 * - @Entity: Đánh dấu lớp này là một entity trong cơ sở dữ liệu.
 * - @Table(name="Employees"): Xác định tên bảng tương ứng trong cơ sở dữ liệu.
 * - @Data: Tạo các phương thức getter, setter, equals, hashCode và toString tự động.
 * - @NoArgsConstructor: Tạo constructor mặc định không có tham số.
 * - @AllArgsConstructor: Tạo constructor có tham số cho tất cả các thuộc tính.
 * - @Id: Xác định thuộc tính id là khóa chính của entity.
 * - @GeneratedValue(strategy = GenerationType.IDENTITY): Xác định cách sinh giá trị cho id là tự động tăng.
 * - @ManyToOne: Xác định mối quan hệ nhiều-đến-một với entity User.
 * - @JoinColumn(name = "User_Id"): Xác định tên cột tham chiếu đến khóa chính của entity User trong bảng Employees.
 * - @JsonIgnore: Ngăn không cho trường user được trả về khi chuyển đối tượng Employee thành JSON.
 */

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Employees")
public class Employee implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String department;
	
	private String position;

	private String phone;
	
	private String Startday;
	
	private int salary;
	
	private String Createday;
	
	private int Personcreate;
	
	private String Deleteday;
	
	private int Persondelete;
	
	private String Updateday;
	
	private int Personupdate;
	
	private String image;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "User_Id")
	User user;
}
