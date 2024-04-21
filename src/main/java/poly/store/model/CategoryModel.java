
package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Đây là một lớp model để đại diện cho thông tin về một danh mục sản phẩm.
 *
 * Các trường dữ liệu trong lớp này bao gồm:
 * - id: Định danh duy nhất của danh mục sản phẩm.
 * - name: Tên của danh mục sản phẩm.
 * - banner: Đường dẫn đến hình ảnh banner của danh mục sản phẩm.
 * - logo: Đường dẫn đến hình ảnh logo của danh mục sản phẩm.
 * - describe: Mô tả về danh mục sản phẩm.
 * - nameSearch: Tên của danh mục sản phẩm dùng cho tìm kiếm.
 *
 * Lớp này được sử dụng để truyền thông tin về danh mục sản phẩm từ tầng dữ liệu đến tầng giao diện người dùng để hiển thị và quản lý danh mục sản phẩm.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryModel {
	private int id;
	private String name;
	private String banner;
	private String logo;
	private String describe;
	private String nameSearch;
}
