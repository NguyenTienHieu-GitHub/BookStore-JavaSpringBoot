package poly.store.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp StatisticalOrder là một model được sử dụng để lưu trữ thông tin thống kê về số lượng đơn hàng.
 *
 * Trường dữ liệu trong lớp này bao gồm:
 * - count: Số lượng đơn hàng.
 *
 * Lớp này được sử dụng để truyền thông tin thống kê số lượng đơn hàng giữa các thành phần khác nhau trong ứng dụng.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StatisticalOrder {
	@Id
	private long count;
}
