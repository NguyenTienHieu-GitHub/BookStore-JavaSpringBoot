package poly.store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import poly.store.interceptor.GlobalInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	@Autowired
	GlobalInterceptor globalInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// Đăng ký interceptor globalInterceptor và cấu hình các path pattern
		registry.addInterceptor(globalInterceptor)
				// Áp dụng interceptor cho mọi request (pattern: "/**")
				.addPathPatterns("/**")
				// Loại trừ interceptor khỏi các request tới các endpoint sau đây
				.excludePathPatterns("/rest/**", "/admin/**", "/assets/**");
	}

}
