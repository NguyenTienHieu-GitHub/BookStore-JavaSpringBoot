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

import poly.store.entity.Manufacturer;
import poly.store.model.ManufacturerModel;
import poly.store.service.ManufacturerService;

@CrossOrigin("*") // Cho phép truy cập từ các domain khác
@RestController // Đánh dấu đây là một REST Controller
@RequestMapping("/rest/manufactures") // Định nghĩa URL cơ sở cho tất cả các request trong controller này
public class ManufacturerRestController {
	@Autowired
	ManufacturerService manufacturerService; // Sử dụng autowiring để inject một đối tượng của ManufacturerService vào controller

	// Xử lý request POST để tạo một nhà sản xuất mới
	@PostMapping("/form") // Định nghĩa URL endpoint cho việc tạo một nhà sản xuất mới
	public ManufacturerModel create(@RequestBody ManufacturerModel manufacturerModel) { // Sử dụng dữ liệu gửi đến từ request body để tạo một nhà sản xuất mới
		return manufacturerService.createManufacturer(manufacturerModel); // Gọi phương thức createManufacturer trong ManufacturerService để tạo một nhà sản xuất mới và trả về kết quả
	}

	// Xử lý request GET để lấy danh sách tất cả các nhà sản xuất
	@GetMapping() // Định nghĩa URL endpoint cho việc lấy danh sách tất cả các nhà sản xuất
	public List<Manufacturer> getAll(){ // Trả về danh sách tất cả các nhà sản xuất
		return manufacturerService.findAll(); // Gọi phương thức findAll trong ManufacturerService để lấy danh sách tất cả các nhà sản xuất và trả về kết quả
	}

	// Xử lý request GET để lấy thông tin của một nhà sản xuất dựa trên id
	@GetMapping("/form/{id}") // Định nghĩa URL endpoint cho việc lấy thông tin của một nhà sản xuất dựa trên id
	public ManufacturerModel getOneUserById(@PathVariable("id") Integer id) { // Lấy id nhà sản xuất từ đường dẫn và trả về thông tin của nhà sản xuất
		return manufacturerService.getOneManufacturerById(id); // Gọi phương thức getOneManufacturerById trong ManufacturerService để lấy thông tin của một nhà sản xuất và trả về kết quả
	}

	// Xử lý request DELETE để xóa một nhà sản xuất dựa trên id
	@DeleteMapping("{id}") // Định nghĩa URL endpoint cho việc xóa một nhà sản xuất dựa trên id
	public void delete(@PathVariable("id") Integer id) { // Lấy id nhà sản xuất từ đường dẫn và xóa nhà sản xuất có id tương ứng
		manufacturerService.delete(id); // Gọi phương thức delete trong ManufacturerService để xóa một nhà sản xuất
	}

	// Xử lý request PUT để cập nhật thông tin của một nhà sản xuất dựa trên id
	@PutMapping("/form/{id}") // Định nghĩa URL endpoint cho việc cập nhật thông tin của một nhà sản xuất dựa trên id
	public ManufacturerModel update(@PathVariable("id") Integer id, @RequestBody ManufacturerModel manufacturerModel) { // Lấy id nhà sản xuất từ đường dẫn và sử dụng dữ liệu gửi đến từ request body để cập nhật thông tin của nhà sản xuất
		return manufacturerService.updateManufacturer(manufacturerModel); // Gọi phương thức updateManufacturer trong ManufacturerService để cập nhật thông tin của một nhà sản xuất và trả về kết quả
	}
}
