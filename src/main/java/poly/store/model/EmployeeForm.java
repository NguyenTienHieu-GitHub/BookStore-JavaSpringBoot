package poly.store.model;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

import lombok.Data;
/**
 * Đây là một lớp model để lưu trữ thông tin của một nhân viên.
 *
 * Các trường dữ liệu trong lớp này bao gồm:
 * - id: Mã số duy nhất của nhân viên.
 * - email: Địa chỉ email của nhân viên.
 * - fullname: Họ và tên của nhân viên.
 * - position: Chức vụ của nhân viên.
 * - department: Bộ phận hoặc phòng ban của nhân viên.
 * - phone: Số điện thoại của nhân viên.
 * - salary: Mức lương của nhân viên.
 * - startday: Ngày bắt đầu làm việc của nhân viên.
 * - role: Vai trò hoặc quyền hạn của nhân viên trong hệ thống.
 *
 * Lớp này được sử dụng để truyền thông tin về nhân viên giữa tầng giao diện và tầng xử lý logic trong ứng dụng.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeForm {
	private int id;
	private String email;
	private String fullname;
	private String position;
	private String department;
	private String phone;
	private int salary;
	private String startday;
	private int role;
}
