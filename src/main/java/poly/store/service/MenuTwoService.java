package poly.store.service;

import java.util.List;
import poly.store.entity.MenuTwo;
import poly.store.model.Nav2Model;

public interface MenuTwoService {

	// Tạo mới một mục menu loại 2 từ một đối tượng Nav2Model
	Nav2Model createNav2(Nav2Model nav2Model);

	// Lấy danh sách tất cả các mục menu loại 2
	List<MenuTwo> findAll();

	// Xóa một mục menu loại 2 dựa trên ID
	void delete(Integer id);

	// Lấy thông tin của một mục menu loại 2 dựa trên ID
	Nav2Model getOneNav2ById(Integer id);

	// Cập nhật thông tin của một mục menu loại 2 từ một đối tượng Nav2Model
	Nav2Model updateNav2(Nav2Model nav2Model);
}
