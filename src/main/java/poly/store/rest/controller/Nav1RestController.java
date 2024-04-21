package poly.store.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.store.entity.MenuOne;
import poly.store.model.Nav1Model;
import poly.store.service.MenuOneService;

@CrossOrigin("*") // Cho phép truy cập từ các domain khác
@RestController // Đánh dấu đây là một REST Controller
@RequestMapping("/rest/nav1") // Định nghĩa URL cơ sở cho tất cả các request trong controller này
public class Nav1RestController {
	@Autowired
	MenuOneService menuOneService; // Sử dụng autowiring để inject một đối tượng của MenuOneService vào controller

	// Xử lý request POST để tạo một mục menu cấp 1 mới
	@PostMapping("/form") // Định nghĩa URL endpoint cho việc tạo một mục menu cấp 1 mới
	public Nav1Model create(@RequestBody Nav1Model nav1Model) { // Sử dụng dữ liệu gửi đến từ request body để tạo một mục menu cấp 1 mới
		return menuOneService.createNav1(nav1Model); // Gọi phương thức createNav1 trong MenuOneService để tạo một mục menu cấp 1 mới và trả về kết quả
	}

	// Xử lý request GET để lấy danh sách tất cả các mục menu cấp 1
	@GetMapping() // Định nghĩa URL endpoint cho việc lấy danh sách tất cả các mục menu cấp 1
	public List<MenuOne> getAll(){ // Trả về danh sách tất cả các mục menu cấp 1
		return menuOneService.findAll(); // Gọi phương thức findAll trong MenuOneService để lấy danh sách tất cả các mục menu cấp 1 và trả về kết quả
	}

	// Xử lý request DELETE để xóa một mục menu cấp 1 dựa trên id
	@DeleteMapping("{id}") // Định nghĩa URL endpoint cho việc xóa một mục menu cấp 1 dựa trên id
	public void delete(@PathVariable("id") Integer id) { // Lấy id mục menu cấp 1 từ đường dẫn và xóa mục menu cấp 1 có id tương ứng
		menuOneService.delete(id); // Gọi phương thức delete trong MenuOneService để xóa một mục menu cấp 1
	}

	// Xử lý request GET để lấy thông tin của một mục menu cấp 1 dựa trên id
	@GetMapping("/form/{id}") // Định nghĩa URL endpoint cho việc lấy thông tin của một mục menu cấp 1 dựa trên id
	public Nav1Model getOneNav1ById(@PathVariable("id") Integer id) { // Lấy id mục menu cấp 1 từ đường dẫn và trả về thông tin của mục menu cấp 1
		return menuOneService.getOneNav1ById(id); // Gọi phương thức getOneNav1ById trong MenuOneService để lấy thông tin của một mục menu cấp 1 và trả về kết quả
	}

	// Xử lý request PUT để cập nhật thông tin của một mục menu cấp 1 dựa trên id
	@PutMapping("/form/{id}") // Định nghĩa URL endpoint cho việc cập nhật thông tin của một mục menu cấp 1 dựa trên id
	public Nav1Model update(@PathVariable("id") Integer id, @RequestBody Nav1Model nav1Model) { // Lấy id mục menu cấp 1 từ đường dẫn và sử dụng dữ liệu gửi đến từ request body để cập nhật thông tin của mục menu cấp 1
		return menuOneService.updateNav1(nav1Model); // Gọi phương thức updateNav1 trong MenuOneService để cập nhật thông tin của một mục menu cấp 1 và trả về kết quả
	}
}
