package poly.store.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import poly.store.dao.CategoryDao;
import poly.store.dao.UserDao;
import poly.store.entity.Category;
import poly.store.entity.User;
import poly.store.model.CategoryModel;
import poly.store.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryDao categoryDao;

	@Autowired
	UserDao userDao;

	// Phương thức tạo mới một danh mục
	@Override
	public CategoryModel createCategory(CategoryModel categoryModel) {
		// Lấy thông tin người dùng hiện tại từ SecurityContextHolder
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Lấy thời gian hiện tại
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		// Tìm thông tin người dùng dựa trên email
		User temp = userDao.findUserByEmail(username);

		// Tạo một đối tượng Category từ dữ liệu trong CategoryModel
		Category category = new Category();
		category.setName(categoryModel.getName());
		category.setNamesearch(categoryModel.getNameSearch());
		category.setLogo(categoryModel.getLogo());
		category.setBanner(categoryModel.getBanner());
		category.setDescription(categoryModel.getDescribe());
		category.setPersoncreate(temp.getId());
		category.setCreateday(timestamp.toString());
		categoryDao.save(category);
		return categoryModel;
	}

	// Phương thức lấy danh sách tất cả các danh mục
	@Override
	public List<Category> findAll() {
		return categoryDao.getListCategory();
	}

	// Phương thức xóa một danh mục
	@Override
	public void delete(Integer id) {
		// Lấy thông tin người dùng hiện tại từ SecurityContextHolder
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		// Tìm thông tin người dùng dựa trên email
		User temp = userDao.findUserByEmail(username);
		// Lấy thời gian hiện tại
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		// Tìm danh mục dựa trên ID và gán thông tin người xóa và thời gian xóa
		Category category = categoryDao.findById(id).get();
		category.setPersondelete(temp.getId());
		category.setDeleteday(timestamp.toString());
		categoryDao.save(category);
	}

	// Phương thức lấy một danh mục dựa trên ID
	@Override
	public CategoryModel getOneCategoryById(Integer id) {
		// Tìm danh mục dựa trên ID
		Category category = categoryDao.findById(id).get();
		// Tạo một đối tượng CategoryModel và chuyển thông tin từ Category sang CategoryModel
		CategoryModel categoryModel = new CategoryModel();
		categoryModel.setName(category.getName());
		categoryModel.setNameSearch(category.getNamesearch());
		categoryModel.setLogo(category.getLogo());
		categoryModel.setBanner(category.getBanner());
		categoryModel.setDescribe(category.getDescription());
		return categoryModel;
	}

	// Phương thức cập nhật một danh mục
	@Override
	public CategoryModel updateCategory(CategoryModel categoryModel) {
		// Lấy thông tin người dùng hiện tại từ SecurityContextHolder
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Lấy thời gian hiện tại
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		// Tìm thông tin người dùng dựa trên email
		User temp = userDao.findUserByEmail(username);

		// Tìm danh mục dựa trên ID và cập nhật thông tin mới
		Category category = categoryDao.findById(categoryModel.getId()).get();
		category.setName(categoryModel.getName());
		category.setNamesearch(categoryModel.getNameSearch());
		category.setLogo(categoryModel.getLogo());
		category.setBanner(categoryModel.getBanner());
		category.setDescription(categoryModel.getDescribe());
		category.setUpdateday(timestamp.toString());
		category.setPersonupdate(temp.getId());
		categoryDao.save(category);
		return categoryModel;
	}

	// Phương thức lấy một danh mục dựa trên tên
	@Override
	public Category getCategoryByNameSearch(String nameSearch) {
		// Tìm danh mục dựa trên tên
		return categoryDao.getCategoryByNameSearch(nameSearch);
	}
}
