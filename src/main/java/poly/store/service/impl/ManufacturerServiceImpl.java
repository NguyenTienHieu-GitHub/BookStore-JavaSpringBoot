package poly.store.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import poly.store.dao.ManufacturerDao;
import poly.store.dao.UserDao;
import poly.store.entity.Manufacturer;
import poly.store.entity.User;
import poly.store.model.ManufacturerModel;
import poly.store.service.ManufacturerService;

@Service
public class ManufacturerServiceImpl implements ManufacturerService{
	@Autowired
	ManufacturerDao manufacturerDao;

	@Autowired
	UserDao userDao;

	// Tạo mới nhà sản xuất
	@Override
	public ManufacturerModel createManufacturer(ManufacturerModel manufacturerModel) {
		// Lấy thông tin người dùng hiện tại từ SecurityContextHolder
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Tạo timestamp hiện tại
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		// Tìm người dùng theo email
		User temp = userDao.findUserByEmail(username);

		// Tạo mới đối tượng nhà sản xuất
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName(manufacturerModel.getName());
		manufacturer.setLogo(manufacturerModel.getLogo());
		manufacturer.setBanner(manufacturerModel.getBanner());
		manufacturer.setDescription(manufacturerModel.getDescribe());
		manufacturer.setPersoncreate(temp.getId());
		manufacturer.setCreateday(timestamp.toString());
		// Lưu đối tượng nhà sản xuất vào cơ sở dữ liệu
		manufacturerDao.save(manufacturer);
		return manufacturerModel;
	}

	// Lấy danh sách tất cả nhà sản xuất
	@Override
	public List<Manufacturer> findAll() {
		return manufacturerDao.getListManufacturer();
	}

	// Lấy thông tin của một nhà sản xuất bằng ID
	@Override
	public ManufacturerModel getOneManufacturerById(Integer id) {
		Manufacturer manufacturer = manufacturerDao.findById(id).get();
		ManufacturerModel manufacturerModel = new ManufacturerModel();
		manufacturerModel.setName(manufacturer.getName());
		manufacturerModel.setLogo(manufacturer.getLogo());
		manufacturerModel.setBanner(manufacturer.getBanner());
		manufacturerModel.setDescribe(manufacturer.getDescription());
		return manufacturerModel;
	}

	// Xóa một nhà sản xuất
	@Override
	public void delete(Integer id) {
		// Lấy thông tin người dùng hiện tại từ SecurityContextHolder
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User temp = userDao.findUserByEmail(username);
		// Tạo timestamp hiện tại
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		// Lấy thông tin nhà sản xuất cần xóa
		Manufacturer manufacturer = manufacturerDao.findById(id).get();
		// Cập nhật thông tin người xóa và ngày xóa
		manufacturer.setPersondelete(temp.getId());
		manufacturer.setDeleteday(timestamp.toString());
		// Lưu thông tin cập nhật vào cơ sở dữ liệu
		manufacturerDao.save(manufacturer);
	}

	// Cập nhật thông tin của một nhà sản xuất
	@Override
	public ManufacturerModel updateManufacturer(ManufacturerModel manufacturerModel) {
		// Lấy thông tin người dùng hiện tại từ SecurityContextHolder
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Tạo timestamp hiện tại
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		// Tìm người dùng theo email
		User temp = userDao.findUserByEmail(username);

		// Lấy thông tin nhà sản xuất cần cập nhật
		Manufacturer manufacturer = manufacturerDao.findById(manufacturerModel.getId()).get();
		// Cập nhật thông tin nhà sản xuất
		manufacturer.setName(manufacturerModel.getName());
		manufacturer.setLogo(manufacturerModel.getLogo());
		manufacturer.setBanner(manufacturerModel.getBanner());
		manufacturer.setDescription(manufacturerModel.getDescribe());
		manufacturer.setUpdateday(timestamp.toString());
		manufacturer.setPersonupdate(temp.getId());
		// Lưu thông tin cập nhật vào cơ sở dữ liệu
		manufacturerDao.save(manufacturer);
		return manufacturerModel;
	}
}
