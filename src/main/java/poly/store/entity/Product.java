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
 * Lớp `Product` đại diện cho một sản phẩm trong hệ thống.
 *
 * Thuộc tính:
 * - id: Định danh duy nhất của sản phẩm.
 * - code: Mã sản phẩm.
 * - name: Tên sản phẩm.
 * - price: Giá sản phẩm.
 * - quality: Số lượng sản phẩm trong kho.
 * - views: Số lượt xem của sản phẩm.
 * - description: Mô tả sản phẩm.
 * - specification: Thông số kỹ thuật của sản phẩm.
 * - image1, image2, image3, image4, image5: Đường dẫn đến hình ảnh của sản phẩm.
 * - active: Trạng thái hoạt động của sản phẩm.
 * - sales: Số lượng sản phẩm đã bán.
 * - Namesearch: Tên tìm kiếm của sản phẩm.
 * - Createday: Ngày tạo sản phẩm.
 * - Personcreate: Người tạo sản phẩm.
 * - Deleteday: Ngày xóa sản phẩm.
 * - Persondelete: Người xóa sản phẩm.
 * - Updateday: Ngày cập nhật sản phẩm.
 * - Personupdate: Người cập nhật sản phẩm.
 * - manufacturer: Nhà sản xuất của sản phẩm.
 * - category: Danh mục của sản phẩm.
 * - listOrder: Danh sách các đơn hàng chứa sản phẩm này.
 * - listFavorite: Danh sách các sản phẩm được yêu thích bởi người dùng.
 * - listComment: Danh sách các bình luận của người dùng về sản phẩm.
 *
 * Các annotations:
 * - @Entity: Đánh dấu lớp này là một entity trong cơ sở dữ liệu.
 * - @Table(name = "Products"): Xác định tên bảng tương ứng trong cơ sở dữ liệu.
 * - @Data: Tạo các phương thức getter, setter, equals, hashCode và toString tự động.
 * - @NoArgsConstructor: Tạo constructor mặc định không có tham số.
 * - @AllArgsConstructor: Tạo constructor có tham số cho tất cả các thuộc tính.
 * - @Id: Xác định thuộc tính id là khóa chính của entity.
 * - @GeneratedValue(strategy = GenerationType.IDENTITY): Xác định cách sinh giá trị cho id là tự động tăng.
 * - @ManyToOne: Xác định mối quan hệ nhiều-đến-một với các entity khác thông qua trường được chú thích.
 * - @JoinColumn(name = "Manu_Id"): Xác định tên cột tham chiếu đến khóa chính của entity Manufacturer trong bảng Products.
 * - @JsonIgnore: Ngăn cản việc serialization các trường được chú thích khi chuyển đổi thành JSON.
 * - @OneToMany: Xác định mối quan hệ một-đến-nhiều với các entity khác thông qua trường được chú thích.
 */

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Products")
public class Product implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String code;

	private String name;

	private int price;

	private int quality;

	private int views;

	private String description;

	private String specification;

	private String image1;

	private String image2;

	private String image3;

	private String image4;

	private String image5;

	private boolean active;
	
	private int sales;
	
	private String Namesearch;
	
	private String Createday;

	private int Personcreate;

	private String Deleteday;

	private int Persondelete;

	private String Updateday;

	private int Personupdate;

	@ManyToOne
	@JoinColumn(name = "Manu_Id")
	Manufacturer manufacturer;

	@ManyToOne
	@JoinColumn(name = "Cate_Id")
	Category category;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	List<Order> listOrder;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	List<Favorite> listFavorite;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
	List<Comment> listComment;
}
