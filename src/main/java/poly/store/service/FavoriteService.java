package poly.store.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import poly.store.entity.Favorite;
import poly.store.model.BestSellerModel;

public interface FavoriteService {

	// Tạo mới một mục yêu thích dựa trên ID sản phẩm
	Favorite create(int id);

	// Lấy danh sách các mục yêu thích của người dùng hiện tại dựa trên email
	List<Favorite> getListFavoriteByEmail();

	// Xóa một mục yêu thích dựa trên ID
	void delete(int id);

	// Lấy thông tin một mục yêu thích dựa trên ID
	Favorite getOneFavorite(int id);

	// Lấy danh sách sản phẩm bán chạy nhất dựa trên trang và số lượng sản phẩm cần hiển thị
	List<BestSellerModel> getListBestSellerProduct(Pageable topFour);
}
