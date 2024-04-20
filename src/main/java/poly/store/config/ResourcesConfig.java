/**
 * @(#)ResourcesConfig.java.
 *
 * Version 1.00.
 */
package poly.store.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Class cấu hình cho các resources trong ứng dụng.
 */
@Configuration
public class ResourcesConfig {

	/**
	 * Phương thức tạo bean MessageSource để cung cấp các thông điệp (messages) cho ứng dụng.
	 * Thông điệp được lấy từ các tập tin properties trong thư mục classpath:messages/validator.
	 * @return MessageSource - Bean MessageSource đã được cấu hình.
	 */
	@Bean("messageSource")
	public MessageSource getMessageSource() {
		// Tạo một instance của ReloadableResourceBundleMessageSource
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		// Xác định đường dẫn của các tập tin properties chứa thông điệp
		ms.setBasename("classpath:messages/validator");
		// Xác định mã hóa mặc định cho các thông điệp
		ms.setDefaultEncoding("UTF-8");
		// Trả về bean MessageSource đã được cấu hình
		return ms;
	}
}
