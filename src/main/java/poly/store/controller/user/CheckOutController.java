package poly.store.controller.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import poly.store.common.Constants;
import poly.store.entity.Address;
import poly.store.entity.Discount;
import poly.store.entity.Order;
import poly.store.entity.Product;
import poly.store.model.CartModel;
import poly.store.service.AddressService;
import poly.store.service.DiscountService;
import poly.store.service.OrderService;
import poly.store.service.ParamService;
import poly.store.service.ProductService;
import poly.store.service.SessionService;
import poly.store.service.impl.MailerServiceImpl;
import poly.store.service.impl.ShoppingCartServiceImpl;

@Controller
public class CheckOutController {
	@Autowired
	AddressService addressService; // Dịch vụ quản lý địa chỉ

	@Autowired
	AddressService provinceService; // Dịch vụ quản lý tỉnh/thành phố

	@Autowired
	ShoppingCartServiceImpl cartService; // Dịch vụ quản lý giỏ hàng

	@Autowired
	DiscountService discountService; // Dịch vụ quản lý mã giảm giá

	@Autowired
	SessionService sessionService; // Dịch vụ quản lý phiên

	@Autowired
	ParamService paramService; // Dịch vụ quản lý tham số

	@Autowired
	OrderService orderService; // Dịch vụ quản lý đơn hàng

	@Autowired
	ProductService productService; // Dịch vụ quản lý sản phẩm

	@Autowired
	MailerServiceImpl mailerService; // Dịch vụ gửi email

	// Hiển thị trang thanh toán
	@GetMapping("/shop/cart/checkout")
	public String index(Model model) {
		List<CartModel> listCartModel = new ArrayList<>(cartService.getItems());
		if (listCartModel.isEmpty()) { // Nếu giỏ hàng trống
			return "redirect:/shop/cart"; // Chuyển hướng về trang giỏ hàng
		}
		model.addAttribute("cart", cartService); // Truyền thông tin giỏ hàng vào model
		return Constants.USER_DISPLAY_CHECKOUT; // Trả về trang thanh toán
	}

	// Xử lý đặt hàng
	@PostMapping("/shop/cart/checkout")
	public String order(Model model) {
		String addressId = paramService.getString("address_id", ""); // Lấy id địa chỉ từ request
		String method = paramService.getString("shipping_method", ""); // Lấy phương thức vận chuyển từ request
		String comment = paramService.getString("comment", null); // Lấy comment từ request

		Address address = addressService.getAddressById(Integer.parseInt(addressId)); // Lấy địa chỉ theo id

		Discount discount = cartService.getDiscount(); // Lấy mã giảm giá từ giỏ hàng

		if (discount.getId() == 0) { // Nếu không có mã giảm giá
			discount = null; // Gán giá trị null cho mã giảm giá
		}

		int code;
		Date date = new Date(); // Lấy ngày hiện tại
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng ngày
		String strDate = formatter.format(date); // Chuyển ngày thành chuỗi theo định dạng

		// Tạo mã đơn hàng
		while (true) {
			code = (int) Math.floor(((Math.random() * 899999) + 100000)); // Sinh mã ngẫu nhiên
			List<Order> list = orderService.getOrderByName(String.valueOf(code)); // Kiểm tra xem mã đã tồn tại chưa
			if (list.isEmpty()) { // Nếu mã không tồn tại
				break; // Dừng vòng lặp
			}
		}

		List<CartModel> listCartModel = new ArrayList<>(cartService.getItems()); // Lấy danh sách sản phẩm trong giỏ hàng
		for (CartModel cart : listCartModel) { // Duyệt qua từng sản phẩm trong giỏ hàng
			Order order = new Order(); // Tạo đối tượng Order mới
			Product product = cart.getProduct(); // Lấy thông tin sản phẩm từ giỏ hàng
			order.setCode(String.valueOf(code)); // Đặt mã đơn hàng
			order.setAddress(address); // Đặt địa chỉ giao hàng
			order.setDiscount(discount); // Đặt mã giảm giá
			order.setProduct(product); // Đặt sản phẩm
			order.setQuality(cart.getQuality()); // Đặt số lượng sản phẩm
			order.setDate(strDate); // Đặt ngày đặt hàng
			order.setMethod(method); // Đặt phương thức vận chuyển
			order.setStatus("0"); // Đặt trạng thái đơn hàng là chưa xử lý
			order.setComment(comment); // Đặt comment
			orderService.save(order); // Lưu đơn hàng vào cơ sở dữ liệu

			product.setQuality(product.getQuality() - cart.getQuality()); // Cập nhật số lượng sản phẩm trong kho
			productService.updateQuality(product); // Cập nhật thông tin sản phẩm trong cơ sở dữ liệu
		}

		if (discount != null) { // Nếu có mã giảm giá
			discountService.updateQuality(discount); // Cập nhật số lần sử dụng của mã giảm giá
		}

		cartService.clear(); // Xóa giỏ hàng sau khi đặt hàng thành công
		cartService.clearDiscount(); // Xóa mã giảm giá sau khi đặt hàng thành công
		model.addAttribute("cart", cartService); // Truyền thông tin giỏ hàng vào model

		// Gửi email thông báo đặt hàng thành công
		mailerService.queue(address.getUser().getEmail(), "Đặt Hàng Thành Công Tại Web BookStore Fahasha",
				"Kính chào " + address.getUser().getFullname() + ",<br>"
						+ "Cảm ơn bạn đã mua hàng tại FAHASA. Mã đơn hàng của bạn là " + code + "<br>"
						+ "Xin vui lòng click vào đường link http://localhost:8080/account/order/invoice/" + code + " để xem chi tiết hóa đơn.<br>"
						+ "<br><br>"
						+ "Xin chân thành cảm ơn đã sử dụng dịch vụ,<br>"
						+ "BOOKSTORE FASAHA");

		return "redirect:/shop/cart/checkout/success"; // Chuyển hướng đến trang thông báo đặt hàng thành công
	}

	// Hiển thị trang thông báo đặt hàng thành công
	@GetMapping("/shop/cart/checkout/success")
	public String success(Model model) {
		return Constants.USER_DISPLAY_CHECKOUT_SUCCESS; // Trả về trang thông báo đặt hàng thành công
	}

	// Tính tổng số tiền của đơn hàng
	@ModelAttribute("total")
	public int total() {
		List<CartModel> list = new ArrayList<>(cartService.getItems());
		int total = 0;
		for (CartModel i : list) {
			total = total + i.getProduct().getPrice() * i.getQuality(); // Tính tổng số tiền
		}
		return total;
	}

	// Lấy danh sách địa chỉ giao hàng của người dùng đăng nhập
	@ModelAttribute("listAddress")
	public List<Address> getListAddress(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // Lấy thông tin người dùng đăng nhập
		String username = ((UserDetails) principal).getUsername(); // Lấy email của người dùng
		return addressService.findListAddressByEmail(username); // Trả về danh sách địa chỉ giao hàng của người dùng
	}
}
