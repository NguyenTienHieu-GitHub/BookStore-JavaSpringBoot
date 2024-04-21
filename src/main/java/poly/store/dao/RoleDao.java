/**
 * @(#)RoleDao.java.
 *
 * Version 1.00.
 */
package poly.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import poly.store.entity.Role;
import poly.store.entity.UserRole;
/**
 * Interface `RoleDao` định nghĩa các phương thức để truy vấn và tương tác với dữ liệu liên quan đến các vai trò (roles).
 *
 * Phương thức `getRoleNames` trả về danh sách các tên vai trò của một người dùng dựa trên id của người dùng.
 *
 * Phương thức `getRoleByUserId` trả về đối tượng `UserRole` của một người dùng dựa trên id của người dùng.
 */
public interface RoleDao extends JpaRepository<Role, Integer>{

	/**
	 * Truy vấn để lấy danh sách tên các vai trò của một người dùng dựa trên id của người dùng.
	 *
	 * @param id id của người dùng
	 * @return danh sách các tên vai trò của người dùng
	 */
	@Query("SELECT u.role.name FROM UserRole u WHERE u.user.id = :uid")
	List<String> getRoleNames(@Param("uid") Integer id);

	/**
	 * Truy vấn để lấy đối tượng UserRole của một người dùng dựa trên id của người dùng.
	 *
	 * @param id id của người dùng
	 * @return đối tượng UserRole của người dùng
	 */
	@Query("SELECT u FROM UserRole u WHERE u.user.id = :uid")
	UserRole getRoleByUserId(@Param("uid") Integer id);
}
