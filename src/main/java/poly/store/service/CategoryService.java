package poly.store.service;

import java.util.List;

import poly.store.entity.Category;
import poly.store.model.CategoryModel;

public interface CategoryService {

	// Tạo mới một danh mục từ một đối tượng CategoryModel
	CategoryModel createCategory(CategoryModel categoryModel);

	// Lấy danh sách tất cả các danh mục
	List<Category> findAll();

	// Xóa một danh mục dựa trên ID
	void delete(Integer id);

	// Lấy thông tin một danh mục dựa trên ID và chuyển đổi sang đối tượng CategoryModel
	CategoryModel getOneCategoryById(Integer id);

	// Cập nhật thông tin về một danh mục từ một đối tượng CategoryModel
	CategoryModel updateCategory(CategoryModel categoryModel);

	// Tìm kiếm một danh mục dựa trên tên
	Category getCategoryByNameSearch(String nameSearch);
}
