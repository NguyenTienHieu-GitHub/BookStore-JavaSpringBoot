package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp StatisticalTotalOrder là một model được sử dụng để lưu trữ thông tin thống kê về tổng số đơn hàng.
 *
 * Trường dữ liệu trong lớp này bao gồm:
 * - orderSuccess: Số lượng đơn hàng thành công.
 * - orderCancel: Số lượng đơn hàng đã hủy.
 * - orderWait: Số lượng đơn hàng đang chờ xử lý.
 * - orderTransport: Số lượng đơn hàng đang vận chuyển.
 *
 * Lớp này được sử dụng để truyền thông tin thống kê về tổng số đơn hàng giữa các thành phần khác nhau trong ứng dụng.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticalTotalOrder {
	private long orderSuccess;
	private long orderCancel;
	private long orderWait;
	private long orderTransport;
}
