package poly.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.store.dao.EmployeeDao;
import poly.store.entity.Employee;
import poly.store.model.EmployeeModel;
import poly.store.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeDao employeeDao;

	// Phương thức lấy danh sách tất cả nhân viên
	@Override
	public List<EmployeeModel> getListEmployee() {
		// Gọi phương thức getListEmployee() từ đối tượng employeeDao để lấy danh sách nhân viên
		return employeeDao.getListEmployee();
	}

	// Phương thức lưu thông tin của một nhân viên vào cơ sở dữ liệu
	@Override
	public void save(Employee employee) {
		// Gọi phương thức save() từ đối tượng employeeDao để lưu thông tin nhân viên
		employeeDao.save(employee);
	}
}
