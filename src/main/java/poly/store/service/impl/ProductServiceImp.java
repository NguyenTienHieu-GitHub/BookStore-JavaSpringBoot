package poly.store.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import poly.store.dao.CategoryDao;
import poly.store.dao.ManufacturerDao;
import poly.store.dao.ProductDao;
import poly.store.dao.UserDao;
import poly.store.entity.Category;
import poly.store.entity.Manufacturer;
import poly.store.entity.Product;
import poly.store.entity.User;
import poly.store.model.ProductModel;
import poly.store.model.ShowProduct;
import poly.store.service.CommentService;
import poly.store.service.ProductService;

@Service
public class ProductServiceImp implements ProductService {
	@Autowired
	ProductDao productDao;

	@Autowired
	UserDao userDao;

	@Autowired
	ManufacturerDao manufacturerDao;

	@Autowired
	CategoryDao categoryDao;

	@Autowired
	CommentService commentService;

	@PersistenceContext
	private EntityManager em;

	// Tạo mới sản phẩm
	@Override
	public ProductModel createProduct(ProductModel productModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);

		// Tạo mới đối tượng Product từ thông tin trong productModel
		Product product = new Product();
		product.setCode(productModel.getCode());
		product.setName(productModel.getName());
		product.setPrice(productModel.getPrice());
		product.setQuality(productModel.getQuality());
		product.setDescription(productModel.getDescription());
		product.setSpecification(productModel.getSpecification());
		product.setImage1(productModel.getImage1());
		product.setImage2(productModel.getImage2());
		product.setImage3(productModel.getImage3());
		product.setImage4(productModel.getImage4());
		product.setImage5(productModel.getImage5());
		product.setActive(productModel.isActive());
		product.setNamesearch(productModel.getNameSearch());
		product.setCreateday(timestamp.toString());
		product.setPersoncreate(temp.getId());
		product.setSales(productModel.getSales());

		// Lấy nhà sản xuất và danh mục tương ứng từ ID được cung cấp trong productModel
		Manufacturer manufacturer = manufacturerDao.findById(productModel.getManuId()).get();
		Category category = categoryDao.findById(productModel.getCateId()).get();

		// Thiết lập nhà sản xuất và danh mục cho sản phẩm
		product.setCategory(category);
		product.setManufacturer(manufacturer);

		// Lưu sản phẩm mới vào cơ sở dữ liệu
		productDao.save(product);

