package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp ProductModel là một model được sử dụng để đại diện cho thông tin của một sản phẩm trong hệ thống.
 *
 * Các trường dữ liệu trong lớp này bao gồm:
 * - id: Mã số định danh của sản phẩm.
 * - code: Mã sản phẩm.
 * - name: Tên của sản phẩm.
 * - price: Giá của sản phẩm.
 * - point: Điểm tích lũy cho sản phẩm.
 * - quality: Số lượng tồn kho của sản phẩm.
 * - description: Mô tả về sản phẩm.
 * - specification: Thông số kỹ thuật của sản phẩm.
 * - image1: Đường dẫn đến hình ảnh chính của sản phẩm.
 * - image2: Đường dẫn đến hình ảnh phụ của sản phẩm.
 * - image3: Đường dẫn đến hình ảnh phụ của sản phẩm.
 * - image4: Đường dẫn đến hình ảnh phụ của sản phẩm.
 * - image5: Đường dẫn đến hình ảnh phụ của sản phẩm.
 * - active: Trạng thái hoạt động của sản phẩm.
 * - manuId: Mã số định danh của nhà sản xuất.
 * - cateId: Mã số định danh của danh mục sản phẩm.
 * - nameSearch: Tên dùng để tìm kiếm sản phẩm.
 * - color: Màu sắc của sản phẩm.
 * - memory: Dung lượng bộ nhớ của sản phẩm (nếu có).
 * - sales: Số lượng sản phẩm đã bán.
 *
 * Lớp này được sử dụng để truyền thông tin về các sản phẩm giữa các thành phần khác nhau trong ứng dụng.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
	private int id;
	private String code;
	private String name;
	private int price;
	private int point;
	private int quality;
	private String description;
	private String specification;
	private String image1;
	private String image2;
	private String image3;
	private String image4;
	private String image5;
	private boolean active;
	private int manuId;
	private int cateId;
	private String nameSearch;
	private String color;
	private String memory;
	private int sales;
}
