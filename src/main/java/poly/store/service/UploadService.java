package poly.store.service;

import java.io.File;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	// Phương thức này lưu trữ tệp tin được tải lên vào thư mục chỉ định và trả về đối tượng File đại diện cho tệp đã lưu.
	File save(MultipartFile file, String folder);
}
