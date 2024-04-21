package poly.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.store.entity.InformationShop;

// Giao diện này mở rộng từ JpaRepository, cung cấp các phương thức để thực hiện các thao tác CRUD trên các đối tượng InformationShop
public interface InformationShopDao extends JpaRepository<InformationShop, Integer>{

	// Truy vấn tùy chỉnh để lấy danh sách thông tin cửa hàng chưa bị xóa
	@Query("SELECT i FROM InformationShop i WHERE i.Deleteday = null")
	List<InformationShop> getListInformationShop();

	// Truy vấn tùy chỉnh để lấy thông tin một cửa hàng chưa bị xóa và đang hoạt động
	@Query("SELECT i FROM InformationShop i WHERE i.Deleteday = null and i.active = 1")
	InformationShop getOneInformationShop();
}
