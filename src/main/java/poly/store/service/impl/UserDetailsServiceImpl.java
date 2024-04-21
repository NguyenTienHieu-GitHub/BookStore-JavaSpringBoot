/**
 * Lớp `UserDetailsServiceImpl` là một service Spring Security được sử dụng để cung cấp thông tin chi tiết về người dùng (user details) từ cơ sở dữ liệu.
 * Implements interface `UserDetailsService` của Spring Security.
 */
package poly.store.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import poly.store.service.RoleService;
import poly.store.service.UserService;

import org.springframework.security.core.userdetails.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService; // Service để tương tác với thông tin người dùng trong cơ sở dữ liệu

	@Autowired
	private RoleService roleService; // Service để tương tác với thông tin quyền của người dùng trong cơ sở dữ liệu

	@Autowired
	private BCryptPasswordEncoder pe; // Bean để mã hóa mật khẩu

	/**
	 * Phương thức được gọi bởi Spring Security để tìm kiếm thông tin chi tiết về người dùng trong cơ sở dữ liệu theo tên đăng nhập (username).
	 *
	 * @param username Tên đăng nhập của người dùng.
	 * @return UserDetails Thông tin chi tiết về người dùng.
	 * @exception UsernameNotFoundException Ném ra nếu không tìm thấy người dùng với tên đăng nhập đã cung cấp trong cơ sở dữ liệu.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		poly.store.entity.User appUser = this.userService.findUserByEmail(username); // Tìm người dùng trong cơ sở dữ liệu bằng email
		if(appUser == null) {
			System.out.println("User not found! "+username);
			throw new UsernameNotFoundException("User " + username + " was not found in database");
		}
		else {
			System.out.println("User found! "+username);
			System.out.println("Password: " + appUser.getPassword());
		}

		List<String> roleNames = this.roleService.getRoleNames(appUser.getId()); // Lấy danh sách tên các quyền của người dùng
		List<GrantedAuthority> grandList = new ArrayList<GrantedAuthority>();

		// Nếu danh sách quyền không rỗng, chuyển đổi các tên quyền thành đối tượng `GrantedAuthority`
		if(roleNames!=null) {
			for(String role: roleNames) {
				System.out.println(role);
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grandList.add(authority);
			}
		}

		// Mã hóa mật khẩu của người dùng trước khi trả về thông tin chi tiết về người dùng
		String password = pe.encode(appUser.getPassword());
		UserDetails userDetails = (UserDetails) new User(appUser.getEmail(), password, grandList);

		return userDetails;
	}

}
