package poly.store.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import poly.store.dao.MenuOneDao;
import poly.store.dao.MenuTwoDao;
import poly.store.dao.UserDao;
import poly.store.entity.MenuOne;
import poly.store.entity.MenuTwo;
import poly.store.entity.User;
import poly.store.model.Nav2Model;
import poly.store.service.MenuTwoService;

@Service
public class MenuTwoServiceImpl implements MenuTwoService{
	@Autowired
	UserDao userDao;

	@Autowired
	MenuOneDao menuOneDao;

	@Autowired
	MenuTwoDao menuTwoDao;

	// Tạo mới một mục Menu 2
	@Override
	public Nav2Model createNav2(Nav2Model nav2Model) {
		// Lấy thông tin người dùng hiện tại từ SecurityContextHolder
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Tạo timestamp hiện tại
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		// Tìm người dùng theo email
		User temp = userDao.findUserByEmail(username);

		// Tìm MenuOne theo ID
		MenuOne menuOne = menuOneDao.findById(nav2Model.getNav1Id()).get();

		// Tạo mới đối tượng MenuTwo
		MenuTwo menuTwo = new MenuTwo();

		menuTwo.setName(nav2Model.getName());
		menuTwo.setNamesearch(nav2Model.getNameSearch());
		menuTwo.setMenuOne(menuOne);
		menuTwo.setCreateday(timestamp.toString());
		menuTwo.setPersoncreate(temp.getId());

		// Lưu đối tượng MenuTwo vào cơ sở dữ liệu
		menuTwoDao.save(menuTwo);

		return nav2Model;
	}

	// Lấy danh sách tất cả MenuTwo
	@Override
	public List<MenuTwo> findAll() {
		return menuTwoDao.getListMenuTwo();
	}

	// Xóa một MenuTwo
	@Override
	public void delete(Integer id) {
		// Lấy thông tin người dùng hiện tại từ SecurityContextHolder
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User temp = userDao.findUserByEmail(username);
		// Tạo timestamp hiện tại
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		// Lấy thông tin MenuTwo cần xóa
		MenuTwo menuTwo = menuTwoDao.findById(id).get();
		// Cập nhật thông tin người xóa và ngày xóa
		menuTwo.setDeleteday(timestamp.toString());
		menuTwo.setPersondelete(temp.getId());
		// Lưu thông tin cập nhật vào cơ sở dữ liệu
		menuTwoDao.save(menuTwo);
	}

	// Lấy thông tin của một MenuTwo bằng ID
	@Override
	public Nav2Model getOneNav2ById(Integer id) {
		Nav2Model nav2Model = new Nav2Model();
		MenuTwo menuTwo = menuTwoDao.findById(id).get();
		nav2Model.setName(menuTwo.getName());
		nav2Model.setNameSearch(menuTwo.getNamesearch());
		nav2Model.setNav1Id(menuTwo.getMenuOne().getId());
		return nav2Model;
	}

	// Cập nhật thông tin của một MenuTwo
	@Override
	public Nav2Model updateNav2(Nav2Model nav2Model) {
		// Tìm MenuOne theo ID
		MenuOne menuOne = menuOneDao.findById(nav2Model.getNav1Id()).get();

		// Lấy thông tin người dùng hiện tại từ SecurityContextHolder
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Tạo timestamp hiện tại
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		// Tìm người dùng theo email
		User temp = userDao.findUserByEmail(username);

		// Lấy thông tin MenuTwo cần cập nhật
		MenuTwo menuTwo = menuTwoDao.findById(nav2Model.getId()).get();
		// Cập nhật thông tin MenuTwo
		menuTwo.setName(nav2Model.getName());
		menuTwo.setNamesearch(nav2Model.getNameSearch());
		menuTwo.setMenuOne(menuOne);
		menuTwo.setPersonupdate(temp.getId());
		menuTwo.setUpdateday(timestamp.toString());
		// Lưu thông tin cập nhật vào cơ sở dữ liệu
		menuTwoDao.save(menuTwo);
		return nav2Model;
	}
}
