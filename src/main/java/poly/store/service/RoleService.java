package poly.store.service;

import java.util.List;
import poly.store.entity.Role;

public interface RoleService {

	// Tìm kiếm và trả về đối tượng Role dựa trên ID của vai trò.
	Role findRoleById(int roleId);

	// Lấy danh sách tên các vai trò mà người dùng có ID tương ứng là userId đang sở hữu.
	List<String> getRoleNames(int userId);

}
