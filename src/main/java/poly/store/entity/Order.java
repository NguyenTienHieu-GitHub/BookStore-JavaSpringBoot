package poly.store.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp `Order` đại diện cho một đơn hàng trong hệ thống.
 *
 * Thuộc tính:
 * - id: Định danh duy nhất của đơn hàng.
 * - code: Mã đơn hàng.
 * - address: Địa chỉ giao hàng của đơn hàng.
 * - product: Sản phẩm được đặt hàng.
 * - discount: Mã giảm giá được áp dụng cho đơn hàng.
 * - quality: Số lượng sản phẩm được đặt hàng.
 * - date: Ngày đặt hàng.
 * - method: Phương thức thanh toán.
 * - status: Trạng thái của đơn hàng.
 * - comment: Ghi chú cho đơn hàng.
 *
 * Các annotations:
 * - @Entity: Đánh dấu lớp này là một entity trong cơ sở dữ liệu.
 * - @Table(name = "Orders"): Xác định tên bảng tương ứng trong cơ sở dữ liệu.
 * - @Data: Tạo các phương thức getter, setter, equals, hashCode và toString tự động.
 * - @NoArgsConstructor: Tạo constructor mặc định không có tham số.
 * - @AllArgsConstructor: Tạo constructor có tham số cho tất cả các thuộc tính.
 * - @Id: Xác định thuộc tính id là khóa chính của entity.
 * - @GeneratedValue(strategy = GenerationType.IDENTITY): Xác định cách sinh giá trị cho id là tự động tăng.
 * - @ManyToOne: Xác định mối quan hệ nhiều-đến-một với các entity khác thông qua trường được chú thích.
 * - @JoinColumn(name = "Address_Id"): Xác định tên cột tham chiếu đến khóa chính của entity Address trong bảng Orders.
 */

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Orders")
public class Order implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String code;

	@ManyToOne
	@JoinColumn(name = "Address_Id")
	Address address;

	@ManyToOne
	@JoinColumn(name = "Product_Id")
	Product product;
	
	@ManyToOne
	@JoinColumn(name = "Discount_Id")
	Discount discount;
	
	private int quality;
	
	private String date;
	
	private String method;
	
	private String status;
	
	private String comment;
}
