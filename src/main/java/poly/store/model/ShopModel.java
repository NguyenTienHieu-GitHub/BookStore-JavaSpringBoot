package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp ShopModel là một model được sử dụng để đại diện cho thông tin của cửa hàng.
 *
 * Các trường dữ liệu trong lớp này bao gồm:
 * - id: Mã số của cửa hàng.
 * - name: Tên của cửa hàng.
 * - time: Thời gian hoạt động của cửa hàng.
 * - phone: Số điện thoại của cửa hàng.
 * - fax: Số fax của cửa hàng.
 * - email: Địa chỉ email của cửa hàng.
 * - logo: Đường dẫn đến hình ảnh logo của cửa hàng.
 * - logoFooter: Đường dẫn đến hình ảnh logo dưới chân trang của cửa hàng.
 * - address: Địa chỉ của cửa hàng.
 * - active: Trạng thái hoạt động của cửa hàng (true nếu hoạt động, false nếu không hoạt động).
 *
 * Lớp này được sử dụng để truyền thông tin của cửa hàng giữa các thành phần khác nhau trong ứng dụng.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopModel {
	private int id;
	private String name;
	private String time;
	private String phone;
	private String fax;
	private String email;
	private String logo;
	private String logoFooter;
	private String address;
	private boolean active;
}
