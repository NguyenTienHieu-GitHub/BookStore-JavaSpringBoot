package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp Nav2Model là một model được sử dụng để đại diện cho thông tin của một mục trong thanh điều hướng (navigation) cấp 2 trong ứng dụng.
 *
 * Các trường dữ liệu trong lớp này bao gồm:
 * - id: Mã số duy nhất định danh mỗi mục trong thanh điều hướng cấp 2.
 * - name: Tên của mục trong thanh điều hướng cấp 2.
 * - nav1Id: ID của mục trong thanh điều hướng cấp 1 mà mục này thuộc về.
 * - nameSearch: Tên được sử dụng để tìm kiếm mục trong thanh điều hướng cấp 2.
 *
 * Lớp này được sử dụng để truyền thông tin về các mục trong thanh điều hướng cấp 2 giữa các thành phần khác nhau trong ứng dụng.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nav2Model {
	private int id;
	private String name;
	private int nav1Id;
	private String nameSearch;
}
