package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp Nav1Model là một model được sử dụng để đại diện cho thông tin của một mục trong thanh điều hướng (navigation) cấp 1 trong ứng dụng.
 *
 * Các trường dữ liệu trong lớp này bao gồm:
 * - id: Mã số duy nhất định danh mỗi mục trong thanh điều hướng.
 * - name: Tên của mục trong thanh điều hướng.
 * - categoryId: ID của danh mục (category) tương ứng với mục trong thanh điều hướng.
 * - nameSearch: Tên được sử dụng để tìm kiếm mục trong thanh điều hướng.
 *
 * Lớp này được sử dụng để truyền thông tin về các mục trong thanh điều hướng cấp 1 giữa các thành phần khác nhau trong ứng dụng.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nav1Model {
	private int id;
	private String name;
	private int categoryId;
	private String nameSearch;
}
