package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp ManufacturerModel là một model được sử dụng để đại diện cho thông tin của một nhà sản xuất (manufacturer), bao gồm các trường thông tin như: id, tên, banner, logo và mô tả.
 *
 * Các trường dữ liệu trong lớp này bao gồm:
 * - id: Mã số duy nhất định danh mỗi nhà sản xuất.
 * - name: Tên của nhà sản xuất.
 * - banner: Đường dẫn tới banner của nhà sản xuất.
 * - logo: Đường dẫn tới logo của nhà sản xuất.
 * - describe: Mô tả về nhà sản xuất.
 *
 * Lớp này được sử dụng để truyền thông tin về nhà sản xuất giữa các thành phần khác nhau trong ứng dụng, như khi hiển thị thông tin chi tiết của sản phẩm.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerModel {
	private int id;
	private String name;
	private String banner;
	private String logo;
	private String describe;
}
