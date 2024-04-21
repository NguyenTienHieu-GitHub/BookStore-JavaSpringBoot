package poly.store.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import poly.store.dao.AddressDao;
import poly.store.dao.ProvinceDao;
import poly.store.dao.UserDao;
import poly.store.entity.Address;
import poly.store.entity.District;
import poly.store.entity.Province;
import poly.store.entity.User;
import poly.store.entity.Ward;
import poly.store.model.AddressModel;
import poly.store.service.AddressService;

@Service
@Repository
public class AddressServiceImpl implements AddressService {
	@Autowired
	AddressDao addressDao;

	@Autowired
	UserDao userDao;

	// Phương thức này trả về danh sách địa chỉ dựa trên email của người dùng
	@Override
	public List<Address> findListAddressByEmail(String username) {
		return addressDao.findListAddressByEmail(username);
	}

	// Khởi tạo RestTemplate để gửi yêu cầu HTTP
	RestTemplate rest = new RestTemplate();
	// URL của API dùng để lấy dữ liệu các tỉnh, huyện, xã từ Firebase
	String url = "https://provinces-1b7c6-default-rtdb.firebaseio.com/.json";

	// Phương thức này trả về danh sách các tỉnh
	@Override
	public List<Province> findAllProvince() {
		ProvinceDao list = rest.getForObject(url, ProvinceDao.class);
		return list;
	}

	// Phương thức này trả về danh sách các huyện dựa trên ID tỉnh
	@Override
	public List<District> findDistrictByIdProvince(Integer id) {
		ProvinceDao list = rest.getForObject(url, ProvinceDao.class);
		List<District> listDistrict = list.get(id).getDistricts();
		return listDistrict;
	}

	// Phương thức này trả về danh sách các xã dựa trên ID tỉnh và ID huyện
	@Override
	public List<Ward> findWardByIdProvinceAndIdDistrict(Integer idProvince, Integer idDistrict) {
		ProvinceDao list = rest.getForObject(url, ProvinceDao.class);
		List<District> listDistrict = list.get(idProvince).getDistricts();

		List<Ward> listWard = listDistrict.get(idDistrict).getWards();

		return listWard;
	}

	// Phương thức này tạo mới một địa chỉ
	@Override
	public AddressModel createAddress(AddressModel addressModel) {
		// Lấy thông tin người dùng hiện tại từ Spring Security context
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Lấy thông tin người dùng từ cơ sở dữ liệu
		User temp = userDao.findUserByEmail(username);
		ProvinceDao list = rest.getForObject(url, ProvinceDao.class);
		Province province = list.get(Integer.parseInt(addressModel.getProvince()));
		District district = province.getDistricts().get(Integer.parseInt(addressModel.getDistrict()));
		Ward ward = district.getWards().get(Integer.parseInt(addressModel.getWard()));

		// Lấy thông tin về địa chỉ từ model
		// Lưu ý: Các thông tin như tỉnh/thành phố, quận/huyện, phường/xã đều lấy từ REST API
		// Sử dụng ObjectMapper để chuyển đổi JSON thành đối tượng Java
		Address address = new Address();
		address.setFullname(addressModel.getFullName());
		address.setPhone(addressModel.getPhone());
		address.setDetail(addressModel.getDetail());
		address.setProvince(province.getName());
		address.setDistrict(district.getName());
		address.setWard(ward.getName());
		address.setUser(temp);
		addressDao.save(address);
		return addressModel;
	}

	// Phương thức này trả về một địa chỉ dựa trên ID
	@Override
	public Address getAddressById(int id) {
		return addressDao.findById(id).get();
	}

	// Phương thức này xóa một địa chỉ
	@Override
	public void delete(Address address) {
		addressDao.delete(address);
	}

	// Phương thức này tìm kiếm một địa chỉ dựa trên ID và username
	@Override
	public Address findAddressById(String username, int id) {
		return addressDao.findAddressById(username, id);
	}

