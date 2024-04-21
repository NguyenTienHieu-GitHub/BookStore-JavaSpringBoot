package poly.store.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp `Province` đại diện cho một tỉnh/thành phố trong hệ thống.
 *
 * Thuộc tính:
 * - code: Mã định danh của tỉnh/thành phố.
 * - id: Định danh duy nhất của tỉnh/thành phố.
 * - name: Tên của tỉnh/thành phố.
 * - districts: Danh sách các quận/huyện thuộc tỉnh/thành phố này.
 *
 * Các annotations:
 * - @Data: Tạo các phương thức getter, setter, equals, hashCode và toString tự động.
 * - @NoArgsConstructor: Tạo constructor mặc định không có tham số.
 * - @AllArgsConstructor: Tạo constructor có tham số cho tất cả các thuộc tính.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Province {
	private String code;
	private Integer id;
	private String name;
	private List<District> districts;
}
