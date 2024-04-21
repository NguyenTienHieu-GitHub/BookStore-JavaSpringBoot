package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Đây là một lớp model để đại diện cho thông tin về một mã giảm giá.
 *
 * Các trường dữ liệu trong lớp này bao gồm:
 * - id: Mã số duy nhất của mã giảm giá.
 * - name: Tên của mã giảm giá.
 * - code: Mã code của mã giảm giá.
 * - price: Giá trị giảm giá.
 * - quality: Số lượng mã giảm giá có sẵn.
 * - applyDay: Ngày bắt đầu áp dụng mã giảm giá.
 * - expiration: Ngày hết hạn của mã giảm giá.
 * - moneyLimit: Giới hạn tiền tối đa áp dụng mã giảm giá.
 *
 * Lớp này được sử dụng để truyền thông tin về mã giảm giá từ tầng xử lý logic đến giao diện người dùng hoặc ngược lại.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountModel {
	private int id;
	private String name;
	private String code;
	private int price;
	private int quality;
	private String applyDay;
	private String expiration;
	private int moneyLimit;
}
