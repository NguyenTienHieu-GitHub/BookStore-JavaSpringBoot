
package poly.store.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp `Discount` đại diện cho thông tin về mã giảm giá áp dụng cho đơn hàng.
 *
 * Thuộc tính:
 * - id: Định danh duy nhất của mã giảm giá.
 * - name: Tên của mã giảm giá.
 * - code: Mã code của mã giảm giá.
 * - price: Giá giảm giá.
 * - quality: Số lượng sản phẩm được áp dụng giảm giá.
 * - Applyday: Ngày bắt đầu áp dụng giảm giá.
 * - expiration: Ngày hết hạn của mã giảm giá.
 * - Moneylimit: Giới hạn số tiền tối đa mà mã giảm giá có thể giảm.
 * - Createday: Ngày tạo mã giảm giá.
 * - Personcreate: Người tạo mã giảm giá.
 * - Deleteday: Ngày mã giảm giá bị xóa.
 * - Persondelete: Người xóa mã giảm giá.
 * - Updateday: Ngày cập nhật mã giảm giá.
 * - Personupdate: Người cập nhật mã giảm giá.
 * - listOrder: Danh sách các đơn hàng mà mã giảm giá được áp dụng.
 *
 * Các annotations:
 * - @Entity: Đánh dấu lớp này là một entity trong cơ sở dữ liệu.
 * - @Table(name = "Discount"): Xác định tên bảng tương ứng trong cơ sở dữ liệu.
 * - @Data: Tạo các phương thức getter, setter, equals, hashCode và toString tự động.
 * - @NoArgsConstructor: Tạo constructor mặc định không có tham số.
 * - @AllArgsConstructor: Tạo constructor có tham số cho tất cả các thuộc tính.
 * - @Id: Xác định thuộc tính id là khóa chính của entity.
 * - @GeneratedValue(strategy = GenerationType.IDENTITY): Xác định cách sinh giá trị cho id là tự động tăng.
 * - @JsonIgnore: Ngăn không cho trường listOrder được trả về khi chuyển đối tượng Discount thành JSON.
 * - @OneToMany(mappedBy = "discount"): Xác định mối quan hệ một-đến-nhiều với entity Order thông qua trường discount.
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Discount")
public class Discount implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String code;
	
	private int price;
	
	private int quality;
	
	private String Applyday;
	
	private String expiration;
	
	private int Moneylimit;

	private String Createday;

	private int Personcreate;

	private String Deleteday;

	private int Persondelete;

	private String Updateday;

	private int Personupdate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "discount")
	List<Order> listOrder;

}
