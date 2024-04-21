package poly.store.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import poly.store.entity.Product;
import poly.store.service.CriteriaDemoService;

@Service
public class CriteriaDemoServiceImpl implements CriteriaDemoService{
	@PersistenceContext
	private EntityManager em;

	// Phương thức lấy tất cả sản phẩm theo tiêu chí sử dụng Criteria API
	@Override
	public List<Product> getAll() {
		// Khởi tạo CriteriaBuilder để tạo các tiêu chí
		CriteriaBuilder cb = em.getCriteriaBuilder();

		// Tạo một CriteriaQuery để xác định loại dữ liệu trả về (trong trường hợp này là Product)
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);

		// Tạo một root để chỉ đến từ (trong trường hợp này là bảng Product)
		Root<Product> from = cq.from(Product.class);

		// Xây dựng các điều kiện (Predicates)
		// Ví dụ: Lọc các sản phẩm có giá bằng 6490000
		Predicate price = cb.equal(from.get("price"), 6490000);

		// Ví dụ: Lọc các sản phẩm có giá nằm trong khoảng từ 3000000 đến 6490000
		Predicate price2 = cb.between(from.get("price"), 3000000, 6490000);

		// Ghép các điều kiện lại với nhau (trong trường hợp này, chỉ sử dụng điều kiện price2)
		cq.where(price2);

		// Tạo một TypedQuery từ CriteriaQuery đã được xác định
		TypedQuery<Product> q = em.createQuery(cq);

		// Thực hiện truy vấn và lấy danh sách kết quả
		List<Product> allItems = q.getResultList();

		return allItems;
	}

}
