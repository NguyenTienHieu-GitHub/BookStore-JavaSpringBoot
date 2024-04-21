package poly.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp `Ward` đại diện cho đơn vị hành chính làng/phường trong hệ thống.
 *
 * Thuộc tính:
 * - id: Định danh duy nhất của làng/phường.
 * - name: Tên của làng/phường.
 * - prefix: Tiền tố của mã địa chỉ, có thể là số điện thoại quốc gia hoặc mã vùng.
 *
 * Các annotations:
 * - @Data: Tạo các phương thức getter, setter, equals, hashCode và toString tự động.
 * - @NoArgsConstructor: Tạo constructor mặc định không có tham số.
 * - @AllArgsConstructor: Tạo constructor có tham số cho tất cả các thuộc tính.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ward {
	private int id;
	private String name;
	private String prefix;
}
