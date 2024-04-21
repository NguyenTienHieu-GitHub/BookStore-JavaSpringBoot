package poly.store.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.store.model.DetailOrder;
import poly.store.model.OrderModel;
import poly.store.service.OrderService;

@CrossOrigin("*") // Cho phép truy cập từ các domain khác
@RestController // Đánh dấu đây là một REST Controller
@RequestMapping("/rest/order") // Định nghĩa URL cơ sở cho tất cả các request trong controller này
public class OrderRestController {
	@Autowired
	OrderService orderService; // Sử dụng autowiring để inject một đối tượng của OrderService vào controller

	// Xử lý request GET để lấy danh sách các đơn hàng đang chờ xử lý
	@GetMapping("/pending") // Định nghĩa URL endpoint cho việc lấy danh sách các đơn hàng đang chờ xử lý
	public List<OrderModel> getListOrder() { // Trả về danh sách các đơn hàng đang chờ xử lý
		return orderService.listOrderGroupByCode(); // Gọi phương thức listOrderGroupByCode trong OrderService để lấy danh sách các đơn hàng đang chờ xử lý và trả về kết quả
	}

	// Xử lý request GET để lấy danh sách các đơn hàng đang vận chuyển
	@GetMapping("/shipping") // Định nghĩa URL endpoint cho việc lấy danh sách các đơn hàng đang vận chuyển
	public List<OrderModel> getListOrderShipping() { // Trả về danh sách các đơn hàng đang vận chuyển
		return orderService.listOrderGroupByCodeShipping(); // Gọi phương thức listOrderGroupByCodeShipping trong OrderService để lấy danh sách các đơn hàng đang vận chuyển và trả về kết quả
	}

	// Xử lý request GET để lấy danh sách các đơn hàng đã giao thành công
	@GetMapping("/success") // Định nghĩa URL endpoint cho việc lấy danh sách các đơn hàng đã giao thành công
	public List<OrderModel> getListOrderSuccess() { // Trả về danh sách các đơn hàng đã giao thành công
		return orderService.listOrderGroupByCodeSuccess(); // Gọi phương thức listOrderGroupByCodeSuccess trong OrderService để lấy danh sách các đơn hàng đã giao thành công và trả về kết quả
	}

	// Xử lý request GET để lấy danh sách các đơn hàng đã hủy
	@GetMapping("/cancel") // Định nghĩa URL endpoint cho việc lấy danh sách các đơn hàng đã hủy
	public List<OrderModel> getListOrderCancel() { // Trả về danh sách các đơn hàng đã hủy
		return orderService.listOrderGroupByCodeCancel(); // Gọi phương thức listOrderGroupByCodeCancel trong OrderService để lấy danh sách các đơn hàng đã hủy và trả về kết quả
	}

	// Xử lý request PUT để đánh dấu đơn hàng đã giao
	@PutMapping("/shipped/{id}") // Định nghĩa URL endpoint cho việc đánh dấu đơn hàng đã giao dựa trên id của đơn hàng
	public void shipped(@PathVariable("id") String id) { // Lấy id đơn hàng từ đường dẫn và đánh dấu đơn hàng đã giao
		orderService.shippedOrder(id); // Gọi phương thức shippedOrder trong OrderService để đánh dấu đơn hàng đã giao
	}

	// Xử lý request GET để lấy chi tiết của một đơn hàng dựa trên mã đơn hàng và tên người dùng
	@GetMapping("/pending/{id}") // Định nghĩa URL endpoint cho việc lấy chi tiết của một đơn hàng đang chờ xử lý dựa trên mã đơn hàng
	public DetailOrder listOrderByCodeAndUsername(@PathVariable("id") String id) { // Lấy mã đơn hàng từ đường dẫn và trả về chi tiết của đơn hàng đang chờ xử lý
		return orderService.getDetailOrderByCode(id); // Gọi phương thức getDetailOrderByCode trong OrderService để lấy chi tiết của một đơn hàng đang chờ xử lý và trả về kết quả
	}

	// Xử lý request PUT để xác nhận và phê duyệt đơn hàng
	@PutMapping("/approve/{id}") // Định nghĩa URL endpoint cho việc xác nhận và phê duyệt đơn hàng dựa trên id của đơn hàng
	public void approve(@PathVariable("id") String id) { // Lấy id đơn hàng từ đường dẫn và xác nhận và phê duyệt đơn hàng
		orderService.approveOrder(id); // Gọi phương thức approveOrder trong OrderService để xác nhận và phê duyệt đơn hàng
	}

	// Xử lý request PUT để hủy đơn hàng
	@PutMapping("/cancel/{id}") // Định nghĩa URL endpoint cho việc hủy đơn hàng dựa trên id của đơn hàng
	public void cancel(@PathVariable("id") String id) { // Lấy id đơn hàng từ đường dẫn và hủy đơn hàng
		orderService.cancelOrder(id); // Gọi phương thức cancelOrder trong OrderService để hủy đơn hàng
	}

	// Xử lý request DELETE để xóa đơn hàng
	@DeleteMapping("/delete/{id}") // Định nghĩa URL endpoint cho việc xóa đơn hàng dựa trên id của đơn hàng
	public void delete(@PathVariable("id") String id) { // Lấy id đơn hàng từ đường dẫn và xóa đơn hàng
		orderService.deleteOrder(id); // Gọi phương thức deleteOrder trong OrderService để xóa đơn hàng
	}
}
