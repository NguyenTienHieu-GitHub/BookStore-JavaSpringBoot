package poly.store.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Đây là một lớp model để đại diện cho thông tin của một nhân viên trong hệ thống.
 *
 * Các trường dữ liệu trong lớp này bao gồm:
 * - Fullname: Họ và tên của nhân viên.
 * - department: Bộ phận hoặc phòng ban mà nhân viên đó thuộc về.
 * - phone: Số điện thoại của nhân viên.
 * - position: Chức vụ của nhân viên trong công ty.
 * - birthday: Ngày sinh của nhân viên.
 * - Startday: Ngày bắt đầu làm việc của nhân viên tại công ty.
 * - salary: Mức lương của nhân viên.
 *
 * Lớp này được sử dụng để truyền thông tin về nhân viên giữa các thành phần khác nhau trong ứng dụng, như giao diện và lớp xử lý dữ liệu.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class EmployeeModel {
	@Id
	private String Fullname;
	
	private String department;

	private String phone;
	
	private String position;
	
	private String birthday;
	
	private String Startday;
	
	private int salary;
}
