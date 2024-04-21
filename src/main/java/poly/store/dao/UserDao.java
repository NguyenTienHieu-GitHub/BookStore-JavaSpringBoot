/**
 * @(#)UserDao.java.
 *
 * Version 1.00.
 */
package poly.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import poly.store.entity.User;

/**
 * Interface `UserDao` định nghĩa các phương thức để truy vấn và tương tác với dữ liệu liên quan đến người dùng.
 *
 * Phương thức `findUserByEmail` trả về đối tượng User dựa trên địa chỉ email.
 *
 * Phương thức `findAllUserAnable` trả về danh sách tất cả người dùng có thể sử dụng, tức là không bị xóa.
 *
 * Phương thức `findAllUserNotRoleAdmin` trả về danh sách tất cả người dùng không có vai trò là admin.
 *
 * Phương thức `getListUserEnableSubscribe` trả về danh sách tất cả người dùng đã kích hoạt tính năng subscribe.
 */
public interface UserDao extends JpaRepository<User, Integer> {

	/**
	 * Truy vấn để tìm kiếm người dùng dựa trên địa chỉ email.
	 *
	 * @param email địa chỉ email của người dùng cần tìm
	 * @return đối tượng User tương ứng với địa chỉ email
	 */
	@Query("SELECT u FROM User u WHERE u.email = :uemail and u.Deleteday = null")
	User findUserByEmail(@Param("uemail") String email);

	/**
	 * Truy vấn để lấy danh sách tất cả người dùng có thể sử dụng, tức là không bị xóa.
	 *
	 * @return danh sách tất cả người dùng có thể sử dụng
	 */
	@Query("SELECT u FROM User u WHERE u.Deleteday = null")
	List<User> findAllUserAnable();

	/**
	 * Truy vấn để lấy danh sách tất cả người dùng không có vai trò là admin.
	 *
	 * @return danh sách tất cả người dùng không có vai trò là admin
	 */
	@Query(value = "SELECT * FROM Users WHERE NOT EXISTS(SELECT * FROM Employees WHERE Users.Id = Employees.User_Id) AND DeleteDay is NULL", nativeQuery = true)
	List<User> findAllUserNotRoleAdmin();

	/**
	 * Truy vấn để lấy danh sách tất cả người dùng đã kích hoạt tính năng subscribe.
	 *
	 * @return danh sách tất cả người dùng đã kích hoạt tính năng subscribe
	 */
	@Query("SELECT u FROM User u WHERE u.Deleteday = null AND u.subscribe = 1")
	List<User> getListUserEnableSubscribe();

}
