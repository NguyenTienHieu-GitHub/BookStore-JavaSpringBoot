package poly.store.service;

import java.util.List;

import poly.store.entity.Address;
import poly.store.entity.District;
import poly.store.entity.Province;
import poly.store.entity.Ward;
import poly.store.model.AddressModel;

public interface AddressService {
	// Lấy danh sách địa chỉ dựa trên email của người dùng
	List<Address> findListAddressByEmail(String username);

	// Lấy danh sách tất cả các tỉnh/thành phố
	List<Province> findAllProvince();

	// Lấy danh sách quận/huyện dựa trên ID của tỉnh/thành phố
	List<District> findDistrictByIdProvince(Integer id);

	// Lấy danh sách phường/xã dựa trên ID của tỉnh/thành phố và ID của quận/huyện
	List<Ward> findWardByIdProvinceAndIdDistrict(Integer idProvince, Integer idDistrict);

	// Tạo mới một địa chỉ từ một đối tượng AddressModel
	AddressModel createAddress(AddressModel addressModel);

	// Lấy thông tin địa chỉ dựa trên ID
	Address getAddressById(int parseInt);

	// Xóa một địa chỉ
	void delete(Address address);

	// Tìm kiếm địa chỉ dựa trên username và ID
	Address findAddressById(String username, int id);

	// Lấy một địa chỉ dựa trên ID và chuyển đổi sang đối tượng AddressModel
	AddressModel getOneAddressById(int id);

	// Lấy danh sách quận/huyện dựa trên ID của địa chỉ
	List<District> getListDistrictByAdressId(Integer id);

	// Lấy danh sách phường/xã dựa trên ID của địa chỉ
	List<Ward> getListWardByAdressId(Integer id);

	// Cập nhật thông tin địa chỉ dựa trên AddressModel và ID của địa chỉ
	AddressModel updateAddress(AddressModel addressModel, Integer id);
}
