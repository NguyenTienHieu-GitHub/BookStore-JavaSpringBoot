package poly.store.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.store.entity.District;
import poly.store.entity.Province;
import poly.store.entity.Ward;
import poly.store.model.AddressModel;
import poly.store.service.AddressService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest")
public class AddressRestController {

	@Autowired
	AddressService addressService;

	// API để lấy danh sách các tỉnh/thành phố
	@GetMapping("/province")
	public List<Province> list(){
		return addressService.findAllProvince();
	}

	// API để lấy danh sách quận/huyện theo id của tỉnh/thành phố
	@GetMapping("/district/{id}")
	public List<District> listDistrict(@PathVariable("id") Integer id){
		return addressService.findDistrictByIdProvince(id);
	}

	// API để lấy danh sách xã/phường theo id của tỉnh/thành phố và id của quận/huyện
	@GetMapping("/ward/{idProvince}/{idDistrict}")
	public List<Ward> listWard(@PathVariable("idProvince") Integer idProvince, @PathVariable("idDistrict") Integer idDistrict){
		return addressService.findWardByIdProvinceAndIdDistrict(idProvince, idDistrict);
	}

	// API để tạo mới một địa chỉ
	@PostMapping("/address/form")
	public AddressModel create(@RequestBody AddressModel addressModel) {
		return addressService.createAddress(addressModel);
	}

	// API để lấy một địa chỉ dựa trên id
	@GetMapping("/address/update/{id}")
	public AddressModel getOneAddressById(@PathVariable("id") int id) {
		return addressService.getOneAddressById(id);
	}

	// API để lấy danh sách quận/huyện dựa trên id của địa chỉ
	@GetMapping("/update/district/{id}")
	public List<District> getListDistrictById(@PathVariable("id") Integer id){
		return addressService.getListDistrictByAdressId(id);
	}

	// API để lấy danh sách xã/phường dựa trên id của địa chỉ
	@GetMapping("/update/ward/{id}")
	public List<Ward> getListWardById(@PathVariable("id") Integer id){
		return addressService.getListWardByAdressId(id);
	}

	// API để cập nhật thông tin một địa chỉ
	@PutMapping("/address/form/{id}")
	public AddressModel update(@PathVariable("id") Integer id, @RequestBody AddressModel addressModel) {
		return addressService.updateAddress(addressModel, id);
	}
}
