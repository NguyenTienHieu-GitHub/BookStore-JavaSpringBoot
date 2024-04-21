/**
 * Giao diện EmployeeDao mô tả các phương thức để truy xuất và thực hiện các thao tác liên quan đến đối tượng Employee trong cơ sở dữ liệu.
 */
package poly.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import poly.store.entity.Employee;
import poly.store.model.EmployeeModel;

// Giao diện này mở rộng từ JpaRepository, cung cấp các phương thức để thực hiện các thao tác CRUD trên các đối tượng Employee
public interface EmployeeDao extends JpaRepository<Employee, Integer> {

	// Phương thức truy vấn tùy chỉnh để lấy danh sách các nhân viên chưa bị xóa và chuyển đổi thành đối tượng EmployeeModel
	@Query("SELECT new EmployeeModel(e.user.Fullname, e.department, e.phone, e.position, e.user.birthday, e.Startday, e.salary) FROM Employee e WHERE e.Deleteday = null")
	List<EmployeeModel> getListEmployee();

	// Phương thức truy vấn tùy chỉnh để lấy thông tin nhân viên dựa trên ID người dùng
	@Query("SELECT e FROM Employee e WHERE e.user.id = :uid")
	Employee getEmployeeByUserId(@Param("uid") Integer id);
}
