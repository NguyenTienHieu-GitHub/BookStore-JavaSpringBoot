package poly.store.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import poly.store.service.UploadService;

/**
 * Implementasi của giao diện UploadService, được sử dụng để lưu trữ file tải lên từ người dùng.
 */
@Service
public class UploadServiceImpl implements UploadService {

	@Autowired
	ServletContext app; // Đối tượng ServletContext để lấy đường dẫn thư mục lưu trữ

	/**
	 * Phương thức để lưu trữ file tải lên vào thư mục được chỉ định.
	 *
	 * @param file   Đối tượng MultipartFile chứa file được tải lên.
	 * @param folder Thư mục trong đó file sẽ được lưu trữ.
	 * @return Đối tượng File đã được lưu trữ.
	 */
	@Override
	public File save(MultipartFile file, String folder) {
		// Tạo thư mục nếu không tồn tại
		File dir = new File("src/main/resources/static/assets/images/" + folder);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		// Lấy tên gốc của file
		String name = file.getOriginalFilename();
		try {
			// Tạo file lưu trữ trong thư mục đã chỉ định
			File savedFile = new File(dir, name);

			// Tạo luồng ghi để ghi dữ liệu từ file tải lên vào file lưu trữ
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(savedFile));
			stream.write(file.getBytes());
			stream.close();

			// Trả về đối tượng File đã được lưu trữ
			return savedFile;
		} catch (Exception e) {
			// Ném ra ngoại lệ nếu có lỗi xảy ra trong quá trình lưu trữ
			throw new RuntimeException(e);
		}
	}
}
