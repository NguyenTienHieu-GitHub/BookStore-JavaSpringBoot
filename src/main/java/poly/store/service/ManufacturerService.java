package poly.store.service;

import java.util.List;

import poly.store.entity.Manufacturer;
import poly.store.model.ManufacturerModel;

public interface ManufacturerService {

	// Tạo mới một nhà sản xuất từ một đối tượng ManufacturerModel
	ManufacturerModel createManufacturer(ManufacturerModel manufacturerModel);

	// Lấy danh sách tất cả các nhà sản xuất
	List<Manufacturer> findAll();

	// Lấy thông tin của một nhà sản xuất dựa trên ID
	ManufacturerModel getOneManufacturerById(Integer id);

	// Xóa một nhà sản xuất dựa trên ID
	void delete(Integer id);

	// Cập nhật thông tin của một nhà sản xuất từ một đối tượng ManufacturerModel
	ManufacturerModel updateManufacturer(ManufacturerModel manufacturerModel);
}
