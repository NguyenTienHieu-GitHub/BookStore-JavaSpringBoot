/**
 * @(#)UserRoleDao.java.
 *
 * Version 1.00.
 */
package poly.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.store.entity.UserRole;
/**
 * Interface `UserRoleDao` định nghĩa các phương thức để truy vấn và tương tác với dữ liệu liên quan đến vai trò người dùng.
 *
 * Phương thức `findAllAdminOrDirector` trả về danh sách các UserRole có vai trò là Admin hoặc Director,
 * mà người dùng của chúng không bị xóa.
 */
public interface UserRoleDao extends JpaRepository<UserRole, Integer>{

	/**
	 * Truy vấn để lấy danh sách các UserRole có vai trò là Admin hoặc Director.
	 *
	 * @return danh sách các UserRole có vai trò là Admin hoặc Director
	 */
	@Query("SELECT u FROM UserRole u WHERE (u.role.id = 2 or u.role.id = 3) and u.user.Deleteday = null")
	List<UserRole> findAllAdminOrDirector();
}
