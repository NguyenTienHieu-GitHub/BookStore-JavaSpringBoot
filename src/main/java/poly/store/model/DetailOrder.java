package poly.store.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Đây là một lớp model để đại diện cho chi tiết của một đơn hàng.
 *
 * Các trường dữ liệu trong lớp này bao gồm:
 * - id: Mã đơn hàng.
 * - date: Ngày đặt hàng.
 * - method: Phương thức thanh toán.
 * - fullName: Họ và tên của người nhận hàng.
 * - address: Địa chỉ nhận hàng.
 * - phone: Số điện thoại của người nhận hàng.
 * - province: Tỉnh/thành phố.
 * - district: Quận/huyện.
 * - ward: Phường/xã.
 * - subTotal: Tổng số tiền của các sản phẩm trong đơn hàng trước khi áp dụng giảm giá.
 * - discount: Số tiền được giảm giá.
 * - total: Tổng số tiền phải thanh toán sau khi áp dụng giảm giá.
 * - comment: Ghi chú từ khách hàng.
 * - listOrder: Danh sách các sản phẩm trong đơn hàng.
 *
 * Lớp này được sử dụng để truyền thông tin chi tiết của đơn hàng từ tầng xử lý logic đến giao diện người dùng hoặc ngược lại.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailOrder {
	private String id;
	private String date;
	private String method;
	private String fullName;
	private String address;
	private String phone;
	private String province;
	private String district;
	private String ward;
	private int subTotal;
	private int discount;
	private int total;
	private String comment;
	private List<CartModel> listOrder;
}
