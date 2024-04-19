package poly.store.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import poly.store.common.Constants;
import poly.store.entity.Address;
import poly.store.service.AddressService;

@Controller
public class AddressController {
	@Autowired
	AddressService addressService;

	// Xử lý yêu cầu GET để hiển thị địa chỉ của người dùng
	@GetMapping("/account/address")
	public String index() {
		// Trả về tên view để hiển thị địa chỉ của người dùng
		return Constants.USER_DISPLAY_ACCOUNT_ADDRESS;
	}

	// Xử lý yêu cầu GET để thêm một địa chỉ mới
	@GetMapping("/account/address/add")
	public String add(Model model) {
		// Thêm một thuộc tính vào model để bật/tắt nút cập nhật
		model.addAttribute("enableBtnUpdate", false);
		// Trả về tên view để thêm một địa chỉ mới
		return Constants.USER_DISPLAY_ACCOUNT_ADDRESS_ADD;
	}

	// Xử lý yêu cầu GET để xóa một địa chỉ bằng ID của nó
	@GetMapping("/account/address/delete/{id}")
	public String add(@PathVariable("id") int id) {
		// Lấy địa chỉ bằng ID của nó
		Address address = addressService.getAddressById(id);
		// Xóa địa chỉ
		addressService.delete(address);
		// Chuyển hướng đến view để hiển thị địa chỉ của người dùng
		return "redirect:/account/address";
	}

	// Xử lý yêu cầu GET để cập nhật một địa chỉ bằng ID của nó
	@GetMapping("/account/address/update/{id}")
	public String update(@PathVariable("id") int id, Model model) {
		// Lấy thông tin người dùng đang đăng nhập
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		// Tìm địa chỉ bằng ID và tên người dùng hiện tại
		Address address = addressService.findAddressById(username, id);
		// Nếu không tìm thấy địa chỉ, chuyển hướng đến view hiển thị địa chỉ của người dùng
		if (address == null) {
			return "redirect:/account/address";
		}
		// Thêm ID địa chỉ và bật nút cập nhật vào model
		model.addAttribute("addressId", id);
		model.addAttribute("enableBtnUpdate", true);
		// Trả về tên view để thêm/cập nhật một địa chỉ
		return Constants.USER_DISPLAY_ACCOUNT_ADDRESS_ADD;
	}

	// Thêm một thuộc tính model cho danh sách địa chỉ được liên kết với người dùng hiện tại
	@ModelAttribute("listAddress")
	public List<Address> getListAddress(Model model) {
		// Lấy thông tin người dùng đang đăng nhập
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		// Lấy danh sách địa chỉ được liên kết với tên người dùng hiện tại
		return addressService.findListAddressByEmail(username);
	}
}
