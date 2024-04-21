package poly.store.rest.controller;

import java.io.File;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import poly.store.service.UploadService;

@CrossOrigin("*") // Cho phép truy cập từ các domain khác
@RestController // Đánh dấu đây là một REST Controller
public class UploadRestController {
	@Autowired
	UploadService uploadService; // Sử dụng autowiring để inject một đối tượng của UploadService vào controller

	// Xử lý request POST để upload file và lưu vào thư mục cụ thể
	@PostMapping("/rest/upload/{folder}") // Định nghĩa URL endpoint cho việc upload file và lưu vào thư mục cụ thể
	public JsonNode upload(@PathParam("file") MultipartFile file, @PathVariable("folder") String folder) { // Nhận file từ request và thư mục đích để lưu trữ
		File savedFile = uploadService.save(file, folder); // Lưu file vào thư mục cụ thể và nhận lại đối tượng File đã lưu
		ObjectMapper mapper = new ObjectMapper(); // Khởi tạo một đối tượng ObjectMapper để chuyển đổi đối tượng Java thành JSON
		ObjectNode node = mapper.createObjectNode(); // Tạo một ObjectNode để tạo JSON object
		node.put("name", savedFile.getName()); // Thêm tên file vào JSON object
		node.put("size", savedFile.length()); // Thêm kích thước file vào JSON object
		return node; // Trả về JSON object chứa thông tin về file đã upload
	}
}
