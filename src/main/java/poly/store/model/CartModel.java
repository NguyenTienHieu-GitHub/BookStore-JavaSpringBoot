package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import poly.store.entity.Product;
/**
 * Đây là một lớp model để đại diện cho thông tin về một mục trong giỏ hàng của người dùng.
 *
 * Các trường dữ liệu trong lớp này bao gồm:
 * - id: Định danh duy nhất của mục trong giỏ hàng.
 * - product: Thông tin về sản phẩm được thêm vào giỏ hàng, được đại diện bởi một đối tượng của lớp Product.
 * - quality: Số lượng sản phẩm được thêm vào giỏ hàng.
 *
 * Lớp này được sử dụng để truyền thông tin về các mục trong giỏ hàng từ tầng xử lý logic đến tầng giao diện người dùng để hiển thị và quản lý giỏ hàng.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartModel {
	private int id;
	private Product product;
//	private String name;
//	private String image;
	private int quality;
//	private int discount = 0;
//	private int price;
}
