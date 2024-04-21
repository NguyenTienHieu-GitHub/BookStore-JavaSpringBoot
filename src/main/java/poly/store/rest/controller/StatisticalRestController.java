package poly.store.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.store.entity.Product;
import poly.store.model.StatisticalProductDay;
import poly.store.model.StatisticalRevenue;
import poly.store.model.StatisticalTotalOrder;
import poly.store.service.OrderService;

@CrossOrigin("*") // Cho phép truy cập từ các domain khác
@RestController // Đánh dấu đây là một REST Controller
@RequestMapping("/rest") // Định nghĩa URL cơ sở cho tất cả các request trong controller này
public class StatisticalRestController {
	@Autowired
	OrderService orderService; // Sử dụng autowiring để inject một đối tượng của OrderService vào controller

	// Xử lý request GET để lấy danh sách thống kê sản phẩm theo ngày
	@GetMapping("/statistical/product/day") // Định nghĩa URL endpoint cho việc lấy danh sách thống kê sản phẩm theo ngày
	public List<StatisticalProductDay> productDay(){ // Trả về danh sách thống kê sản phẩm theo ngày
		return orderService.listStatisticalProductDay(); // Gọi phương thức listStatisticalProductDay trong OrderService để lấy danh sách thống kê sản phẩm theo ngày và trả về kết quả
	}

	// Xử lý request GET để lấy danh sách sản phẩm trong kho
	@GetMapping("/statistical/product/warehouse") // Định nghĩa URL endpoint cho việc lấy danh sách sản phẩm trong kho
	public List<Product> warehouse(){ // Trả về danh sách sản phẩm trong kho
		return orderService.listStatisticalProductWarehouse(); // Gọi phương thức listStatisticalProductWarehouse trong OrderService để lấy danh sách sản phẩm trong kho và trả về kết quả
	}

	// Xử lý request GET để lấy danh sách doanh thu theo ngày trong một tháng và năm cụ thể
	@GetMapping("/statistical/revenue/day/{month}/{year}") // Định nghĩa URL endpoint cho việc lấy danh sách doanh thu theo ngày trong một tháng và năm cụ thể
	public List<StatisticalRevenue> listRevenueByDay(@PathVariable("month") int month, @PathVariable("year") int year){ // Trả về danh sách doanh thu theo ngày trong một tháng và năm cụ thể
		return orderService.listStatisticalRevenue(month, year); // Gọi phương thức listStatisticalRevenue trong OrderService để lấy danh sách doanh thu theo ngày trong một tháng và năm cụ thể và trả về kết quả
	}

	// Xử lý request GET để lấy danh sách doanh thu theo tháng trong một năm cụ thể
	@GetMapping("/statistical/revenue/month/{year}") // Định nghĩa URL endpoint cho việc lấy danh sách doanh thu theo tháng trong một năm cụ thể
	public List<StatisticalRevenue> listRevenueByMonth(@PathVariable("year") int year){ // Trả về danh sách doanh thu theo tháng trong một năm cụ thể
		return orderService.listStatisticalRevenueByMonth(year); // Gọi phương thức listStatisticalRevenueByMonth trong OrderService để lấy danh sách doanh thu theo tháng trong một năm cụ thể và trả về kết quả
	}

	// Xử lý request GET để lấy danh sách doanh thu theo năm cụ thể
	@GetMapping("/statistical/revenue/year/{year}") // Định nghĩa URL endpoint cho việc lấy danh sách doanh thu theo năm cụ thể
	public List<StatisticalRevenue> listRevenueByYear(@PathVariable("year") int year){ // Trả về danh sách doanh thu theo năm cụ thể
		return orderService.listStatisticalRevenueByYear(year); // Gọi phương thức listStatisticalRevenueByYear trong OrderService để lấy danh sách doanh thu theo năm cụ thể và trả về kết quả
	}

	// Xử lý request GET để lấy thông tin đơn hàng theo ngày trong một tháng và năm cụ thể
	@GetMapping("/statistical/order/day/{day}/{month}/{year}") // Định nghĩa URL endpoint cho việc lấy thông tin đơn hàng theo ngày trong một tháng và năm cụ thể
	public StatisticalTotalOrder listOrderByDay(@PathVariable("day") int day, @PathVariable("month") int month, @PathVariable("year") int year){ // Trả về thông tin đơn hàng theo ngày trong một tháng và năm cụ thể
		return orderService.getStatisticalTotalOrderOnDay(day, month, year); // Gọi phương thức getStatisticalTotalOrderOnDay trong OrderService để lấy thông tin đơn hàng theo ngày trong một tháng và năm cụ thể và trả về kết quả
	}

	// Xử lý request GET để lấy thông tin đơn hàng theo tháng trong một năm cụ thể
	@GetMapping("/statistical/order/month/{month}/{year}") // Định nghĩa URL endpoint cho việc lấy thông tin đơn hàng theo tháng trong một năm cụ thể
	public StatisticalTotalOrder listOrderByMonth(@PathVariable("month") int month, @PathVariable("year") int year){ // Trả về thông tin đơn hàng theo tháng trong một năm cụ thể
		return orderService.getStatisticalTotalOrderOnMonth(month, year); // Gọi phương thức getStatisticalTotalOrderOnMonth trong OrderService để lấy thông tin đơn hàng theo tháng trong một năm cụ thể và trả về kết quả
	}

	// Xử lý request GET để lấy thông tin đơn hàng theo năm cụ thể
	@GetMapping("/statistical/order/year/{year}") // Định nghĩa URL endpoint cho việc lấy thông tin đơn hàng theo năm cụ thể
	public StatisticalTotalOrder listOrderByYear(@PathVariable("year") int year){ // Trả về thông tin đơn hàng theo năm cụ thể
		return orderService.getStatisticalTotalOrderOnYear(year); // Gọi phương thức getStatisticalTotalOrderOnYear trong OrderService để lấy thông tin đơn hàng theo năm cụ thể và trả về kết quả
	}

	// Xử lý request GET để lấy danh sách năm có đơn hàng
	@GetMapping("/statisticalOrder/year") // Định nghĩa URL endpoint cho việc lấy danh sách năm có đơn hàng
	public List<Integer> listYear(){ // Trả về danh sách năm có đơn hàng
		return orderService.getListYearOrder(); // Gọi phương thức getListYearOrder trong OrderService để lấy danh sách năm có đơn hàng và trả về kết quả
	}

	// Xử lý request GET để lấy thông tin đơn hàng theo ngày, tháng và năm cụ thể
	@GetMapping("/statistical/order/option/{day}/{month}/{year}") // Định nghĩa URL endpoint cho việc lấy thông tin đơn hàng theo ngày, tháng và năm cụ thể
	public StatisticalTotalOrder listOrderByOption(@PathVariable("day") int day, @PathVariable("month") int month, @PathVariable("year") int year){ // Trả về thông tin đơn hàng theo ngày, tháng và năm cụ thể
		return orderService.getStatisticalTotalOrderOnOption(day, month, year); // Gọi phương thức getStatisticalTotalOrderOnOption trong OrderService để lấy thông tin đơn hàng theo ngày, tháng và năm cụ thể và trả về kết quả
	}
}
