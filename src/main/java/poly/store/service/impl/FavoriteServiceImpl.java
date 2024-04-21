package poly.store.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import poly.store.dao.FavoriteDao;
import poly.store.dao.ProductDao;
import poly.store.dao.UserDao;
import poly.store.entity.Favorite;
import poly.store.entity.Product;
import poly.store.entity.User;
import poly.store.model.BestSellerModel;
import poly.store.service.FavoriteService;

@Service
public class FavoriteServiceImpl implements FavoriteService {
	@Autowired
	ProductDao productDao;

	@Autowired
	FavoriteDao favoriteDao;

	@Autowired
	UserDao userDao;

	// Phương thức tạo mới hoặc xóa một sản phẩm yêu thích
	@Override
	public Favorite create(int id) {
		// Lấy thông tin người dùng hiện tại
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Lấy thông tin người dùng từ cơ sở dữ liệu
		User temp = userDao.findUserByEmail(username);

		Favorite favorite = new Favorite();

		// Kiểm tra xem người dùng có tồn tại không
		if (temp != null) {
			// Kiểm tra xem sản phẩm đã được thêm vào yêu thích của người dùng hay chưa
			favorite = favoriteDao.getOneFavorite(username, id);

			if (favorite == null) {
				// Nếu sản phẩm chưa được thêm vào yêu thích, thì tạo mới một bản ghi yêu thích mới
				favorite = new Favorite();
				Date date = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String strDate = formatter.format(date);

				Product product = productDao.findById(id).get();
				favorite.setProduct(product);
				favorite.setUser(temp);
				favorite.setDate(strDate);
				favoriteDao.save(favorite);
			} else {
				// Nếu sản phẩm đã được thêm vào yêu thích, thì xóa bản ghi yêu thích
				favoriteDao.delete(favorite);
				favorite.setId(0);
			}
		}

		return favorite;
	}

	// Phương thức lấy danh sách các sản phẩm yêu thích của người dùng hiện tại
	@Override
	public List<Favorite> getListFavoriteByEmail() {
		// Lấy thông tin người dùng hiện tại
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		return favoriteDao.getListFavoriteByEmail(username);
	}

	// Phương thức xóa một sản phẩm yêu thích
	@Override
	public void delete(int id) {
		Favorite favorite = favoriteDao.findById(id).get();
		favoriteDao.delete(favorite);
	}

	// Phương thức lấy thông tin một sản phẩm yêu thích của người dùng hiện tại
	@Override
	public Favorite getOneFavorite(int id) {
		Favorite favorite = new Favorite();

		// Lấy thông tin người dùng hiện tại
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		try {
			username = ((UserDetails) principal).getUsername();
		} catch (Exception e) {
		}
		User temp = userDao.findUserByEmail(username);

		// Kiểm tra xem người dùng có tồn tại không
		if (temp != null) {
			favorite = favoriteDao.getOneFavorite(username, id);
		} else {
			favorite = null;
		}
		return favorite;
	}

	// Phương thức lấy danh sách các sản phẩm bán chạy nhất
	@Override
	public List<BestSellerModel> getListBestSellerProduct(Pageable topFour) {
		return favoriteDao.getListBestSellerProduct(topFour);
	}
}
