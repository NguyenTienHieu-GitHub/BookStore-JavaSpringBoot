package poly.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.store.dao.RoleDao;
import poly.store.entity.Role;
import poly.store.service.RoleService;

/**
 * Implementasi của giao diện RoleService.
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDao roleDao;

	/**
	 * Lấy thông tin vai trò dựa trên ID.
	 *
	 * @param roleId ID của vai trò cần lấy thông tin.
	 * @return Đối tượng vai trò tương ứng với ID cho trước.
	 */
	@Override
	public Role findRoleById(int roleId) {
		return roleDao.findById(roleId).get();
	}

	/**
	 * Lấy danh sách tên các vai trò cho một người dùng dựa trên ID của người dùng.
	 *
	 * @param userId ID của người dùng để lấy danh sách tên vai trò.
	 * @return Danh sách tên các vai trò liên kết với người dùng.
	 */
	@Override
	public List<String> getRoleNames(int userId) {
		return roleDao.getRoleNames(userId);
	}

}
