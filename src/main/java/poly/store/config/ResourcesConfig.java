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
 * Class chua cac cau hinh cho he thong
 * 
 *
 *
 */
@Configuration
public class ResourcesConfig {
	/**
	 * Cau hinh file validator.properties
	 * 
	 * @return thong tin ms
	 */
	@Bean("messageSource")
	public MessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
		// Truyen duong dan validator.properties
		ms.setBasename("classpath:messages/validator");
		// Cho phep hien thi tieng viet
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}
}
