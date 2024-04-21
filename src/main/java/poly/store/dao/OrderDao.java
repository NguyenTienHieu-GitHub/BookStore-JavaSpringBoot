package poly.store.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.store.entity.Order;
import poly.store.model.BestSellerModel;
import poly.store.model.OrderModel;
import poly.store.model.StatisticalOrder;
import poly.store.model.StatisticalProductDay;

public interface OrderDao extends JpaRepository<Order, Integer>{

	// Lấy danh sách đơn hàng dựa trên mã đơn hàng
	@Query("SELECT o FROM Order o WHERE o.code = ?1")
	List<Order> getOrderByName(String code);

	// Lấy lịch sử đơn hàng của một người dùng dựa trên email của họ
	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality), o.date, o.status) FROM Order o WHERE o.address.user.email = ?1 GROUP BY o.code, o.address.Fullname, o.date, o.status ORDER BY o.date ASC")
	List<OrderModel> listOrderHistory(String email);

	// Lấy danh sách đơn hàng đang chờ xử lý
	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality), o.date, o.status) FROM Order o WHERE o.status = 0 GROUP BY o.code, o.address.Fullname, o.date, o.status ORDER BY o.date ASC")
	List<OrderModel> listOrderGroupByCodePending();

	// Lấy danh sách đơn hàng đang được vận chuyển
	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality), o.date, o.status) FROM Order o WHERE o.status = 1 GROUP BY o.code, o.address.Fullname, o.date, o.status ORDER BY o.date ASC")
	List<OrderModel> listOrderGroupByCodeShipping();

	// Lấy danh sách đơn hàng đã hoàn thành
	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality), o.date, o.status) FROM Order o WHERE o.status = 2 GROUP BY o.code, o.address.Fullname, o.date, o.status ORDER BY o.date ASC")
	List<OrderModel> listOrderGroupByCodeSuccess();

	// Lấy danh sách đơn hàng đã bị hủy
	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality), o.date, o.status) FROM Order o WHERE o.status = 3 GROUP BY o.code, o.address.Fullname, o.date, o.status ORDER BY o.date ASC")
	List<OrderModel> listOrderGroupByCodeCancel();

	// Lấy danh sách đơn hàng dựa trên mã đơn hàng và tên người dùng
	@Query("SELECT o FROM Order o WHERE o.code = ?1 and o.address.user.email = ?2")
	List<Order> listOrderByCodeAndUsername(String code, String username);

	// Lấy thống kê sản phẩm theo ngày
	@Query("SELECT new StatisticalProductDay(o.product.code, o.product.name, o.product.price, o.product.quality, sum(o.quality)) FROM Order o WHERE CAST(GETDATE() as date) = o.date GROUP BY o.product.code, o.product.name, o.product.price, o.product.quality")
	List<StatisticalProductDay> listStatisticalProductDay();

	// Lấy doanh thu thống kê theo tháng
	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality), o.date, o.status) FROM Order o WHERE (o.status = 2) AND MONTH(o.date) = ?1 AND YEAR(o.date) = ?2 GROUP BY o.code, o.address.Fullname, o.date, o.status ORDER BY o.date ASC")
	List<OrderModel> listStatisticalRevenueMonth(int month, int year);

	// Lấy doanh thu thống kê theo năm
	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality), o.date, o.status) FROM Order o WHERE (o.status = 2) AND YEAR(o.date) = ?1 GROUP BY o.code, o.address.Fullname, o.date, o.status ORDER BY o.date ASC")
	List<OrderModel> listStatisticalRevenueYear(int year);

	// Lấy doanh thu thống kê theo ngày
	@Query("SELECT new OrderModel(o.code, o.address.Fullname, sum(o.quality), sum(o.product.price * o.quality), o.date, o.status) FROM Order o WHERE (o.status = 2) AND DAY(o.date) = ?1 AND MONTH(o.date) = ?2 AND YEAR(o.date) = ?3 GROUP BY o.code, o.address.Fullname, o.date, o.status ORDER BY o.date ASC")
	List<OrderModel> listStatisticalRevenueDay(int day, int month, int year);

	// Lấy số lượng đơn hàng thành công tối đa trong một ngày cụ thể
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 2) AND DAY(o.date) = ?1 AND MONTH(o.date) = ?2 AND YEAR(o.date) = ?3 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderSuccessOnDay(int day, int month, int year);

	// Lấy số lượng đơn hàng đang vận chuyển tối đa trong một ngày cụ thể
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 1) AND DAY(o.date) = ?1 AND MONTH(o.date) = ?2 AND YEAR(o.date) = ?3 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderTransportOnDay(int day, int month, int year);

	// Lấy số lượng đơn hàng đang chờ xử lý tối đa trong một ngày cụ thể
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 0) AND DAY(o.date) = ?1 AND MONTH(o.date) = ?2 AND YEAR(o.date) = ?3 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderWaitOnDay(int day, int month, int year);

	// Lấy số lượng đơn hàng bị hủy tối đa trong một ngày cụ thể
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 3) AND DAY(o.date) = ?1 AND MONTH(o.date) = ?2 AND YEAR(o.date) = ?3 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderCancelOnDay(int day, int month, int year);

	// Lấy số lượng đơn hàng thành công tối đa trong một tháng cụ thể
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 2) AND MONTH(o.date) = ?1 AND YEAR(o.date) = ?2 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderSuccessOnMonth(int month, int year);

	// Lấy số lượng đơn hàng đang vận chuyển tối đa trong một tháng cụ thể
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 1) AND MONTH(o.date) = ?1 AND YEAR(o.date) = ?2 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderTransportOnMonth(int month, int year);

	// Lấy số lượng đơn hàng đang chờ xử lý tối đa trong một tháng cụ thể
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 0) AND MONTH(o.date) = ?1 AND YEAR(o.date) = ?2 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderWaitOnMonth(int month, int year);

	// Lấy số lượng đơn hàng bị hủy tối đa trong một tháng cụ thể
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 3) AND MONTH(o.date) = ?1 AND YEAR(o.date) = ?2 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderCancelOnMonth(int month, int year);

	// Lấy số lượng đơn hàng thành công tối đa trong một năm cụ thể
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 2) AND YEAR(o.date) = ?1 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderSuccessOnYear(int year);

	// Lấy số lượng đơn hàng đang vận chuyển tối đa trong một năm cụ thể
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 1) AND YEAR(o.date) = ?1 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderTransportOnYear(int year);

	// Lấy số lượng đơn hàng đang chờ xử lý tối đa trong một năm cụ thể
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 0) AND YEAR(o.date) = ?1 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderWaitOnYear(int year);

	// Lấy số lượng đơn hàng bị hủy tối đa trong một năm cụ thể
	@Query("SELECT new StatisticalOrder(COUNT(o)) FROM Order o WHERE (o.status = 3) AND YEAR(o.date) = ?1 GROUP BY o.code")
	List<StatisticalOrder> getMaxOrderCancelOnYear(int year);

	// Lấy năm lớn nhất trong danh sách đơn hàng
	@Query("SELECT MAX(YEAR(o.date)) FROM Order o")
	int getMaxYearOrder();

	// Lấy năm nhỏ nhất trong danh sách đơn hàng
	@Query("SELECT MIN(YEAR(o.date)) FROM Order o")
	int getMinYearOrder();

	// Lấy danh sách các sản phẩm bán chạy nhất với phân trang
	@Query("SELECT new BestSellerModel(o.product, sum(o.quality)) FROM Order o WHERE o.product.Deleteday = null AND o.product.active = 1 GROUP BY o.product ORDER BY sum(o.quality) DESC")
	List<BestSellerModel> getListBestSellerProduct(Pageable pageable);
}
