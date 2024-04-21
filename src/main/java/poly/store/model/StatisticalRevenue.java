package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp StatisticalRevenue là một model được sử dụng để lưu trữ thông tin thống kê về doanh thu.
 *
 * Trường dữ liệu trong lớp này bao gồm:
 * - price: Giá trị doanh thu.
 * - date: Ngày thống kê.
 *
 * Lớp này được sử dụng để truyền thông tin thống kê về doanh thu giữa các thành phần khác nhau trong ứng dụng.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticalRevenue {
	private double price;
	private int date;
}
