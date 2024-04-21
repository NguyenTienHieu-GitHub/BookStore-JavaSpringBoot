/**
 * @(#)ProductDao.java.
 *
 * Version 1.00.
 */
package poly.store.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.store.entity.Product;
import poly.store.model.StatisticalProductDay;

public interface ProductDao extends JpaRepository<Product, Integer>{

	// Lấy danh sách sản phẩm hiện có (không bị xóa)
	@Query("SELECT p FROM Product p WHERE p.Deleteday = null")
	List<Product> getListProduct();

	// Lấy danh sách sản phẩm mới nhất
	@Query(value="SELECT TOP(16) * FROM Products WHERE DeleteDay is NULL and Active = 1 ORDER BY CreateDay DESC", nativeQuery = true)
	List<Product> getListLatestProduct();

	// Lấy danh sách sản phẩm được xem nhiều nhất
	@Query(value="SELECT TOP(14) * FROM Products WHERE DeleteDay is NULL and Active = 1 ORDER BY Views DESC", nativeQuery = true)
	List<Product> getListViewsProduct();

	// Lấy danh sách sản phẩm theo tên (phân trang)
	@Query("SELECT p FROM Product p WHERE p.category.Namesearch LIKE ?1 AND p.Deleteday = null AND p.active = 1 ORDER BY p.Createday DESC")
	Page<Product> getListProductByNameSearch(String nameSearch, Pageable pageable);

	// Lấy danh sách sản phẩm theo khoảng giá (phân trang)
	@Query("SELECT p FROM Product p WHERE p.category.Namesearch LIKE ?1 AND p.Deleteday = null AND p.active = 1 AND p.price >= ?2 AND p.price <= ?3 ORDER BY p.Createday DESC")
	Page<Product> getListProductByPrice(String nameSearch, int minPrice, int maxPrice, Pageable pageable);

	// Lấy danh sách sản phẩm theo tên (không phân trang)
	@Query("SELECT p FROM Product p WHERE p.category.Namesearch LIKE ?1 AND p.Deleteday = null")
	List<Product> getListDemo(String nameSearch);

	// Lấy thông tin sản phẩm theo tên
	@Query("SELECT p FROM Product p WHERE p.Deleteday = null AND p.Namesearch LIKE ?1")
	Product getProductByNameSearch(String nameSearch);

	// Lấy danh sách sản phẩm liên quan dựa trên mã nhà sản xuất
	@Query(value="SELECT TOP(10) * FROM Products WHERE DeleteDay is NULL and Active = 1 and Cate_Id = ?1 ORDER BY Views DESC", nativeQuery = true)
	List<Product> getListProductRelated(int manuId);

	// Lấy danh sách sản phẩm được giảm giá (Sales != 0)
	@Query(value="SELECT TOP(5) * FROM Products WHERE DeleteDay is NULL and Active = 1 and Sales != 0 ORDER BY Views DESC", nativeQuery = true)
	List<Product> getListProductSales();

	// Lấy danh sách sản phẩm trong kho không được đặt hàng (không phân trang)
	@Query(value="SELECT * FROM ProductS WHERE NOT EXISTS (SELECT * FROM ORDERS WHERE Products.Id = ORDERS.Product_Id) AND ProductS.DeleteDay is NULL", nativeQuery = true)
	List<Product> listStatisticalProductWarehouse();
}
