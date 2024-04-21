package poly.store.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import poly.store.dao.UserDao;
import poly.store.entity.User;
import poly.store.model.ChangePassModel;
import poly.store.model.InformationModel;
import poly.store.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao; // DAO layer để tương tác với dữ liệu của bảng User

	/**
	 * Tìm kiếm người dùng bằng email.
	 *
	 * @param email Địa chỉ email của người dùng cần tìm kiếm.
	 * @return User Người dùng tìm thấy hoặc null nếu không tìm thấy.
	 */
	@Override
	public User findUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}

	/**
	 * Lưu thông tin người dùng vào cơ sở dữ liệu.
	 *
	 * @param user Đối tượng User cần lưu.
	 */
	@Override
	public void save(User user) {
		userDao.save(user);
	}

	/**
	 * Lấy danh sách tất cả các người dùng từ cơ sở dữ liệu.
	 *
	 * @return List<User> Danh sách các người dùng.
	 */
	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	/**
	 * Tạo mới một người dùng và lưu vào cơ sở dữ liệu.
	 *
	 * @param user Đối tượng User mới cần tạo.
	 * @return User Người dùng đã được tạo.
	 */
	@Override
	public User create(User user) {
		return userDao.save(user);
	}

	/**
	 * Lấy danh sách tất cả các người dùng có tài khoản được kích hoạt từ cơ sở dữ liệu.
	 *
	 * @return List<User> Danh sách các người dùng có tài khoản được kích hoạt.
	 */
	@Override
	public List<User> findAllUserAnable() {
		return userDao.findAllUserAnable();
	}

	/**
	 * Lấy thông tin tài khoản của người dùng hiện tại.
	 *
	 * @return InformationModel Thông tin tài khoản của người dùng.
	 */
	@Override
	public InformationModel getUserAccount() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		User user = userDao.findUserByEmail(username);

		InformationModel information = new InformationModel();

		information.setPassword(user.getPassword());
		information.setFullName(user.getFullname());
		information.setEmail(user.getEmail());
		information.setBirthday(user.getBirthday());
		information.setGender(user.getSex());
		information.setNews(user.getSubscribe());

		return information;
	}

	/**
	 * Cập nhật thông tin của người dùng hiện tại.
	 *
	 * @param informationModel Đối tượng InformationModel chứa thông tin cần cập nhật.
	 * @return InformationModel Đối tượng InformationModel đã được cập nhật.
	 */
	@Override
	public InformationModel update(InformationModel informationModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		User user = userDao.findUserByEmail(username);

		user.setFullname(informationModel.getFullName());
		user.setBirthday(informationModel.getBirthday());
		user.setSubscribe(informationModel.getNews());
		user.setSex(informationModel.getGender());

		userDao.save(user);

		return informationModel;
	}

	/**
	 * Cập nhật mật khẩu của người dùng hiện tại.
	 *
	 * @param changePassModel Đối tượng ChangePassModel chứa thông tin cần cập nhật mật khẩu.
	 * @return ChangePassModel Đối tượng ChangePassModel đã được cập nhật.
	 */
	@Override
	public ChangePassModel updatePass(ChangePassModel changePassModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		User user = userDao.findUserByEmail(username);

		user.setPassword(changePassModel.getNewPass());

		userDao.save(user);

		return changePassModel;
	}

	/**
	 * Lấy thông tin người dùng dựa trên ID.
	 *
	 * @param id ID của người dùng cần lấy thông tin.
	 * @return User Người dùng có ID tương ứng.
	 */
	@Override
	public User findById(Integer id) {
		return userDao.findById(id).get();
	}

	/**
	 * Tạo mới một người dùng và lưu vào cơ sở dữ liệu.
	 *
	 * @param informationModel Đối tượng InformationModel chứa thông tin người dùng cần tạo.
	 * @return InformationModel Đối tượng InformationModel đã được tạo.
	 */
	@Override
	public InformationModel createUser(InformationModel informationModel) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		User user = new User();

		user.setEmail(informationModel.getEmail());
		user.setFullname(informationModel.getFullName());
		user.setBirthday(informationModel.getBirthday());
		user.setSubscribe(informationModel.getNews());
		user.setSex(informationModel.getGender());
		user.setPassword("1234567");

		user.setCreateday(timestamp.toString());

		userDao.save(user);

		return informationModel;
	}

	/**
	 * Lấy danh sách tất cả các người dùng không phải là admin từ cơ sở dữ liệu.
	 *
	 * @return List<User> Danh sách các người dùng không phải là admin.
	 */
	@Override
	public List<User> findAllUserNotRoleAdmin() {
		return userDao.findAllUserNotRoleAdmin();
	}

	/**
	 * Xóa người dùng từ cơ sở dữ liệu theo ID.
	 *
	 * @param id ID của người dùng cần xóa.
	 */
	@Override
	public void deleteUser(Integer id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		User temp = userDao.findUserByEmail(username);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User user = userDao.findById(id).get();

		user.setDeleteday(timestamp.toString());
		user.setPersondelete(temp.getId());
		userDao.save(user);
	}

	/**
	 * Lấy thông tin người dùng dựa trên ID.
	 *
	 * @param id ID của người dùng cần lấy thông tin.
	 * @return InformationModel Thông tin của người dùng có ID tương ứng.
	 */
	@Override
	public InformationModel getOneUserById(Integer id) {
		User user = userDao.findById(id).get();
		InformationModel information = new InformationModel();

		information.setFullName(user.getFullname());
		information.setEmail(user.getEmail());
		information.setGender(user.getSex());
		information.setNews(user.getSubscribe());
		information.setBirthday(user.getBirthday());

		return information;
	}

	/**
	 * Cập nhật thông tin của người dùng dựa trên ID.
	 *
	 * @param informationModel Đối tượng InformationModel chứa thông tin cần cập nhật.
	 * @param id ID của người dùng cần cập nhật thông tin.
	 * @return InformationModel Đối tượng InformationModel đã được cập nhật.
	 */
	@Override
	public InformationModel updateUser(InformationModel informationModel, Integer id) {

		User user = userDao.findById(id).get();

		user.setFullname(informationModel.getFullName());
		user.setBirthday(informationModel.getBirthday());
		user.setSubscribe(informationModel.getNews());
		user.setSex(informationModel.getGender());

		userDao.save(user);

		return informationModel;
	}

	/**
	 * Lấy danh sách tất cả các người dùng có tài khoản được kích hoạt và đăng ký nhận thông báo từ cơ sở dữ liệu.
	 *
	 * @return List<User> Danh sách các người dùng có tài khoản được kích hoạt và đăng ký nhận thông báo.
	 */
	@Override
	public List<User> getListUserEnableSubscribe() {
		return userDao.getListUserEnableSubscribe();
	}
}
