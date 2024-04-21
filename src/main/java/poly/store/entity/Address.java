package poly.store.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp `Address` đại diện cho thông tin địa chỉ của người dùng.
 *
 * Thuộc tính:
 * - id: Định danh duy nhất của địa chỉ.
 * - Fullname: Họ và tên người nhận hàng.
 * - phone: Số điện thoại liên hệ.
 * - province: Tên tỉnh/thành phố.
 * - district: Tên quận/huyện.
 * - ward: Tên phường/xã.
 * - detail: Địa chỉ chi tiết.
 * - user: Thông tin người dùng sở hữu địa chỉ này.
 * - listOrder: Danh sách các đơn hàng đã sử dụng địa chỉ này.
 *
 * Các annotations:
 * - @Entity: Đánh dấu lớp này là một entity trong cơ sở dữ liệu.
 * - @Table(name = "Address"): Xác định tên bảng tương ứng trong cơ sở dữ liệu.
 * - @Data: Tạo các phương thức getter, setter, equals, hashCode và toString tự động.
 * - @NoArgsConstructor: Tạo constructor mặc định không có tham số.
 * - @AllArgsConstructor: Tạo constructor có tham số cho tất cả các thuộc tính.
 * - @Id: Xác định thuộc tính id là khóa chính của entity.
 * - @GeneratedValue(strategy = GenerationType.IDENTITY): Xác định cách sinh giá trị cho id là tự động tăng.
 * - @ManyToOne: Xác định mối quan hệ nhiều-đến-một với entity User.
 * - @JoinColumn(name = "User_Id"): Xác định tên cột tham chiếu đến khóa chính của entity User.
 * - @OneToMany: Xác định mối quan hệ một-đến-nhiều với entity Order.
 * - @JsonIgnore: Ngăn không cho trường user được trả về khi chuyển đối tượng Address thành JSON.
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Address")
public class Address implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String Fullname;

	private String phone;

	private String province;

	private String district;

	private String ward;

	private String detail;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "User_Id")
	User user;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "address")
	List<Order> listOrder;
}
