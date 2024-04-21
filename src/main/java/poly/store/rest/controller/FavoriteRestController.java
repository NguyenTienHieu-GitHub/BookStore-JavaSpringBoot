package poly.store.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.store.entity.Favorite;
import poly.store.service.FavoriteService;

@CrossOrigin("*") // Cho phép truy cập từ các domain khác
@RestController // Đánh dấu đây là một REST Controller
@RequestMapping("/rest/favorite") // Định nghĩa URL cơ sở cho tất cả các request trong controller này
public class FavoriteRestController {
	@Autowired
	FavoriteService favoriteService; // Sử dụng autowiring để inject một đối tượng của FavoriteService vào controller

	// Xử lý request POST để thêm một sản phẩm vào danh sách yêu thích
	@PostMapping("/add/{id}") // Định nghĩa URL endpoint cho việc thêm sản phẩm vào danh sách yêu thích
	public Favorite create(@PathVariable("id") int id) { // Lấy id sản phẩm từ đường dẫn và thêm vào danh sách yêu thích
		return favoriteService.create(id); // Gọi phương thức create trong FavoriteService để thêm sản phẩm vào danh sách yêu thích và trả về kết quả
	}

	// Xử lý request GET để lấy thông tin của một sản phẩm yêu thích dựa trên id
	@GetMapping("/{id}") // Định nghĩa URL endpoint cho việc lấy thông tin của một sản phẩm yêu thích
	public Favorite getOneFavorite(@PathVariable("id") int id) { // Lấy id sản phẩm từ đường dẫn và trả về thông tin của sản phẩm yêu thích
		return favoriteService.getOneFavorite(id); // Gọi phương thức getOneFavorite trong FavoriteService để lấy thông tin của sản phẩm yêu thích và trả về kết quả
	}
}
