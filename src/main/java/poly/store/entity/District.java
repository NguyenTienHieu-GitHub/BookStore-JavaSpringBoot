package poly.store.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp `District` đại diện cho thông tin về một quận/huyện trong một tỉnh/thành phố.
 *
 * Thuộc tính:
 * - id: Định danh duy nhất của quận/huyện.
 * - name: Tên của quận/huyện.
 * - wards: Danh sách các phường/xã thuộc quận/huyện.
 *
 * Các annotations:
 * - Không có annotation nào được sử dụng trong class này.
 *
 * Lombok annotations:
 * - @Data: Tạo các phương thức getter, setter, equals, hashCode và toString tự động.
 * - @NoArgsConstructor: Tạo constructor mặc định không có tham số.
 * - @AllArgsConstructor: Tạo constructor có tham số cho tất cả các thuộc tính.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class District {
	private int id;
	private String name;
	private List<Ward> wards;
}
