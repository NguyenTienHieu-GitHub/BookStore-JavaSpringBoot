package poly.store.service;

import java.util.Collection;

import poly.store.entity.Discount;
import poly.store.model.CartModel;

public interface ShoppingCartService {
	// Thêm một sản phẩm vào giỏ hàng với ID và thông tin sản phẩm
	void add(Integer id, CartModel entity);

	// Xóa một sản phẩm khỏi giỏ hàng dựa trên ID của sản phẩm
	void remove(Integer id);

	// Cập nhật số lượng của một sản phẩm trong giỏ hàng
	void update(Integer id, int qty);

	// Xóa toàn bộ các sản phẩm khỏi giỏ hàng
	void clear();

	// Lấy tất cả các mặt hàng trong giỏ hàng
	Collection<CartModel> getItems();

	// Lấy số lượng các mặt hàng trong giỏ hàng
	int getCount();

	// Lấy tổng số lượng sản phẩm trong giỏ hàng
	int getCountAllProduct();

	// Lấy tổng số tiền của các mặt hàng trong giỏ hàng
	double getAmount();

	// Thêm một mã giảm giá vào giỏ hàng với ID và thông tin mã giảm giá
	void addDiscount(Integer id, Discount entity);

	// Lấy thông tin về mã giảm giá hiện tại trong giỏ hàng
	Discount getDiscount();

	// Xóa mã giảm giá khỏi giỏ hàng
	void clearDiscount();
}
