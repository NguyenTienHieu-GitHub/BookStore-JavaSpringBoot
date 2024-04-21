package poly.store.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.store.entity.Favorite;
import poly.store.model.BestSellerModel;

// Giao diện này mở rộng từ JpaRepository, cung cấp các phương thức để thực hiện các thao tác CRUD trên các đối tượng Favorite
public interface FavoriteDao extends JpaRepository<Favorite, Integer>{

	// Phương thức truy vấn tùy chỉnh để lấy danh sách các sản phẩm yêu thích của một người dùng dựa trên email
	@Query("SELECT f FROM Favorite f WHERE f.user.email LIKE ?1 and f.product.Deleteday = null")
	List<Favorite> getListFavoriteByEmail(String email);

	// Phương thức truy vấn tùy chỉnh để lấy một sản phẩm yêu thích dựa trên email người dùng và ID sản phẩm
	@Query("SELECT f FROM Favorite f WHERE f.user.email LIKE ?1 and f.product.id = ?2")
	Favorite getOneFavorite(String email, int id);

	// Phương thức truy vấn tùy chỉnh để lấy danh sách các sản phẩm bán chạy nhất
	@Query("SELECT new BestSellerModel(f.product, count(f.product)) FROM Favorite f WHERE f.product.Deleteday = null AND f.product.active = 1 GROUP BY f.product ORDER BY count(f.product) DESC")
	List<BestSellerModel> getListBestSellerProduct(Pageable pageable);
}
