package poly.store.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import poly.store.dao.EmployeeDao;
import poly.store.dao.RoleDao;
import poly.store.dao.UserDao;
import poly.store.dao.UserRoleDao;
import poly.store.entity.Employee;
import poly.store.entity.Role;
import poly.store.entity.User;
import poly.store.entity.UserRole;
import poly.store.model.EmployeeForm;
import poly.store.service.GeneralService;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class GeneralServiceImpl implements GeneralService {
	@Autowired
	UserDao userDao;

	@Autowired
	RoleDao roleDao;

	@Autowired
	UserRoleDao userRoleDao;

	@Autowired
	EmployeeDao employeeDao;

	// Phương thức tạo mới một nhân viên và tài khoản người dùng
	@Override
	public EmployeeForm createEmployee(EmployeeForm employeeForm) {
		// Tạo mới một tài khoản người dùng
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User user = new User();
		user.setEmail(employeeForm.getEmail());
		user.setPassword("1234567"); // Mật khẩu mặc định
		user.setFullname(employeeForm.getFullname());
		user.setCreateday(timestamp.toString());
		userDao.save(user);

		// Tìm thông tin vai trò dựa trên roleId
		Role role = roleDao.findById(employeeForm.getRole()).get();

		// Tạo mới một bản ghi UserRole
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		userRoleDao.save(userRole);

		// Tạo mới một bản ghi Employee
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		User temp = userDao.findUserByEmail(username);

		Employee employee = new Employee();
		employee.setDepartment(employeeForm.getDepartment());
		employee.setPosition(employeeForm.getPosition());
		employee.setPhone(employeeForm.getPhone());
		employee.setStartday(employeeForm.getStartday());
		employee.setSalary(employeeForm.getSalary());
		employee.setCreateday(timestamp.toString());
		employee.setUser(user);
		employee.setPersoncreate(temp.getId());
		employeeDao.save(employee);

		return employeeForm;
	}

	// Phương thức lấy thông tin một nhân viên dựa trên id
	@Override
	public EmployeeForm getOneUserById(Integer id) {
		User user = userDao.findById(id).get();
		EmployeeForm employeeForm = new EmployeeForm();
		employeeForm.setFullname(user.getFullname());
		employeeForm.setEmail(user.getEmail());

		for (Employee employee : user.getListEmployee()) {
			employeeForm.setDepartment(employee.getDepartment());
			employeeForm.setPhone(employee.getPhone());
			employeeForm.setSalary(employee.getSalary());
			employeeForm.setPosition(employee.getPosition());
			employeeForm.setStartday(employee.getStartday());
		}

		for (UserRole userRole : user.getListUserRole()) {
			employeeForm.setRole(userRole.getRole().getId());
		}

		return employeeForm;
	}

	// Phương thức cập nhật thông tin một nhân viên
	@Override
	public EmployeeForm updateEmployee(EmployeeForm employeeForm) {
		// Cập nhật thông tin tài khoản người dùng
		User user = userDao.findById(employeeForm.getId()).get();
		user.setEmail(employeeForm.getEmail());
		user.setFullname(employeeForm.getFullname());
		userDao.save(user);

		// Cập nhật quyền người dùng
		UserRole userRole = roleDao.getRoleByUserId(employeeForm.getId());
		Role role = roleDao.findById(employeeForm.getRole()).get();
		userRole.setUser(user);
		userRole.setRole(role);
		userRoleDao.save(userRole);

		// Cập nhật thông tin nhân viên
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		User temp = userDao.findUserByEmail(username);

		Employee employee = employeeDao.getEmployeeByUserId(employeeForm.getId());
		employee.setDepartment(employeeForm.getDepartment());
		employee.setPosition(employeeForm.getPosition());
		employee.setPhone(employeeForm.getPhone());
		employee.setStartday(employeeForm.getStartday());
		employee.setSalary(employeeForm.getSalary());
		employee.setUpdateday(timestamp.toString());
		employee.setUser(user);
		employee.setPersonupdate(temp.getId());
		employeeDao.save(employee);

		return employeeForm;
	}
}
