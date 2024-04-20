/**
 * @(#)SecurityConfig.java.
 *
 * Version 1.00.
 */
package poly.store.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import poly.store.service.UserService;
import poly.store.service.impl.UserDetailsServiceImpl;

/**
 * Class cấu hình bảo mật cho ứng dụng sử dụng Spring Security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	// Thong tin User Service
	@Autowired
	UserService userService;

	// Phuong thuc ma hoa mat khau
	@Autowired
	BCryptPasswordEncoder pe;

	// Phuong thuc cap quyen
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	// Thong tin datasource
	@Autowired
	DataSource dataSource;

	/**
	 * Phương thức cấu hình AuthenticationManager để xác thực người dùng.
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	/**
	 * Phương thức cấu hình HTTP Security để quản lý quyền truy cập.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		// Cấu hình quyền truy cập cho các URL dựa trên vai trò của người dùng
		http.authorizeRequests().antMatchers("/admin/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_DIRECTOR')");
		http.authorizeRequests().antMatchers("/shop/profile/**", "/shop/favorite/**", "/shop/cart/checkout", "/account", "/account/**", "/rest/favorite/add/**")
				.access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_DIRECTOR')");
		http.authorizeRequests().anyRequest().permitAll();

		// Xử lý trường hợp người dùng không có quyền truy cập
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403page");

		// Cấu hình trang đăng nhập và đăng xuất
		http.authorizeRequests().and().formLogin().loginPage("/login").usernameParameter("username")
				.passwordParameter("password").failureForwardUrl("/login").defaultSuccessUrl("/login/success", false);
		http.authorizeRequests().and().logout().logoutUrl("/logout").logoutSuccessUrl("/index");

		// Cấu hình ghi nhớ đăng nhập
		http.authorizeRequests().and().rememberMe().tokenValiditySeconds(86400);
	}

	/**
	 * Phương thức tạo bean BCryptPasswordEncoder để mã hóa mật khẩu người dùng.
	 */
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Phương thức cấu hình WebSecurity để bỏ qua việc áp dụng bảo mật cho các request OPTIONS.
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}
}