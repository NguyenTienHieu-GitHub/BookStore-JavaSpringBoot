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

import poly.store.entity.User;
import poly.store.model.ChangePassModel;
import poly.store.model.EmployeeForm;
import poly.store.model.InformationModel;
import poly.store.service.UserService;

@CrossOrigin("*") // Cho phép truy cập từ các domain khác
@RestController // Đánh dấu đây là một REST Controller
@RequestMapping("/rest/user") // Định nghĩa URL cơ sở cho tất cả các request trong controller này
public class UserRestController {
	@Autowired
	UserService userService; // Sử dụng autowiring để inject một đối tượng của UserService vào controller

	// Xử lý request GET để lấy thông tin người dùng bằng email
	@GetMapping("{email}") // Định nghĩa URL endpoint cho việc lấy thông tin người dùng bằng email
	public User getUserByEmail(@PathVariable("email") String email) { // Nhận email từ URL path và trả về thông tin người dùng
		return userService.findUserByEmail(email); // Gọi phương thức findUserByEmail trong UserService để lấy thông tin người dùng theo email và trả về kết quả
	}

	// Xử lý request GET để lấy danh sách tất cả người dùng
	@GetMapping() // Định nghĩa URL endpoint cho việc lấy danh sách tất cả người dùng
	public List<User> getAllUser() { // Trả về danh sách tất cả người dùng
		return userService.findAllUserAnable(); // Gọi phương thức findAllUserAnable trong UserService để lấy danh sách tất cả người dùng và trả về kết quả
	}

	// Xử lý request GET để lấy thông tin tài khoản người dùng
	@GetMapping("/account") // Định nghĩa URL endpoint cho việc lấy thông tin tài khoản người dùng
	public InformationModel getUserAccount() { // Trả về thông tin tài khoản người dùng
		return userService.getUserAccount(); // Gọi phương thức getUserAccount trong UserService để lấy thông tin tài khoản người dùng và trả về kết quả
	}

	// Xử lý request POST để tạo mới người dùng
	@PostMapping // Định nghĩa URL endpoint cho việc tạo mới người dùng
	public User create(@RequestBody User user) { // Nhận thông tin người dùng từ request body và tạo mới người dùng
		return userService.create(user); // Gọi phương thức create trong UserService để tạo mới người dùng và trả về kết quả
	}

	// Xử lý request PUT để cập nhật thông tin tài khoản người dùng
	@PutMapping("/account/update") // Định nghĩa URL endpoint cho việc cập nhật thông tin tài khoản người dùng
	public InformationModel update(@RequestBody InformationModel informationModel) { // Nhận thông tin tài khoản người dùng từ request body và cập nhật thông tin
		return userService.update(informationModel); // Gọi phương thức update trong UserService để cập nhật thông tin tài khoản người dùng và trả về kết quả
	}

	// Xử lý request PUT để thay đổi mật khẩu của người dùng
	@PutMapping("/account/change-password") // Định nghĩa URL endpoint cho việc thay đổi mật khẩu của người dùng
	public ChangePassModel changePass(@RequestBody ChangePassModel changePassModel) { // Nhận thông tin mật khẩu mới từ request body và thay đổi mật khẩu
		return userService.updatePass(changePassModel); // Gọi phương thức updatePass trong UserService để thay đổi mật khẩu của người dùng và trả về kết quả
	}

	// Xử lý request GET để lấy danh sách tất cả người dùng không có vai trò admin
	@GetMapping("/list") // Định nghĩa URL endpoint cho việc lấy danh sách tất cả người dùng không có vai trò admin
	public List<User> getAllUserNotRoleAdmin() { // Trả về danh sách tất cả người dùng không có vai trò admin
		return userService.findAllUserNotRoleAdmin(); // Gọi phương thức findAllUserNotRoleAdmin trong UserService để lấy danh sách tất cả người dùng không có vai trò admin và trả về kết quả
	}

	// Xử lý request POST để tạo mới người dùng từ form thông tin
	@PostMapping("/form") // Định nghĩa URL endpoint cho việc tạo mới người dùng từ form thông tin
	public InformationModel createUser(@RequestBody InformationModel informationModel) { // Nhận thông tin người dùng từ request body và tạo mới người dùng
		return userService.createUser(informationModel); // Gọi phương thức createUser trong UserService để tạo mới người dùng từ form thông tin và trả về kết quả
	}

	// Xử lý request DELETE để xóa người dùng
	@DeleteMapping("{id}") // Định nghĩa URL endpoint cho việc xóa người dùng
	public void delete(@PathVariable("id") Integer id) { // Nhận id người dùng từ URL path và xóa người dùng
		userService.deleteUser(id); // Gọi phương thức deleteUser trong UserService để xóa người dùng
	}

	// Xử lý request GET để lấy thông tin người dùng bằng id
	@GetMapping("/update/{id}") // Định nghĩa URL endpoint cho việc lấy thông tin người dùng bằng id
	public InformationModel getOneUserById(@PathVariable("id") Integer id) { // Nhận id người dùng từ URL path và trả về thông tin người dùng
		return userService.getOneUserById(id); // Gọi phương thức getOneUserById trong UserService để lấy thông tin người dùng bằng id và trả về kết quả
	}

	// Xử lý request PUT để cập nhật thông tin người dùng
	@PutMapping("/form/{id}") // Định nghĩa URL endpoint cho việc cập nhật thông tin người dùng
	public InformationModel update(@PathVariable("id") Integer id, @RequestBody InformationModel informationModel) { // Nhận id người dùng từ URL path và thông tin người dùng từ request body, sau đó cập nhật thông tin người dùng
		return userService.updateUser(informationModel, id); // Gọi phương thức updateUser trong UserService để cập nhật thông tin người dùng và trả về kết quả
	}
}