		// Trả về productModel sau khi đã tạo mới sản phẩm thành công
		return productModel;
	}

	// Lấy danh sách tất cả sản phẩm
	@Override
	public List<Product> findAll() {
		return productDao.getListProduct();
	}

	// Xóa sản phẩm dựa trên ID
	@Override
	public void delete(Integer id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		User temp = userDao.findUserByEmail(username);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		Product product = productDao.findById(id).get();
		product.setDeleteday(timestamp.toString());
		product.setPersondelete(temp.getId());
		productDao.save(product);
	}

	// Cập nhật thông tin sản phẩm
	@Override
	public ProductModel updateProduct(ProductModel productModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = userDao.findUserByEmail(username);

		Product product = productDao.findById(productModel.getId()).get();

		product.setCode(productModel.getCode());
		product.setName(productModel.getName());
		product.setPrice(productModel.getPrice());
		product.setQuality(productModel.getQuality());
		product.setDescription(productModel.getDescription());
		product.setSpecification(productModel.getSpecification());
		product.setImage1(productModel.getImage1());
		product.setImage2(productModel.getImage2());
		product.setImage3(productModel.getImage3());
		product.setImage4(productModel.getImage4());
		product.setImage5(productModel.getImage5());
		product.setActive(productModel.isActive());
		product.setNamesearch(productModel.getNameSearch());
		product.setUpdateday(timestamp.toString());
		product.setPersonupdate(temp.getId());
		product.setSales(productModel.getSales());

		Manufacturer manufacturer = manufacturerDao.findById(productModel.getManuId()).get();
		Category category = categoryDao.findById(productModel.getCateId()).get();

		product.setCategory(category);
		product.setManufacturer(manufacturer);

		productDao.save(product);
		return productModel;
	}

	// Lấy thông tin sản phẩm dựa trên ID
	@Override
	public ProductModel getOneProductById(Integer id) {
		Product product = productDao.findById(id).get();
		ProductModel productModel = new ProductModel();
		productModel.setId(product.getId());
		productModel.setCode(product.getCode());
		productModel.setName(product.getName());
		productModel.setPrice(product.getPrice());
		productModel.setQuality(product.getQuality());
		productModel.setImage1(product.getImage1());
		productModel.setImage2(product.getImage2());
		productModel.setImage3(product.getImage3());
		productModel.setImage4(product.getImage4());
		productModel.setImage5(product.getImage5());
		productModel.setNameSearch(product.getNamesearch());
		productModel.setActive(product.isActive());
		productModel.setManuId(product.getManufacturer().getId());
		productModel.setCateId(product.getCategory().getId());
		productModel.setDescription(product.getDescription());
		productModel.setSpecification(product.getSpecification());
		productModel.setSales(product.getSales());
		return productModel;
	}

	// Lấy danh sách sản phẩm mới nhất
	@Override
	public List<Product> getListLatestProduct() {
		return productDao.getListLatestProduct();
	}

	// Lấy danh sách sản phẩm được xem nhiều nhất
	@Override
	public List<Product> getListViewsProduct() {
		return productDao.getListViewsProduct();
	}

	// Lấy danh sách sản phẩm dựa trên tên tìm kiếm
	@Override
	public Page<Product> getListProductByNameSearch(String nameSearch, Pageable pageable) {
		return productDao.getListProductByNameSearch(nameSearch, pageable);
	}

	// Lấy danh sách sản phẩm demo
	@Override
	public List<Product> getDemo(String nameSearch) {
		return productDao.getListDemo(nameSearch);
	}

	// Lấy danh sách sản phẩm dựa trên giá
	@Override
	public Page<Product> getListProductByPrice(String nameSearch, int minPrice, int maxPrice, Pageable pageable) {
		return productDao.getListProductByPrice(nameSearch, minPrice, maxPrice, pageable);
	}

	// Lấy danh sách sản phẩm dựa trên bộ lọc
	@Override
	public Page<ShowProduct> getListProductByFilter(String nameSearch, String price, String manu, String sort,
													Pageable pageable, String status, String name, String category) {
		// Tạo criteria builder để tạo các query dựa trên tiêu chí
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		Root<Product> from = cq.from(Product.class);

		Predicate preStatus;

		// Xác định tiêu chí dựa trên status
		if (status.equals("danh-sach")) {
			preStatus = cb.like(from.get("category").get("Namesearch"), "%" + nameSearch + "%");
		} else if (status.equals("tim-kiem")) {
			Predicate preCategory = null;
			if (name.length() != 0) {
				name = "%" + name + "%";
			}
			preStatus = cb.like(from.get("name"), name);
			if (category.length() != 0) {
				preCategory = cb.like(from.get("category").get("Namesearch"), "%" + category + "%");
				preStatus = cb.and(preStatus, preCategory);
			}
		} else {
			preStatus = cb.notEqual(from.get("sales"), 0);
		}

		Predicate preActive = cb.equal(from.get("active"), 1);
		Predicate preDeleteDay = cb.isNull(from.get("Deleteday"));

		int check = 0;
		Predicate prePrice = null;
		Predicate preManu = null;

		// Xác định tiêu chí dựa trên giá
		if (price != null) {
			int min = 0;
			int max = 999999999;
			if (price.equals("1")) {
				max = 150000;
			} else if (price.equals("2")) {
				min = 150000;
				max = 300000;
			} else if (price.equals("3")) {
				min = 300000;
				max = 500000;
			} else if (price.equals("4")) {
				min = 500000;
				max = 700000;
			} else {
				min = 700000;
			}
			check = 1;
			prePrice = cb.between(from.get("price"), min, max);
		}

		// Xác định tiêu chí dựa trên nhà sản xuất
		if (manu != null) {
			preManu = cb.equal(from.get("manufacturer").get("id"), Integer.parseInt(manu));
			if (check == 1) {
				check = 3;
			} else {
				check = 2;
			}
		}

		// Xây dựng câu lệnh query dựa trên các tiêu chí đã xác định
		if (check == 1) {
			cq.where(prePrice, preActive, preDeleteDay, preStatus);
		} else if (check == 2) {
			cq.where(preManu, preActive, preDeleteDay, preStatus);
		} else if (check == 3) {
			cq.where(prePrice, preManu, preActive, preDeleteDay, preStatus);
		} else {
			cq.where(preActive, preDeleteDay, preStatus);
		}

		// Xác định cách sắp xếp kết quả
		if (sort != null) {
			if (sort.equals("0")) {
				cq.orderBy(cb.desc(from.get("Createday")));
			}
			if (sort.equals("1")) {
				cq.orderBy(cb.asc(from.get("name")));
			}
			if (sort.equals("2")) {
				cq.orderBy(cb.desc(from.get("name")));
			}
			if (sort.equals("3")) {
				cq.orderBy(cb.asc(from.get("price")));
			}
			if (sort.equals("4")) {
				cq.orderBy(cb.desc(from.get("price")));
			}
		} else {
			cq.orderBy(cb.desc(from.get("Createday")));
		}

		// Tạo và thực thi truy vấn
		TypedQuery<Product> q = em.createQuery(cq);

		// Lấy danh sách kết quả
		List<Product> countAllItems = q.getResultList();

		// Thiết lập phân trang
		q.setFirstResult(Math.toIntExact(pageable.getOffset()));
		q.setMaxResults(pageable.getPageSize());

		List<Product> getAllItems = q.getResultList();

		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		// Đối với mỗi sản phẩm trong danh sách kết quả, lấy số lượng sao của bình luận
		// và thêm vào danh sách đối tượng ShowProduct
		for (Product product : getAllItems) {
			ShowProduct showProduct = new ShowProduct();
			int totalStar = commentService.getAllStarCommentByProductNameSearch(product.getNamesearch());
			showProduct.setProduct(product);
			showProduct.setTotalStar(totalStar);
			listProduct.add(showProduct);
		}

		// Tạo trang mới dựa trên danh sách sản phẩm đã được thêm thông tin về số lượng
		// sao
		Page<ShowProduct> page = new PageImpl<ShowProduct>(listProduct, pageable, countAllItems.size());

		return page;
	}

	// Lấy thông tin sản phẩm dựa trên tên tìm kiếm
	@Override
	public Product getProductByNameSearch(String nameSearch) {
		return productDao.getProductByNameSearch(nameSearch);
	}

	// Lấy danh sách sản phẩm liên quan dựa trên ID của sản phẩm
	@Override
	public List<Product> getListProductRelated(int id) {
		return productDao.getListProductRelated(id);
	}

	// Cập nhật lượt xem của sản phẩm
	@Override
	public void updateView(String nameSearch) {
		Product product = productDao.getProductByNameSearch(nameSearch);
		int view = product.getViews();
		product.setViews(view + 1);
		productDao.save(product);
	}

	// Cập nhật số lượng của sản phẩm
	@Override
	public void updateQuality(Product product) {
		productDao.save(product);
	}

	// Lấy danh sách sản phẩm có doanh số bán hàng cao nhất
	@Override
	public List<Product> getListProductSales() {
		return productDao.getListProductSales();
	}

}
