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

import poly.store.entity.Product;
import poly.store.model.ProductModel;
import poly.store.service.ProductService;

@CrossOrigin("*") // Cho phép truy cập từ các domain khác
@RestController // Đánh dấu đây là một REST Controller
@RequestMapping("/rest/product") // Định nghĩa URL cơ sở cho tất cả các request trong controller này
public class ProductRestController {
	@Autowired
	ProductService productService; // Sử dụng autowiring để inject một đối tượng của ProductService vào controller

	// Xử lý request POST để tạo một sản phẩm mới
	@PostMapping("/form") // Định nghĩa URL endpoint cho việc tạo một sản phẩm mới
	public ProductModel create(@RequestBody ProductModel productModel) { // Sử dụng dữ liệu gửi đến từ request body để tạo một sản phẩm mới
		return productService.createProduct(productModel); // Gọi phương thức createProduct trong ProductService để tạo một sản phẩm mới và trả về kết quả
	}

	// Xử lý request GET để lấy danh sách tất cả các sản phẩm
	@GetMapping() // Định nghĩa URL endpoint cho việc lấy danh sách tất cả các sản phẩm
	public List<Product> getAll(){ // Trả về danh sách tất cả các sản phẩm
		return productService.findAll(); // Gọi phương thức findAll trong ProductService để lấy danh sách tất cả các sản phẩm và trả về kết quả
	}

	// Xử lý request DELETE để xóa một sản phẩm dựa trên id
	@DeleteMapping("{id}") // Định nghĩa URL endpoint cho việc xóa một sản phẩm dựa trên id
	public void delete(@PathVariable("id") Integer id) { // Lấy id sản phẩm từ đường dẫn và xóa sản phẩm có id tương ứng
		productService.delete(id); // Gọi phương thức delete trong ProductService để xóa một sản phẩm
	}

	// Xử lý request PUT để cập nhật thông tin của một sản phẩm dựa trên id
	@PutMapping("/form/{id}") // Định nghĩa URL endpoint cho việc cập nhật thông tin của một sản phẩm dựa trên id
	public ProductModel update(@PathVariable("id") Integer id, @RequestBody ProductModel productModel) { // Lấy id sản phẩm từ đường dẫn và sử dụng dữ liệu gửi đến từ request body để cập nhật thông tin của sản phẩm
		return productService.updateProduct(productModel); // Gọi phương thức updateProduct trong ProductService để cập nhật thông tin của một sản phẩm và trả về kết quả
	}

	// Xử lý request GET để lấy thông tin của một sản phẩm dựa trên id
	@GetMapping("/form/{id}") // Định nghĩa URL endpoint cho việc lấy thông tin của một sản phẩm dựa trên id
	public ProductModel getOneProductById(@PathVariable("id") Integer id) { // Lấy id sản phẩm từ đường dẫn và trả về thông tin của sản phẩm
		return productService.getOneProductById(id); // Gọi phương thức getOneProductById trong ProductService để lấy thông tin của một sản phẩm và trả về kết quả
	}
}
