package poly.store.service;

import java.util.List;

import poly.store.entity.Discount;
import poly.store.entity.User;
import poly.store.model.DiscountModel;
import poly.store.model.InformationModel;

public interface DiscountService {

	// Tạo mới một mã giảm giá từ một đối tượng DiscountModel
	DiscountModel createDiscount(DiscountModel discountModel);

	// Lấy danh sách tất cả các mã giảm giá
	List<Discount> findAll();

	// Lấy thông tin một mã giảm giá dựa trên ID và chuyển đổi sang đối tượng DiscountModel
	DiscountModel getOneDiscountById(Integer id);

	// Xóa một mã giảm giá dựa trên ID
	void delete(Integer id);

	// Cập nhật thông tin về một mã giảm giá từ một đối tượng DiscountModel
	DiscountModel updateDiscount(DiscountModel discountModel);

	// Lấy thông tin một mã giảm giá dựa trên mã code
	Discount getDiscountByCode(String code);

	// Cập nhật số lượng mã giảm giá đã sử dụng
	void updateQuality(Discount discount);

	// Lấy danh sách các mã giảm giá có sẵn
	List<Discount> getListDiscountAvailable();

	// Gửi mã giảm giá cho một người dùng và cập nhật thông tin người dùng
	User sendCodeDiscount(Integer discountId, User user);
}
