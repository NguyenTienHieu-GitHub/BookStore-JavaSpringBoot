package poly.store.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import poly.store.entity.Product;
import poly.store.model.ProductModel;
import poly.store.model.ShowProduct;

public interface ProductService {

	// Tạo mới một sản phẩm từ một đối tượng ProductModel
	ProductModel createProduct(ProductModel productModel);

	// Lấy danh sách tất cả các sản phẩm
	List<Product> findAll();

	// Xóa một sản phẩm dựa trên ID
	void delete(Integer id);

	// Cập nhật thông tin của một sản phẩm từ một đối tượng ProductModel
	ProductModel updateProduct(ProductModel productModel);

	// Lấy thông tin của một sản phẩm dựa trên ID
	ProductModel getOneProductById(Integer id);

	// Lấy danh sách sản phẩm mới nhất
	List<Product> getListLatestProduct();

	// Lấy danh sách sản phẩm được xem nhiều nhất
	List<Product> getListViewsProduct();

	// Lấy danh sách sản phẩm theo tên với phân trang
	Page<Product> getListProductByNameSearch(String nameSearch, Pageable pageable);

	// Phương thức demo
	List<Product> getDemo(String nameSearch);

	// Lấy danh sách sản phẩm theo giá với phân trang
	Page<Product> getListProductByPrice(String nameSearch, int minPrice, int maxPrice, Pageable pageable);

	// Lấy danh sách sản phẩm theo bộ lọc với phân trang
	Page<ShowProduct> getListProductByFilter(String nameSearch, String price, String manu, String sort, Pageable pageable, String status, String name, String category);

	// Lấy thông tin của một sản phẩm dựa trên tên
	Product getProductByNameSearch(String nameSearch);

	// Lấy danh sách sản phẩm liên quan đến một sản phẩm dựa trên ID
	List<Product> getListProductRelated(int id);

	// Cập nhật lượt xem của một sản phẩm dựa trên tên
	void updateView(String nameSearch);

	// Cập nhật số lượng của một sản phẩm
	void updateQuality(Product product);

	// Lấy danh sách sản phẩm bán chạy
	List<Product> getListProductSales();

}
