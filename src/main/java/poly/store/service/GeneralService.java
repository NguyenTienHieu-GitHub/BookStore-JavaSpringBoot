package poly.store.service;

import poly.store.model.EmployeeForm;

public interface GeneralService {

	// Tạo mới thông tin nhân viên từ một đối tượng EmployeeForm
	EmployeeForm createEmployee(EmployeeForm employee);

	// Lấy thông tin của một nhân viên dựa trên ID
	EmployeeForm getOneUserById(Integer id);

	// Cập nhật thông tin của một nhân viên từ một đối tượng EmployeeForm
	EmployeeForm updateEmployee(EmployeeForm employeeForm);
}
