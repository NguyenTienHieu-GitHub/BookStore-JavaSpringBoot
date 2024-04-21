package poly.store.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp StatisticalProductDay là một model được sử dụng để lưu trữ thông tin thống kê về sản phẩm trong một ngày cụ thể.
 *
 * Trường dữ liệu trong lớp này bao gồm:
 * - code: Mã sản phẩm.
 * - name: Tên sản phẩm.
 * - price: Giá sản phẩm.
 * - quantity: Số lượng sản phẩm.
 * - selled: Số lượng sản phẩm đã bán.
 *
 * Lớp này được sử dụng để truyền thông tin thống kê về sản phẩm trong một ngày cụ thể giữa các thành phần khác nhau trong ứng dụng.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StatisticalProductDay {
	@Id
	private String code;
	private String name;
	private int price;
	private int quantity;
	private long selled;
}
