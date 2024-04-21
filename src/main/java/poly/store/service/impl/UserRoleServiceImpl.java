package poly.store.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import poly.store.dao.EmployeeDao;
import poly.store.dao.UserDao;
import poly.store.dao.UserRoleDao;
import poly.store.entity.Employee;
import poly.store.entity.User;
import poly.store.entity.UserRole;
import poly.store.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	UserRoleDao userRoleDao; // DAO layer để tương tác với dữ liệu của bảng UserRole

	@Autowired
	UserDao userDao; // DAO layer để tương tác với dữ liệu của bảng User

	@Autowired
	EmployeeDao employeeDao; // DAO layer để tương tác với dữ liệu của bảng Employee

	/**
	 * Lưu thông tin UserRole vào cơ sở dữ liệu.
	 *
	 * @param userRole Đối tượng UserRole cần lưu.
	 */
	@Override
	public void save(UserRole userRole) {
		userRoleDao.save(userRole);
	}

	/**
	 * Lấy danh sách tất cả các UserRole từ cơ sở dữ liệu.
	 *
	 * @return List<UserRole> Danh sách các UserRole.
	 */
	@Override
	public List<UserRole> findAll() {
		return userRoleDao.findAll();
	}

	/**
	 * Lấy danh sách tất cả các UserRole có quyền admin hoặc giám đốc từ cơ sở dữ liệu.
	 *
	 * @return List<UserRole> Danh sách các UserRole có quyền admin hoặc giám đốc.
	 */
	@Override
	public List<UserRole> findAllAdminOrDirector() {
		return userRoleDao.findAllAdminOrDirector();
	}

	/**
	 * Xóa UserRole từ cơ sở dữ liệu theo ID.
	 *
	 * @param id ID của UserRole cần xóa.
	 */
	@Override
	public void delete(Integer id) {
		// Lấy thông tin người dùng đang đăng nhập
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User temp = userDao.findUserByEmail(username); // Tìm thông tin người dùng dựa trên email
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User user = userDao.findById(id).get(); // Tìm người dùng cần xóa dựa trên ID

		// Kiểm tra nếu người dùng đang đăng nhập không phải là người dùng cần xóa
		if(!temp.getEmail().equals(user.getEmail())) {
			user.setDeleteday(timestamp.toString()); // Đặt thời gian xóa cho người dùng
			user.setPersondelete(temp.getId()); // Đặt ID của người xóa
			userDao.save(user); // Lưu thay đổi vào cơ sở dữ liệu

			// Xóa các thông tin liên quan đến Employee nếu có
			List<Employee> listEmployee = user.getListEmployee();
			for(Employee e: listEmployee) {
				e.setDeleteday(timestamp.toString()); // Đặt thời gian xóa cho Employee
				e.setPersondelete(temp.getId()); // Đặt ID của người xóa
				employeeDao.save(e); // Lưu thay đổi vào cơ sở dữ liệu
			}
		}
		// Nếu người dùng đang đăng nhập là người dùng cần xóa, ném ra một ngoại lệ
		else {
			throw new RuntimeException();
		}
	}

}
