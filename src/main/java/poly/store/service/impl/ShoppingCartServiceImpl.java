package poly.store.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import poly.store.entity.Discount;
import poly.store.model.CartModel;
import poly.store.service.ShoppingCartService;

/**
 * Implementasi của giao diện ShoppingCartService, được gắn với phạm vi phiên là SessionScope.
 */
@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	// Lưu trữ các mặt hàng trong giỏ hàng, sử dụng ID sản phẩm làm khóa.
	public static Map<Integer, CartModel> map = new HashMap<>();

	// Lưu trữ thông tin giảm giá của giỏ hàng, sử dụng ID sản phẩm làm khóa.
	public static Map<Integer, Discount> mapDiscount = new HashMap<>();

	/**
	 * Thêm một mặt hàng vào giỏ hàng hoặc tăng số lượng nếu mặt hàng đã tồn tại trong giỏ.
	 *
	 * @param id     ID của mặt hàng.
	 * @param entity Đối tượng CartModel chứa thông tin mặt hàng.
	 */
	@Override
	public void add(Integer id, CartModel entity) {
		if (map.get(id) != null) {
			this.update(id, entity.getQuality() + 1);
		} else {
			map.put(id, entity);
		}
	}

	/**
	 * Thêm một đối tượng Discount vào giỏ hàng.
	 *
	 * @param id     ID của mặt hàng áp dụng giảm giá.
	 * @param entity Đối tượng Discount chứa thông tin giảm giá.
	 */
	@Override
	public void addDiscount(Integer id, Discount entity) {
		mapDiscount.put(id, entity);
	}

	/**
	 * Lấy tổng số tiền của tất cả các mặt hàng trong giỏ hàng, tính cả giảm giá nếu có.
	 *
	 * @return Tổng số tiền của giỏ hàng.
	 */
	@Override
	public double getAmount() {
		double amount = 0;
		Set<Integer> set = map.keySet();
		for (Integer i : set) {
			amount += map.get(i).getQuality() * map.get(i).getProduct().getPrice();
		}

		// Kiểm tra nếu có giảm giá
		if (this.getDiscount() != null) {
			try {
				amount = amount - this.getDiscount().getPrice();
			} catch (Exception e) {
				// Xử lý nếu có lỗi khi tính toán giảm giá
			}
		}

		return amount;
	}

	/**
	 * Xóa một mặt hàng khỏi giỏ hàng.
	 *
	 * @param id ID của mặt hàng cần xóa.
	 */
	@Override
	public void remove(Integer id) {
		map.remove(id);
	}

	/**
	 * Cập nhật số lượng của một mặt hàng trong giỏ hàng.
	 *
	 * @param id  ID của mặt hàng cần cập nhật.
	 * @param qty Số lượng mới của mặt hàng.
	 */
	@Override
	public void update(Integer id, int qty) {
		map.get(id).setQuality(qty);
	}

	/**
	 * Xóa tất cả các mặt hàng khỏi giỏ hàng.
	 */
	@Override
	public void clear() {
		map.clear();
	}

	/**
	 * Lấy danh sách các mặt hàng trong giỏ hàng.
	 *
	 * @return Danh sách các mặt hàng trong giỏ hàng.
	 */
	@Override
	public Collection<CartModel> getItems() {
		return map.values();
	}

	/**
	 * Lấy tổng số lượng các mặt hàng trong giỏ hàng.
	 *
	 * @return Tổng số lượng các mặt hàng trong giỏ hàng.
	 */
	@Override
	public int getCount() {
		int count = 0;
		Set<Integer> set = map.keySet();
		for (Integer i : set) {
			count++;
		}
		return count;
	}

	/**
	 * Lấy tổng số lượng tất cả các sản phẩm trong giỏ hàng.
	 *
	 * @return Tổng số lượng tất cả các sản phẩm trong giỏ hàng.
	 */
	@Override
	public int getCountAllProduct() {
		int count = 0;
		Set<Integer> set = map.keySet();
		for (Integer i : set) {
			count += map.get(i).getQuality();
		}
		return count;
	}

	/**
	 * Lấy thông tin giảm giá áp dụng cho giỏ hàng.
	 *
	 * @return Đối tượng Discount chứa thông tin giảm giá.
	 */
	@Override
	public Discount getDiscount() {
		Discount discount = new Discount();
		Set<Integer> set = mapDiscount.keySet();
		for (Integer i : set) {
			discount = mapDiscount.get(i);
		}
		return discount;
	}

	/**
	 * Xóa thông tin giảm giá khỏi giỏ hàng.
	 */
	@Override
	public void clearDiscount() {
		mapDiscount.clear();
	}

}
