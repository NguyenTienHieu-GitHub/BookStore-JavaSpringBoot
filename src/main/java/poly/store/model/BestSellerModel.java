package poly.store.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.store.entity.Product;

/**
 * Đây là một lớp model để đại diện cho thông tin về sản phẩm bán chạy nhất trong hệ thống cửa hàng.
 *
 * Các trường dữ liệu trong lớp này bao gồm:
 * - product: Đối tượng Product đại diện cho sản phẩm bán chạy nhất.
 * - sum: Số lượng tổng cộng của sản phẩm đã được bán.
 *
 * Lớp này được sử dụng để truyền thông tin về sản phẩm bán chạy nhất từ tầng xử lý logic đến tầng giao diện người dùng để hiển thị thông tin tương ứng.
 *
 * Lớp này được đánh dấu với @Entity để có thể ánh xạ vào một bảng trong cơ sở dữ liệu, giúp quản lý và truy xuất thông tin từ cơ sở dữ liệu một cách thuận tiện.
 * Trường product được đánh dấu với @Id để xác định định danh duy nhất của đối tượng BestSellerModel trong cơ sở dữ liệu.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class BestSellerModel {
	@Id
	private Product product;
	private long sum;
}
