package poly.store.service;

import java.util.List;

import poly.store.entity.Discount;
import poly.store.entity.User;
import poly.store.model.DiscountModel;
import poly.store.model.InformationModel;

public interface DiscountService {

	DiscountModel createDiscount(DiscountModel discountModel);

	List<Discount> findAll();

	DiscountModel getOneDiscountById(Integer id);

	void delete(Integer id);

	DiscountModel updateDiscount(DiscountModel discountModel);

	Discount getDiscountByCode(String code);

	void updateQuality(Discount discount);

	List<Discount> getListDiscountAvailable();

	User sendCodeDiscount(Integer discountId, User user);

}
