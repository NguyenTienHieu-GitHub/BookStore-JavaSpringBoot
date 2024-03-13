package poly.store.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import poly.store.dao.DiscountDao;
import poly.store.dao.UserDao;
import poly.store.entity.Discount;
import poly.store.entity.User;
import poly.store.model.DiscountModel;
import poly.store.service.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService {
	@Autowired
	DiscountDao discountDao;

	@Autowired
	UserDao userDao;

	// Class cung cap service gui mail
	@Autowired
	MailerServiceImpl mailerService;

	@Override
	public DiscountModel createDiscount(DiscountModel discountModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);

		Discount discount = new Discount();

		discount.setName(discountModel.getName());
		discount.setCode(discountModel.getCode());
		discount.setPrice(discountModel.getPrice());
		discount.setApplyday(discountModel.getApplyDay());
		discount.setExpiration(discountModel.getExpiration());
		discount.setQuality(discountModel.getQuality());
		discount.setMoneylimit(discountModel.getMoneyLimit());

		discount.setPersoncreate(temp.getId());
		discount.setCreateday(timestamp.toString());
		discountDao.save(discount);
		return discountModel;
	}

	@Override
	public List<Discount> findAll() {
		return discountDao.getListDiscount();
	}

	@Override
	public DiscountModel getOneDiscountById(Integer id) {
		Discount discount = discountDao.findById(id).get();
		DiscountModel discountModel = new DiscountModel();
		discountModel.setName(discount.getName());
		discountModel.setPrice(discount.getPrice());
		discountModel.setCode(discount.getCode());
		discountModel.setApplyDay(discount.getApplyday());
		discountModel.setExpiration(discount.getExpiration());
		discountModel.setMoneyLimit(discount.getMoneylimit());
		discountModel.setQuality(discount.getQuality());
		return discountModel;
	}

	@Override
	public void delete(Integer id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		User temp = userDao.findUserByEmail(username);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		Discount discount = discountDao.findById(id).get();
		discount.setPersondelete(temp.getId());
		discount.setDeleteday(timestamp.toString());
		discountDao.save(discount);
	}

	@Override
	public DiscountModel updateDiscount(DiscountModel discountModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);

		Discount discount = discountDao.findById(discountModel.getId()).get();
		discount.setName(discountModel.getName());
		discount.setCode(discountModel.getCode());
		discount.setPrice(discountModel.getPrice());
		discount.setApplyday(discountModel.getApplyDay());
		discount.setExpiration(discountModel.getExpiration());
		discount.setQuality(discountModel.getQuality());
		discount.setMoneylimit(discountModel.getMoneyLimit());

		discount.setUpdateday(timestamp.toString());
		discount.setPersonupdate(temp.getId());
		discountDao.save(discount);
		return discountModel;
	}

	@Override
	public Discount getDiscountByCode(String code) {
		return discountDao.getDiscountByCode(code);
	}

	@Override
	public void updateQuality(Discount discount) {
		if (discount != null) {
			discount.setQuality(discount.getQuality() - 1);
			discountDao.save(discount);
		}
	}

	@Override
	public List<Discount> getListDiscountAvailable() {
		return discountDao.getListDiscountAvailable();
	}

	@Override
	public User sendCodeDiscount(Integer discountId, User user) {
		Discount discount = discountDao.findById(discountId).get();
		
		String[] applyDay = discount.getApplyday().split("-");
		String resultApplyDay = applyDay[2] + "/" + applyDay[1] + "/" + applyDay[0];
		
		String[] expiration = discount.getExpiration().split("-");
		String resultExpiration = expiration[2] + "/" + expiration[1] + "/" + expiration[0];
		
		mailerService.queue(user.getEmail(), "FAHASA. Thông Tin Khuyến Mãi!", 
				"Xin chào bạn " + user.getFullname() +",<br>"
				+ "Fahasa xin thông báo đến bạn chương trình. " + discount.getName() + " khi bạn nhập mã <b>" + discount.getCode() + "</b>." + "<br>"
				+ "Thời gian áp dụng từ ngày " + resultApplyDay +" đến ngày " + resultExpiration + "<br>"
				+ "Số tiền giảm " + discount.getPrice() + "đ<br>"
				+ "Số tiền áp dụng trên " + discount.getMoneylimit() + "đ<br>"
				+ "<br><br>"
				+ "Xin chân thành cảm ơn đã sử dụng dịch vụ,<br>"
				+ "FASAHA SHOP");
		return user;
	}

}
