package poly.store.service;

import java.util.List;

import poly.store.entity.Employee;
import poly.store.model.EmployeeModel;

public interface EmployeeService {

	// Lấy danh sách tất cả nhân viên và chuyển đổi sang đối tượng EmployeeModel
	List<EmployeeModel> getListEmployee();

	// Lưu thông tin của một nhân viên
	void save(Employee employee);
}
