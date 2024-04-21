package poly.store.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import poly.store.entity.Discount;
/**
 * Lớp OrderModel là một model được sử dụng để đại diện cho thông tin của một đơn đặt hàng trong hệ thống.
 *
 * Các trường dữ liệu trong lớp này bao gồm:
 * - id: Mã số định danh mỗi đơn đặt hàng.
 * - name: Tên của sản phẩm trong đơn đặt hàng.
 * - quantity: Số lượng sản phẩm được đặt hàng.
 * - total: Tổng số tiền của đơn hàng.
 * - date: Ngày đặt hàng.
 * - status: Trạng thái của đơn hàng.
 * - discount: Thông tin về giảm giá (nếu có) được áp dụng cho đơn hàng.
 *
 * Lớp này được sử dụng để truyền thông tin về các đơn hàng giữa các thành phần khác nhau trong ứng dụng.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class OrderModel {
	@Id
	private String id;
	private String name;
	private Long quantity;
	private Long total;
	private String date;
	private String status;
	private Discount discount;
	
	public OrderModel(String id, String name, Long quantity, Long total, String date, String status) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.total = total;
		this.date = date;
		this.status = status;
	}
}
