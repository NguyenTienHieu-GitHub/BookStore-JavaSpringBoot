package poly.store.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import poly.store.dao.CategoryDao;
import poly.store.dao.MenuOneDao;
import poly.store.dao.UserDao;
import poly.store.entity.Category;
import poly.store.entity.MenuOne;
import poly.store.entity.User;
import poly.store.model.Nav1Model;
import poly.store.service.MenuOneService;

@Service
public class MenuOneServiceImpl implements MenuOneService{
	@Autowired
	MenuOneDao menuOneDao;

	@Autowired
	UserDao userDao;

	@Autowired
	CategoryDao categoryDao;

	// Tạo mới một mục Menu 1
	@Override
	public Nav1Model createNav1(Nav1Model nav1Model) {
		// Lấy thông tin người dùng hiện tại từ SecurityContextHolder
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Tạo timestamp hiện tại
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		// Tìm người dùng theo email
		User temp = userDao.findUserByEmail(username);

		// Tìm category theo ID
		Category category = categoryDao.findById(nav1Model.getCategoryId()).get();

		// Tạo mới đối tượng MenuOne
		MenuOne menuOne = new MenuOne();
		menuOne.setName(nav1Model.getName());
		menuOne.setNamesearch(nav1Model.getNameSearch());
		menuOne.setCategory(category);
		menuOne.setCreateday(timestamp.toString());
		menuOne.setPersoncreate(temp.getId());

		// Lưu đối tượng MenuOne vào cơ sở dữ liệu
		menuOneDao.save(menuOne);
		return nav1Model;
	}

	// Lấy danh sách tất cả MenuOne
	@Override
	public List<MenuOne> findAll() {
		return menuOneDao.getListMenuOne();
	}

	// Xóa một MenuOne
	@Override
	public void delete(Integer id) {
		// Lấy thông tin người dùng hiện tại từ SecurityContextHolder
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User temp = userDao.findUserByEmail(username);
		// Tạo timestamp hiện tại
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		// Lấy thông tin MenuOne cần xóa
		MenuOne entity = menuOneDao.findById(id).get();
		// Cập nhật thông tin người xóa và ngày xóa
		entity.setDeleteday(timestamp.toString());
		entity.setPersondelete(temp.getId());
		// Lưu thông tin cập nhật vào cơ sở dữ liệu
		menuOneDao.save(entity);
	}

	// Lấy thông tin của một MenuOne bằng ID
	@Override
	public Nav1Model getOneNav1ById(Integer id) {
		Nav1Model nav1Model = new Nav1Model();
		MenuOne entity = menuOneDao.findById(id).get();
		nav1Model.setName(entity.getName());
		nav1Model.setNameSearch(entity.getNamesearch());
		nav1Model.setCategoryId(entity.getCategory().getId());
		return nav1Model;
	}

	// Cập nhật thông tin của một MenuOne
	@Override
	public Nav1Model updateNav1(Nav1Model nav1Model) {
		// Tìm category theo ID
		Category category = categoryDao.findById(nav1Model.getCategoryId()).get();

		// Lấy thông tin người dùng hiện tại từ SecurityContextHolder
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Tạo timestamp hiện tại
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		// Tìm người dùng theo email
		User temp = userDao.findUserByEmail(username);

		// Lấy thông tin MenuOne cần cập nhật
		MenuOne entity = menuOneDao.findById(nav1Model.getId()).get();
		// Cập nhật thông tin MenuOne
		entity.setName(nav1Model.getName());
		entity.setNamesearch(nav1Model.getNameSearch());
		entity.setCategory(category);
		entity.setUpdateday(timestamp.toString());
		entity.setPersonupdate(temp.getId());
		// Lưu thông tin cập nhật vào cơ sở dữ liệu
		menuOneDao.save(entity);
		return nav1Model;
	}

}
