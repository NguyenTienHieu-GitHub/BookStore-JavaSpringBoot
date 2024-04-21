package poly.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Đây là một lớp model đơn giản để đại diện cho thông tin của một cảnh báo hiển thị trong ứng dụng.
 *
 * Các trường dữ liệu trong lớp này bao gồm:
 * - content: Nội dung của cảnh báo.
 * - alert: Loại cảnh báo, ví dụ: "success", "info", "warning", "danger".
 * - display: Biến boolean cho biết liệu cảnh báo có được hiển thị hay không.
 *
 * Lớp này được sử dụng để truyền dữ liệu cảnh báo giữa các thành phần trong ứng dụng, chẳng hạn như từ tầng xử lý logic đến tầng giao diện người dùng để hiển thị thông báo tương ứng.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertModel {
	private String content;
	private String alert;
	private boolean display;
}
