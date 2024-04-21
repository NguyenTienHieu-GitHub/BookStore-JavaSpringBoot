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

import poly.store.entity.MenuTwo;
import poly.store.model.Nav2Model;
import poly.store.service.MenuTwoService;

@CrossOrigin("*") // Cho phép truy cập từ các domain khác
@RestController // Đánh dấu đây là một REST Controller
@RequestMapping("/rest/nav2") // Định nghĩa URL cơ sở cho tất cả các request trong controller này
public class Nav2RestController {
	@Autowired
	MenuTwoService menuTwoService; // Sử dụng autowiring để inject một đối tượng của MenuTwoService vào controller

	// Xử lý request POST để tạo một mục menu cấp 2 mới
	@PostMapping("/form") // Định nghĩa URL endpoint cho việc tạo một mục menu cấp 2 mới
	public Nav2Model create(@RequestBody Nav2Model nav2Model) { // Sử dụng dữ liệu gửi đến từ request body để tạo một mục menu cấp 2 mới
		return menuTwoService.createNav2(nav2Model); // Gọi phương thức createNav2 trong MenuTwoService để tạo một mục menu cấp 2 mới và trả về kết quả
	}

	// Xử lý request GET để lấy danh sách tất cả các mục menu cấp 2
	@GetMapping() // Định nghĩa URL endpoint cho việc lấy danh sách tất cả các mục menu cấp 2
	public List<MenuTwo> getAll(){ // Trả về danh sách tất cả các mục menu cấp 2
		return menuTwoService.findAll(); // Gọi phương thức findAll trong MenuTwoService để lấy danh sách tất cả các mục menu cấp 2 và trả về kết quả
	}

	// Xử lý request DELETE để xóa một mục menu cấp 2 dựa trên id
	@DeleteMapping("{id}") // Định nghĩa URL endpoint cho việc xóa một mục menu cấp 2 dựa trên id
	public void delete(@PathVariable("id") Integer id) { // Lấy id mục menu cấp 2 từ đường dẫn và xóa mục menu cấp 2 có id tương ứng
		menuTwoService.delete(id); // Gọi phương thức delete trong MenuTwoService để xóa một mục menu cấp 2
	}

	// Xử lý request GET để lấy thông tin của một mục menu cấp 2 dựa trên id
	@GetMapping("/form/{id}") // Định nghĩa URL endpoint cho việc lấy thông tin của một mục menu cấp 2 dựa trên id
	public Nav2Model getOneNav2ById(@PathVariable("id") Integer id) { // Lấy id mục menu cấp 2 từ đường dẫn và trả về thông tin của mục menu cấp 2
		return menuTwoService.getOneNav2ById(id); // Gọi phương thức getOneNav2ById trong MenuTwoService để lấy thông tin của một mục menu cấp 2 và trả về kết quả
	}

	// Xử lý request PUT để cập nhật thông tin của một mục menu cấp 2 dựa trên id
	@PutMapping("/form/{id}") // Định nghĩa URL endpoint cho việc cập nhật thông tin của một mục menu cấp 2 dựa trên id
	public Nav2Model update(@PathVariable("id") Integer id, @RequestBody Nav2Model nav2Model) { // Lấy id mục menu cấp 2 từ đường dẫn và sử dụng dữ liệu gửi đến từ request body để cập nhật thông tin của mục menu cấp 2
		return menuTwoService.updateNav2(nav2Model); // Gọi phương thức updateNav2 trong MenuTwoService để cập nhật thông tin của một mục menu cấp 2 và trả về kết quả
	}
}
