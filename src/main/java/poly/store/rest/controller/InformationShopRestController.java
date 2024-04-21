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

import poly.store.entity.InformationShop;
import poly.store.model.ShopModel;
import poly.store.service.InformationShopService;

@CrossOrigin("*") // Cho phép truy cập từ các domain khác
@RestController // Đánh dấu đây là một REST Controller
@RequestMapping("/rest/shop") // Định nghĩa URL cơ sở cho tất cả các request trong controller này
public class InformationShopRestController {
	@Autowired
	InformationShopService informationShopService; // Sử dụng autowiring để inject một đối tượng của InformationShopService vào controller

	// Xử lý request POST để tạo một cửa hàng mới
	@PostMapping("/form") // Định nghĩa URL endpoint cho việc tạo một cửa hàng mới
	public ShopModel create(@RequestBody ShopModel shopModel) { // Sử dụng dữ liệu gửi đến từ request body để tạo một cửa hàng mới
		return informationShopService.createInformationShop(shopModel); // Gọi phương thức createInformationShop trong InformationShopService để tạo một cửa hàng mới và trả về kết quả
	}

	// Xử lý request GET để lấy danh sách tất cả các cửa hàng
	@GetMapping() // Định nghĩa URL endpoint cho việc lấy danh sách tất cả các cửa hàng
	public List<InformationShop> getAll(){ // Trả về danh sách tất cả các cửa hàng
		return informationShopService.findAll(); // Gọi phương thức findAll trong InformationShopService để lấy danh sách tất cả các cửa hàng và trả về kết quả
	}

	// Xử lý request DELETE để xóa một cửa hàng dựa trên id
	@DeleteMapping("{id}") // Định nghĩa URL endpoint cho việc xóa một cửa hàng dựa trên id
	public void delete(@PathVariable("id") Integer id) { // Lấy id cửa hàng từ đường dẫn và xóa cửa hàng có id tương ứng
		informationShopService.delete(id); // Gọi phương thức delete trong InformationShopService để xóa một cửa hàng
	}

	// Xử lý request PUT để cập nhật trạng thái hoạt động của một cửa hàng dựa trên id
	@PutMapping("/form/active/{id}") // Định nghĩa URL endpoint cho việc cập nhật trạng thái hoạt động của một cửa hàng dựa trên id
	public ShopModel update(@PathVariable("id") Integer id, @RequestBody ShopModel shopModel) { // Lấy id cửa hàng từ đường dẫn và sử dụng dữ liệu gửi đến từ request body để cập nhật trạng thái hoạt động của cửa hàng
		return informationShopService.updateActive(shopModel); // Gọi phương thức updateActive trong InformationShopService để cập nhật trạng thái hoạt động của một cửa hàng và trả về kết quả
	}

	// Xử lý request GET để lấy thông tin của một cửa hàng dựa trên id
	@GetMapping("/form/{id}") // Định nghĩa URL endpoint cho việc lấy thông tin của một cửa hàng dựa trên id
	public ShopModel getOneShopById(@PathVariable("id") Integer id) { // Lấy id cửa hàng từ đường dẫn và trả về thông tin của cửa hàng
		return informationShopService.getOneShopById(id); // Gọi phương thức getOneShopById trong InformationShopService để lấy thông tin của một cửa hàng và trả về kết quả
	}

	// Xử lý request PUT để cập nhật thông tin của một cửa hàng dựa trên id
	@PutMapping("/form/{id}") // Định nghĩa URL endpoint cho việc cập nhật thông tin của một cửa hàng dựa trên id
	public ShopModel updateInformation(@PathVariable("id") Integer id, @RequestBody ShopModel shopModel) { // Lấy id cửa hàng từ đường dẫn và sử dụng dữ liệu gửi đến từ request body để cập nhật thông tin của cửa hàng
		return informationShopService.updateInformation(shopModel); // Gọi phương thức updateInformation trong InformationShopService để cập nhật thông tin của một cửa hàng và trả về kết quả
	}
}
