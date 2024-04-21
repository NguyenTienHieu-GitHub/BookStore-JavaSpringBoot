package poly.store.service;

import java.util.List;
import poly.store.entity.UserRole;

public interface UserRoleService {

	// Lưu trữ một đối tượng UserRole vào cơ sở dữ liệu
	void save(UserRole userRole);

	// Lấy danh sách tất cả các UserRole từ cơ sở dữ liệu
	List<UserRole> findAll();

	// Lấy danh sách tất cả các UserRole có vai trò là "Admin" hoặc "Director" từ cơ sở dữ liệu
	List<UserRole> findAllAdminOrDirector();

	// Xóa một UserRole dựa trên ID của nó
	void delete(Integer id);

}
