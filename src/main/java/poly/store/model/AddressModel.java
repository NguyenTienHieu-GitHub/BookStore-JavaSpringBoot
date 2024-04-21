package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Đây là một lớp model đơn giản để đại diện cho thông tin địa chỉ của người dùng.
 *
 * Các trường dữ liệu trong lớp này bao gồm:
 * - fullName: Họ và tên đầy đủ của người dùng.
 * - detail: Chi tiết địa chỉ.
 * - phone: Số điện thoại liên hệ.
 * - province: Tên tỉnh/thành phố.
 * - district: Tên quận/huyện.
 * - ward: Tên phường/xã.
 *
 * Lớp này được sử dụng để chuyển dữ liệu giữa các tầng trong ứng dụng, thường là từ tầng giao diện người dùng đến tầng dịch vụ.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressModel {
	private String fullName;
	private String detail;
	private String phone;
	private String province;
	private String district;
	private String ward;
}
