package poly.store.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Lớp `Favorite` đại diện cho thông tin về một sản phẩm được người dùng đánh dấu là yêu thích.
 *
 * Thuộc tính:
 * - id: Định danh duy nhất của mục yêu thích.
 * - date: Ngày đánh dấu sản phẩm là yêu thích.
 * - user: Thông tin người dùng tương ứng với mục yêu thích.
 * - product: Thông tin sản phẩm được đánh dấu là yêu thích.
 *
 * Các annotations:
 * - @Entity: Đánh dấu lớp này là một entity trong cơ sở dữ liệu.
 * - @Table(name="Favorites"): Xác định tên bảng tương ứng trong cơ sở dữ liệu.
 * - @Data: Tạo các phương thức getter, setter, equals, hashCode và toString tự động.
 * - @NoArgsConstructor: Tạo constructor mặc định không có tham số.
 * - @AllArgsConstructor: Tạo constructor có tham số cho tất cả các thuộc tính.
 * - @Id: Xác định thuộc tính id là khóa chính của entity.
 * - @GeneratedValue(strategy = GenerationType.IDENTITY): Xác định cách sinh giá trị cho id là tự động tăng.
 * - @ManyToOne: Xác định mối quan hệ nhiều-đến-một với entity User và Product.
 * - @JoinColumn(name = "User_Id"): Xác định tên cột tham chiếu đến khóa chính của entity User trong bảng Favorites.
 * - @JoinColumn(name = "Product_Id"): Xác định tên cột tham chiếu đến khóa chính của entity Product trong bảng Favorites.
 * - @JsonIgnore: Ngăn không cho trường user và product được trả về khi chuyển đối tượng Favorite thành JSON.
 */

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Favorites")
public class Favorite implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String date;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "User_Id")
	User user;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "Product_Id")
	Product product;
}
