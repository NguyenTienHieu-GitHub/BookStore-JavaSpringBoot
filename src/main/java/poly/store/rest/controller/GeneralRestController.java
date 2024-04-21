package poly.store.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.store.model.EmployeeForm;
import poly.store.service.GeneralService;

@CrossOrigin("*") // Cho phép truy cập từ các domain khác
@RestController // Đánh dấu đây là một REST Controller
@RequestMapping("/rest/form/employee") // Định nghĩa URL cơ sở cho tất cả các request trong controller này
public class GeneralRestController {
	@Autowired
	GeneralService generalService; // Sử dụng autowiring để inject một đối tượng của GeneralService vào controller

	// Xử lý request POST để tạo một nhân viên mới
	@PostMapping // Định nghĩa URL endpoint cho việc tạo một nhân viên mới
	public EmployeeForm create(@RequestBody EmployeeForm employee) { // Sử dụng dữ liệu gửi đến từ request body để tạo một nhân viên mới
		return generalService.createEmployee(employee); // Gọi phương thức createEmployee trong GeneralService để tạo một nhân viên mới và trả về kết quả
	}

	// Xử lý request GET để lấy thông tin của một nhân viên dựa trên id
	@GetMapping("{id}") // Định nghĩa URL endpoint cho việc lấy thông tin của một nhân viên dựa trên id
	public EmployeeForm getOneUserById(@PathVariable("id") Integer id) { // Lấy id nhân viên từ đường dẫn và trả về thông tin của nhân viên
		return generalService.getOneUserById(id); // Gọi phương thức getOneUserById trong GeneralService để lấy thông tin của một nhân viên và trả về kết quả
	}

	// Xử lý request PUT để cập nhật thông tin của một nhân viên dựa trên id
	@PutMapping("{id}") // Định nghĩa URL endpoint cho việc cập nhật thông tin của một nhân viên dựa trên id
	public EmployeeForm update(@PathVariable("id") Integer id, @RequestBody EmployeeForm employeeForm) { // Lấy id nhân viên từ đường dẫn và sử dụng dữ liệu gửi đến từ request body để cập nhật thông tin của nhân viên
		return generalService.updateEmployee(employeeForm); // Gọi phương thức updateEmployee trong GeneralService để cập nhật thông tin của một nhân viên và trả về kết quả
	}
}
