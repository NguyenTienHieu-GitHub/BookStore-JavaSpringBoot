package poly.store.service;

import java.util.List;
import poly.store.entity.User;
import poly.store.model.ChangePassModel;
import poly.store.model.InformationModel;

public interface UserService {

	// Tìm kiếm người dùng dựa trên địa chỉ email
	User findUserByEmail(String email);

	// Lưu trữ một đối tượng User vào cơ sở dữ liệu
	void save(User user);

	// Lấy danh sách tất cả các User từ cơ sở dữ liệu
	List<User> findAll();

	// Tạo mới một User
	User create(User user);

	// Lấy danh sách tất cả các User có tài khoản hoạt động
	List<User> findAllUserAnable();

	// Lấy thông tin tài khoản của người dùng
	InformationModel getUserAccount();

	// Cập nhật thông tin của người dùng
	InformationModel update(InformationModel informationModel);

	// Cập nhật mật khẩu của người dùng
	ChangePassModel updatePass(ChangePassModel changePassModel);

	// Tìm kiếm người dùng dựa trên ID
	User findById(Integer id);

	// Tạo mới một người dùng
	InformationModel createUser(InformationModel informationModel);

	// Lấy danh sách tất cả các người dùng không có vai trò là Admin
	List<User> findAllUserNotRoleAdmin();

	// Xóa một người dùng dựa trên ID của nó
	void deleteUser(Integer id);

	// Lấy thông tin của một người dùng dựa trên ID
	InformationModel getOneUserById(Integer id);

	// Cập nhật thông tin của một người dùng dựa trên ID
	InformationModel updateUser(InformationModel informationModel, Integer id);

	// Lấy danh sách tất cả các người dùng có thể đăng ký nhận thông báo
	List<User> getListUserEnableSubscribe();

}
