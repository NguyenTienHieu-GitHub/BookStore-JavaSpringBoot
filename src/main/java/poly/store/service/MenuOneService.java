package poly.store.service;

import java.util.List;

import poly.store.entity.MenuOne;
import poly.store.model.Nav1Model;

public interface MenuOneService {

	// Tạo mới một mục menu loại 1 từ một đối tượng Nav1Model
	Nav1Model createNav1(Nav1Model nav1Model);

	// Lấy danh sách tất cả các mục menu loại 1
	List<MenuOne> findAll();

	// Xóa một mục menu loại 1 dựa trên ID
	void delete(Integer id);

	// Lấy thông tin của một mục menu loại 1 dựa trên ID
	Nav1Model getOneNav1ById(Integer id);

	// Cập nhật thông tin của một mục menu loại 1 từ một đối tượng Nav1Model
	Nav1Model updateNav1(Nav1Model nav1Model);
}
