package poly.store.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import poly.store.dao.InformationShopDao;
import poly.store.dao.UserDao;
import poly.store.entity.InformationShop;
import poly.store.entity.User;
import poly.store.model.ShopModel;
import poly.store.service.InformationShopService;

@Service
public class InformationShopServiceImpl implements InformationShopService{
	@Autowired
	UserDao userDao;

	@Autowired
	InformationShopDao informationDao;

	// Phương thức tạo mới thông tin cửa hàng
	@Override
	public ShopModel createInformationShop(ShopModel shopModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);

		InformationShop informationShop = new InformationShop();
		// Set thông tin cho cửa hàng
		informationShop.setName(shopModel.getName());
		informationShop.setTimeopen(shopModel.getTime());
		informationShop.setLogo(shopModel.getLogo());
		informationShop.setLogofooter(shopModel.getLogoFooter());
		informationShop.setPhone(shopModel.getPhone());
		informationShop.setFax(shopModel.getFax());
		informationShop.setEmail(shopModel.getEmail());
		informationShop.setAddress(shopModel.getAddress());
		informationShop.setCreateday(timestamp.toString());
		informationShop.setPersoncreate(temp.getId());
		informationDao.save(informationShop);
		return shopModel;
	}

	// Phương thức lấy danh sách thông tin tất cả cửa hàng
	@Override
	public List<InformationShop> findAll() {
		return informationDao.getListInformationShop();
	}

	// Phương thức xóa thông tin cửa hàng dựa trên id
	@Override
	public void delete(Integer id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);

		InformationShop informationShop = informationDao.findById(id).get();
		informationShop.setDeleteday(timestamp.toString());
		informationShop.setPersondelete(temp.getId());
		informationDao.save(informationShop);
	}

	// Phương thức cập nhật trạng thái active của cửa hàng
	@Override
	public ShopModel updateActive(ShopModel shopModel) {
		List<InformationShop> list = informationDao.findAll();
		for(InformationShop infor: list) {
			if(infor.getId() != shopModel.getId()) {
				infor.setActive(false);
			}
			else {
				infor.setActive(true);
			}
			informationDao.save(infor);
		}

		return shopModel;
	}

	// Phương thức lấy thông tin của một cửa hàng dựa trên id
	@Override
	public ShopModel getOneShopById(Integer id) {
		InformationShop informationShop = informationDao.findById(id).get();
		ShopModel shopModel = new ShopModel();
		shopModel.setAddress(informationShop.getAddress());
		shopModel.setEmail(informationShop.getEmail());
		shopModel.setFax(informationShop.getFax());
		shopModel.setPhone(informationShop.getPhone());
		shopModel.setLogo(informationShop.getLogo());
		shopModel.setLogoFooter(informationShop.getLogofooter());
		shopModel.setName(informationShop.getName());
		shopModel.setTime(informationShop.getTimeopen());
		return shopModel;
	}

	// Phương thức cập nhật thông tin của cửa hàng
	@Override
	public ShopModel updateInformation(ShopModel shopModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);

		InformationShop informationShop = informationDao.findById(shopModel.getId()).get();
		// Cập nhật thông tin cho cửa hàng
		informationShop.setAddress(shopModel.getAddress());
		informationShop.setEmail(shopModel.getEmail());
		informationShop.setFax(shopModel.getFax());
		informationShop.setLogo(shopModel.getLogo());
		informationShop.setLogofooter(shopModel.getLogoFooter());
		informationShop.setName(shopModel.getName());
		informationShop.setTimeopen(shopModel.getTime());
		informationShop.setPhone(shopModel.getPhone());
		informationShop.setUpdateday(timestamp.toString());
		informationShop.setPersonupdate(temp.getId());
		informationDao.save(informationShop);
		return shopModel;
	}

	// Phương thức lấy thông tin của một cửa hàng
	@Override
	public InformationShop getOneInformationShop() {
		InformationShop informationShop = informationDao.getOneInformationShop();
		// Format số điện thoại và fax
		String phone = informationShop.getPhone().substring(0, 3) + "-" + informationShop.getPhone().substring(3, 6) + "-" + informationShop.getPhone().substring(6);
		String fax = "+84 " + informationShop.getFax().substring(1, 4) + " " + informationShop.getFax().substring(4, 7) + " " + informationShop.getFax().substring(7);
		informationShop.setPhone(phone);
		informationShop.setFax(fax);
		return informationShop;
	}

}
