package poly.store.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.store.entity.UserRole;
import poly.store.service.UserRoleService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/employees")
public class EmployeeRestController {

	@Autowired
	UserRoleService userRoleService;

	// API để lấy danh sách tất cả các vai trò nhân viên (admin hoặc director)
	@GetMapping()
	public List<UserRole> getAll() {
		return userRoleService.findAllAdminOrDirector();
	}

	// API để xóa một vai trò nhân viên dựa trên id
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		userRoleService.delete(id);
	}
}