	// Phương thức này trả về một địa chỉ dựa trên ID
	@Override
	public AddressModel getOneAddressById(int id) {
		// Lấy thông tin người dùng hiện tại từ Spring Security context
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Lấy thông tin địa chỉ từ cơ sở dữ liệu
		Address address = addressDao.findAddressById(username, id);

		// Chuyển đổi thông tin địa chỉ thành đối tượng AddressModel
		AddressModel addressModel = new AddressModel();

		addressModel.setFullName(address.getFullname());
		addressModel.setPhone(address.getPhone());
		addressModel.setDetail(address.getDetail());

		addressModel.setProvince(address.getProvince());
		addressModel.setDistrict(address.getDistrict());
		addressModel.setWard(address.getWard());

		return addressModel;
	}

	// Phương thức này trả về danh sách các huyện dựa trên ID địa chỉ
	@Override
	public List<District> getListDistrictByAdressId(Integer id) {
		// Lấy thông tin người dùng hiện tại từ Spring Security context
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Lấy thông tin địa chỉ từ cơ sở dữ liệu
		Address address = addressDao.findAddressById(username, id);

		// Gọi REST API để lấy danh sách quận/huyện dựa trên tỉnh/thành phố của địa chỉ
		ProvinceDao list = rest.getForObject(url, ProvinceDao.class);

		List<District> listDistrict = new ArrayList<>();

		for(int i = 0; i<list.size(); i++) {
			if(list.get(i).getName().equals(address.getProvince())) {
				listDistrict = list.get(i).getDistricts();
				break;
			}
		}
		return listDistrict;
	}

	// Phương thức này trả về danh sách các xã dựa trên ID địa chỉ
	@Override
	public List<Ward> getListWardByAdressId(Integer id) {
		// Lấy thông tin người dùng hiện tại từ Spring Security context
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Lấy thông tin địa chỉ từ cơ sở dữ liệu
		Address address = addressDao.findAddressById(username, id);

		// Gọi REST API để lấy danh sách phường/xã dựa trên tỉnh/thành phố và quận/huyện của địa chỉ
		ProvinceDao list = rest.getForObject(url, ProvinceDao.class);

		List<District> listDistrict = new ArrayList<>();
		List<Ward> listWard = new ArrayList<>();

		for(int i = 0; i<list.size(); i++) {
			if(list.get(i).getName().equals(address.getProvince())) {
				listDistrict = list.get(i).getDistricts();
				for(int j = 0; j<listDistrict.size(); j++) {
					if(listDistrict.get(j).getName().equals(address.getDistrict())) {
						listWard = listDistrict.get(j).getWards();
						break;
					}
				}
				break;
			}
		}
		return listWard;
	}

	// Phương thức này cập nhật một địa chỉ
	@Override
	public AddressModel updateAddress(AddressModel addressModel, Integer id) {
		// Lấy thông tin người dùng hiện tại từ Spring Security context
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Gọi REST API để lấy danh sách tỉnh/thành phố
		ProvinceDao list = rest.getForObject(url, ProvinceDao.class);
		Province province = list.get(Integer.parseInt(addressModel.getProvince()));
		District district = province.getDistricts().get(Integer.parseInt(addressModel.getDistrict()));
		Ward ward = district.getWards().get(Integer.parseInt(addressModel.getWard()));
		User temp = userDao.findUserByEmail(username);
		Address address = addressDao.findAddressById(username, id);

		// Cập nhật thông tin địa chỉ từ model vào đối tượng Address
		address.setFullname(addressModel.getFullName());
		address.setPhone(addressModel.getPhone());
		address.setDetail(addressModel.getDetail());
		address.setProvince(province.getName());
		address.setDistrict(district.getName());
		address.setWard(ward.getName());
		address.setUser(temp);
		addressDao.save(address);

		return addressModel;
	}
}
