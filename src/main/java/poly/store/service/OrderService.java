package poly.store.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import poly.store.entity.Order;
import poly.store.entity.Product;
import poly.store.model.BestSellerModel;
import poly.store.model.DetailOrder;
import poly.store.model.OrderModel;
import poly.store.model.StatisticalProductDay;
import poly.store.model.StatisticalRevenue;
import poly.store.model.StatisticalTotalOrder;

public interface OrderService {

	// Tìm kiếm đơn hàng theo mã đơn hàng
	List<Order> getOrderByName(String code);

	// Lưu thông tin của một đơn hàng
	void save(Order order);

	// Lấy danh sách lịch sử đơn hàng
	List<OrderModel> listOrderHistory();

	// Lấy danh sách đơn hàng dựa trên mã đơn hàng và tên người dùng
	List<Order> listOrderByCodeAndUsername(String id);

	// Lấy danh sách đơn hàng được nhóm theo mã đơn hàng
	List<OrderModel> listOrderGroupByCode();

	// Lấy chi tiết đơn hàng dựa trên mã đơn hàng
	DetailOrder getDetailOrderByCode(String id);

	// Duyệt đơn hàng dựa trên mã đơn hàng
	void approveOrder(String id);

	// Hủy đơn hàng dựa trên mã đơn hàng
	void cancelOrder(String id);

	// Lấy danh sách đơn hàng được nhóm theo mã đơn hàng (đã vận chuyển)
	List<OrderModel> listOrderGroupByCodeShipping();

	// Đánh dấu đơn hàng đã vận chuyển dựa trên mã đơn hàng
	void shippedOrder(String id);

	// Lấy danh sách đơn hàng được nhóm theo mã đơn hàng (đã thành công)
	List<OrderModel> listOrderGroupByCodeSuccess();

	// Lấy danh sách đơn hàng được nhóm theo mã đơn hàng (đã hủy)
	List<OrderModel> listOrderGroupByCodeCancel();

	// Xóa đơn hàng dựa trên mã đơn hàng
	void deleteOrder(String id);

	// Lấy danh sách thống kê sản phẩm theo ngày
	List<StatisticalProductDay> listStatisticalProductDay();

	// Lấy danh sách thống kê doanh thu theo tháng và năm
	List<StatisticalRevenue> listStatisticalRevenue(int month, int year);

	// Lấy danh sách thống kê doanh thu theo tháng trong một năm
	List<StatisticalRevenue> listStatisticalRevenueByMonth(int year);

	// Lấy danh sách thống kê doanh thu theo năm
	List<StatisticalRevenue> listStatisticalRevenueByYear(int year);

	// Lấy tổng số đơn hàng trong một ngày cụ thể
	StatisticalTotalOrder getStatisticalTotalOrderOnDay(int day, int month, int year);

	// Lấy tổng số đơn hàng trong một tháng cụ thể
	StatisticalTotalOrder getStatisticalTotalOrderOnMonth(int month, int year);

	// Lấy tổng số đơn hàng trong một năm cụ thể
	StatisticalTotalOrder getStatisticalTotalOrderOnYear(int year);

	// Lấy danh sách các năm có đơn hàng
	List<Integer> getListYearOrder();

	// Lấy tổng số đơn hàng dựa trên tuỳ chọn: ngày, tháng, năm
	StatisticalTotalOrder getStatisticalTotalOrderOnOption(int day, int month, int year);

	// Lấy danh sách sản phẩm bán chạy nhất
	List<BestSellerModel> getListBestSellerProduct(Pageable topFour);

	// Lấy danh sách sản phẩm trong kho
	List<Product> listStatisticalProductWarehouse();

}
