package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.store.entity.Product;
/**
 * Lớp ShowProduct là một model được sử dụng để đại diện cho thông tin hiển thị của một sản phẩm cùng với tổng số sao đánh giá của sản phẩm đó.
 *
 * Các trường dữ liệu trong lớp này bao gồm:
 * - product: Đối tượng Product, đại diện cho thông tin của sản phẩm.
 * - totalStar: Tổng số sao đánh giá của sản phẩm.
 *
 * Lớp này được sử dụng để truyền thông tin hiển thị sản phẩm cùng với tổng số sao đánh giá tương ứng giữa các thành phần khác nhau trong ứng dụng.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowProduct {
	private Product product;
	private int totalStar;
}
