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

	// Phương thức tạo mã giảm giá mới
	@Override
	public DiscountModel createDiscount(DiscountModel discountModel) {
		// Lấy thông tin người dùng hiện tại
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Lấy thời gian hiện tại
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		// Tìm người dùng hiện tại trong cơ sở dữ liệu
		User temp = userDao.findUserByEmail(username);

		// Tạo một đối tượng Discount mới
		Discount discount = new Discount();

		// Thiết lập các thuộc tính cho đối tượng Discount từ dữ liệu nhập vào
		discount.setName(discountModel.getName());
		discount.setCode(discountModel.getCode());
		discount.setPrice(discountModel.getPrice());
		discount.setApplyday(discountModel.getApplyDay());
		discount.setExpiration(discountModel.getExpiration());
		discount.setQuality(discountModel.getQuality());
		discount.setMoneylimit(discountModel.getMoneyLimit());

		// Thiết lập người tạo và thời gian tạo cho đối tượng Discount
		discount.setPersoncreate(temp.getId());
		discount.setCreateday(timestamp.toString());

		// Lưu đối tượng Discount vào cơ sở dữ liệu
		discountDao.save(discount);
		return discountModel;
	}

	// Phương thức lấy danh sách tất cả các mã giảm giá
	@Override
	public List<Discount> findAll() {
		return discountDao.getListDiscount();
	}

	// Phương thức lấy thông tin của một mã giảm giá dựa trên id
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

	// Phương thức xóa một mã giảm giá dựa trên id
	@Override
	public void delete(Integer id) {
		// Lấy thông tin người dùng hiện tại
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		User temp = userDao.findUserByEmail(username);

		// Lấy thời gian hiện tại
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		// Tìm mã giảm giá dựa trên id và thiết lập thông tin người xóa và thời gian xóa
		Discount discount = discountDao.findById(id).get();
		discount.setPersondelete(temp.getId());
		discount.setDeleteday(timestamp.toString());
		discountDao.save(discount);
	}

	// Phương thức cập nhật thông tin của một mã giảm giá
	@Override
	public DiscountModel updateDiscount(DiscountModel discountModel) {
		// Lấy thông tin người dùng hiện tại
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Lấy thời gian hiện tại
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		// Tìm người dùng hiện tại trong cơ sở dữ liệu
		User temp = userDao.findUserByEmail(username);

		// Tìm mã giảm giá dựa trên id và cập nhật thông tin từ dữ liệu nhập vào
		Discount discount = discountDao.findById(discountModel.getId()).get();
		discount.setName(discountModel.getName());
		discount.setCode(discountModel.getCode());
		discount.setPrice(discountModel.getPrice());
		discount.setApplyday(discountModel.getApplyDay());
		discount.setExpiration(discountModel.getExpiration());
		discount.setQuality(discountModel.getQuality());
		discount.setMoneylimit(discountModel.getMoneyLimit());

		// Thiết lập thông tin người cập nhật và thời gian cập nhật
		discount.setUpdateday(timestamp.toString());
		discount.setPersonupdate(temp.getId());

		// Lưu thông tin mã giảm giá đã cập nhật vào cơ sở dữ liệu
		discountDao.save(discount);
		return discountModel;
	}

	// Phương thức lấy thông tin của một mã giảm giá dựa trên code
	@Override
	public Discount getDiscountByCode(String code) {
		return discountDao.getDiscountByCode(code);
	}

	// Phương thức cập nhật số lượng mã giảm giá sau khi sử dụng
	@Override
	public void updateQuality(Discount discount) {
		if (discount != null) {
			discount.setQuality(discount.getQuality() - 1);
			discountDao.save(discount);
		}
	}

	// Phương thức lấy danh sách các mã giảm giá có sẵn
	@Override
	public List<Discount> getListDiscountAvailable() {
		return discountDao.getListDiscountAvailable();
	}

	// Phương thức gửi mã giảm giá cho người dùng qua email
	@Override
	public User sendCodeDiscount(Integer discountId, User user) {
		// Lấy thông tin mã giảm giá dựa trên id
		Discount discount = discountDao.findById(discountId).get();

		// Chuyển đổi định dạng ngày tháng
		String[] applyDay = discount.getApplyday().split("-");
		String resultApplyDay = applyDay[2] + "/" + applyDay[1] + "/" + applyDay[0];

		String[] expiration = discount.getExpiration().split("-");
		String resultExpiration = expiration[2] + "/" + expiration[1] + "/" + expiration[0];

		// Gửi email thông báo mã giảm giá cho người dùng
		mailerService.queue(user.getEmail(), "BookStore FAHASA. Thông Tin Khuyến Mãi!",
				"Xin chào bạn " + user.getFullname() +",<br>"
						+ "Fahasa xin thông báo đến bạn chương trình. " + discount.getName() + " khi bạn nhập mã <b>" + discount.getCode() + "</b>." + "<br>"
						+ "Thời gian áp dụng từ ngày " + resultApplyDay +" đến ngày " + resultExpiration + "<br>"
						+ "Số tiền giảm " + discount.getPrice() + "đ<br>"
						+ "Số tiền áp dụng trên " + discount.getMoneylimit() + "đ<br>"
						+ "<br><br>"
						+ "Xin chân thành cảm ơn đã sử dụng dịch vụ,<br>"
						+ "BOOKSTORE FASAHA");
		return user;
	}

}
