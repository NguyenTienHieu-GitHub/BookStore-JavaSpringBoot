package poly.store.service;

import java.util.List;

import poly.store.entity.InformationShop;
import poly.store.model.ShopModel;

public interface InformationShopService {

	// Tạo mới thông tin cửa hàng từ một đối tượng ShopModel
	ShopModel createInformationShop(ShopModel shopModel);

	// Lấy danh sách tất cả thông tin cửa hàng
	List<InformationShop> findAll();

	// Xóa thông tin cửa hàng dựa trên ID
	void delete(Integer id);

	// Cập nhật trạng thái hoạt động của cửa hàng từ một đối tượng ShopModel
	ShopModel updateActive(ShopModel shopModel);

	// Lấy thông tin của một cửa hàng dựa trên ID
	ShopModel getOneShopById(Integer id);

	// Cập nhật thông tin cửa hàng từ một đối tượng ShopModel
	ShopModel updateInformation(ShopModel shopModel);

	// Lấy thông tin của cửa hàng duy nhất
	InformationShop getOneInformationShop();
}
