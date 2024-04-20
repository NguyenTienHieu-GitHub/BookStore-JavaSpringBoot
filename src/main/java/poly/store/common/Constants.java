package poly.store.common;

/**
 * Class chứa các hằng số.
 */
public class Constants {

	/* List màn hình người dùng */
	// Đường dẫn đến màn hình trang chủ người dùng
	public static final String USER_DISPLAY_INDEX = "user/home/index";
	// Đường dẫn đến màn hình blog
	public static final String USER_DISPLAY_BLOG = "user/blog/blog_index";
	// Đường dẫn đến màn hình chi tiết blog
	public static final String USER_DISPLAY_BLOG_DETAIL = "user/blog/blog_detail";
	// Đường dẫn đến màn hình đăng nhập người dùng
	public static final String USER_DISPLAY_LOGIN = "user/security/login";
	// Đường dẫn đến màn hình đăng ký
	public static final String USER_DISPLAY_REGISTER = "user/security/register";
	// Đường dẫn đến màn hình xác nhận mã
	public static final String USER_DISPLAY_CONFIRM_CODE = "user/security/confirm-code";
	// Đường dẫn đến màn hình thành công
	public static final String USER_DISPLAY_ALERT_STATUS = "user/security/success";
	// Đường dẫn đến màn hình quên mật khẩu
	public static final String USER_DISPLAY_FORGET_PASSWORD = "user/security/forget-password";
	// Đường dẫn đến màn hình đặt lại mật khẩu
	public static final String USER_DISPLAY_RESET_PASSWORD = "user/security/reset-password";
	// Đường dẫn đến màn hình 404
	public static final String USER_DISPLAY_404_PAGE = "user/security/404page";
	// Đường dẫn đến màn hình danh sách sản phẩm theo danh mục
	public static final String USER_DISPLAY_LIST_PRODUCT_BY_CATEGORY = "user/list/list_by_category";
	// Đường dẫn đến màn hình danh sách sản phẩm theo ưu đãi
	public static final String USER_DISPLAY_LIST_PRODUCT_BY_SALES = "user/list/list_by_sales";
	// Đường dẫn đến màn hình tìm kiếm
	public static final String USER_DISPLAY_LIST_PRODUCT_BY_SEARCH = "user/list/list_by_search";
	// Đường dẫn đến màn hình chi tiết sản phẩm
	public static final String USER_DISPLAY_DETAIL_PRODUCT = "user/detail/product_detail";
	// Đường dẫn đến màn hình giỏ hàng
	public static final String USER_DISPLAY_SHOPPING_CART = "user/cart/shopping_cart";
	// Đường dẫn đến màn hình thanh toán
	public static final String USER_DISPLAY_CHECKOUT = "user/checkout/checkout_form";
	// Đường dẫn đến màn hình thanh toán thành công
	public static final String USER_DISPLAY_CHECKOUT_SUCCESS = "user/checkout/success";
	// Đường dẫn đến màn hình tài khoản
	public static final String USER_DISPLAY_ACCOUNT_PAGE = "user/account/account_index";
	// Đường dẫn đến màn hình thông tin tài khoản
	public static final String USER_DISPLAY_ACCOUNT_INFORMATION = "user/account/account_information";
	// Đường dẫn đến màn hình thay đổi mật khẩu
	public static final String USER_DISPLAY_ACCOUNT_CHANGE_PASSWORD = "user/account/account_change_password";
	// Đường dẫn đến màn hình địa chỉ
	public static final String USER_DISPLAY_ACCOUNT_ADDRESS = "user/account/account_address";
	// Đường dẫn đến màn hình thêm địa chỉ
	public static final String USER_DISPLAY_ACCOUNT_ADDRESS_ADD = "user/account/account_address_add";
	// Đường dẫn đến màn hình yêu thích
	public static final String USER_DISPLAY_ACCOUNT_FAVORITE = "user/account/account_favorite";
	// Đường dẫn đến màn hình liên hệ
	public static final String USER_DISPLAY_CONTACT = "user/contact/contact_form";
	// Đường dẫn đến màn hình lịch sử đơn hàng
	public static final String USER_DISPLAY_ACCOUNT_ORDER = "user/account/account_order";
	// Đường dẫn đến màn hình hóa đơn chi tiết
	public static final String USER_DISPLAY_ACCOUNT_INVOICE = "user/account/account_invoice";
	// Đường dẫn đến màn hình tìm kiếm đơn hàng
	public static final String USER_DISPLAY_ACCOUNT_ORDER_SEARCH = "user/account/account_order_search";
	// Đường dẫn đến màn hình Giới thiệu
	public static final String USER_DISPLAY_IMFORMATION_ABOUT_US = "user/information/about_us";
	// Đường dẫn đến màn hình giao hàng
	public static final String USER_DISPLAY_IMFORMATION_DELIVERY = "user/information/delivery";
	// Đường dẫn đến màn hình chính sách
	public static final String USER_DISPLAY_IMFORMATION_POLICY = "user/information/policy";
	// Đường dẫn đến màn hình điều khoản
	public static final String USER_DISPLAY_IMFORMATION_TERM_CONDITION = "user/information/term_condition";

	/* List màn hình quản trị */
	// Đường dẫn đến màn hình trang chủ quản trị
	public static final String USER_DISPLAY_ADMIN_INDEX = "admin/dashboard/index";
	// Đường dẫn đến màn hình quản lý danh sách nhân viên
	public static final String USER_DISPLAY_ADMIN_EMPLOYEE_LIST = "admin/employees/list";
	// Đường dẫn đến màn hình quản lý thông tin nhân viên
	public static final String USER_DISPLAY_ADMIN_EMPLOYEE_FORM = "admin/employees/form";
	// Đường dẫn đến màn hình quản lý danh sách danh mục
	public static final String USER_DISPLAY_ADMIN_CATEGORY_LIST = "admin/categories/list";
	// Đường dẫn đến màn hình quản lý thông tin danh mục
	public static final String USER_DISPLAY_ADMIN_CATEGORY_FORM = "admin/categories/form";
	// Đường dẫn đến màn hình quản lý danh sách nhà sản xuất
	public static final String USER_DISPLAY_ADMIN_MANUFACTURER_LIST = "admin/manufactures/list";
	// Đường dẫn đến màn hình quản lý thông tin nhà sản xuất
	public static final String USER_DISPLAY_ADMIN_MANUFACTURER_FORM = "admin/manufactures/form";
	// Đường dẫn đến màn hình quản lý thông tin menu cấp 1
	public static final String USER_DISPLAY_ADMIN_NAV_FORM = "admin/menu/form";
	// Đường dẫn đến màn hình quản lý danh sách menu cấp 1
	public static final String USER_DISPLAY_ADMIN_NAV_LIST = "admin/menu/list";
	// Đường dẫn đến màn hình quản lý thông tin menu cấp 2
	public static final String USER_DISPLAY_ADMIN_NAV2_FORM = "admin/menu2/form";
	// Đường dẫn đến màn hình quản lý danh sách menu cấp 2
	public static final String USER_DISPLAY_ADMIN_NAV2_LIST = "admin/menu2/list";
	// Đường dẫn đến màn hình quản lý thông tin cửa hàng
	public static final String USER_DISPLAY_ADMIN_SHOP_FORM = "admin/informationShop/form";
	// Đường dẫn đến màn hình quản lý danh sách thông tin cửa hàng
	public static final String USER_DISPLAY_ADMIN_SHOP_LIST = "admin/informationShop/list";
	// Đường dẫn đến màn hình quản lý danh sách sản phẩm
	public static final String USER_DISPLAY_ADMIN_PRODUCT_LIST = "admin/products/list";
	// Đường dẫn đến màn hình quản lý thông tin sản phẩm
	public static final String USER_DISPLAY_ADMIN_PRODUCT_FORM = "admin/products/form";
	// Đường dẫn đến màn hình tạo thông số sản phẩm
	public static final String USER_DISPLAY_ADMIN_EXTEND_SPECIFICATION = "admin/extend/create-specification";
	// Đường dẫn đến màn hình chỉnh sửa hình ảnh
	public static final String USER_DISPLAY_ADMIN_EXTEND_CROP_IMAGE = "admin/extend/crop-image";
	// Đường dẫn đến màn hình quản lý danh sách mã giảm giá
	public static final String USER_DISPLAY_ADMIN_DISCOUNT_LIST = "admin/discount/list";
	// Đường dẫn đến màn hình quản lý danh sách người dùng nhận mã giảm giá
	public static final String USER_DISPLAY_ADMIN_DISCOUNT_USER_LIST = "admin/discount/listUserDiscount";
	// Đường dẫn đến màn hình quản lý thông tin mã giảm giá
	public static final String USER_DISPLAY_ADMIN_DISCOUNT_FORM = "admin/discount/form";
	// Đường dẫn đến màn hình quản lý binh luận
	public static final String USER_DISPLAY_ADMIN_COMMENT_PENDING = "admin/comment/list_pending";
	// Đường dẫn đến màn hình quản lý binh luận
	public static final String USER_DISPLAY_ADMIN_COMMENT_APPROVED = "admin/comment/list_approved";
	// Đường dẫn đến màn hình đơn hàng đang chờ xử lý
	public static final String USER_DISPLAY_ADMIN_ORDER_PENDING = "admin/order/order_pending";
	// Đường dẫn đến màn hình đơn hàng đang chờ giao
	public static final String USER_DISPLAY_ADMIN_ORDER_SHIPPING = "admin/order/order_shipping";
	// Đường dẫn đến màn hình đơn hàng thành công
	public static final String USER_DISPLAY_ADMIN_ORDER_SUCCESS = "admin/order/order_success";
	// Đường dẫn đến màn hình đơn hàng bị hủy
	public static final String USER_DISPLAY_ADMIN_ORDER_CANCEL = "admin/order/order_cancel";
	// Đường dẫn đến màn hình quản lý thông tin blog
	public static final String USER_DISPLAY_ADMIN_BLOG_FORM = "admin/blog/blog_form";
	// Đường dẫn đến màn hình quản lý danh sách blog
	public static final String USER_DISPLAY_ADMIN_BLOG_LIST = "admin/blog/blog_list";
	// Đường dẫn đến màn hình thống kê sản phẩm bán chạy theo ngày
	public static final String USER_DISPLAY_ADMIN_STATISTICAL_PRODUCT_DAY = "admin/statisticalProduct/top_product";
	// Đường dẫn đến màn hình thống kê sản phẩm tồn kho
	public static final String USER_DISPLAY_ADMIN_STATISTICAL_WAREHOUSE_PRODUCT = "admin/statisticalProduct/warehouse_product";
	// Đường dẫn đến màn hình quản lý danh sách đánh giá
	public static final String USER_DISPLAY_ADMIN_CONTACT_PENDING = "admin/contact/list_pending";
	// Đường dẫn đến màn hình quản lý danh sách đánh giá
	public static final String USER_DISPLAY_ADMIN_CONTACT_APPROVED = "admin/contact/list_approved";
	// Đường dẫn đến màn hình thống kê doanh thu
	public static final String USER_DISPLAY_ADMIN_STATISTICAL_REVENUE = "admin/statisticalRevenue/list";
	// Đường dẫn đến màn hình thống kê đơn hàng
	public static final String USER_DISPLAY_ADMIN_STATISTICAL_ORDER = "admin/statisticalOrder/list";
	// Đường dẫn đến màn hình quản lý thông tin người dùng
	public static final String USER_DISPLAY_ADMIN_USER_FORM = "admin/user/form";
	// Đường dẫn đến màn hình quản lý danh sách thông tin người dùng
	public static final String USER_DISPLAY_ADMIN_USER_LIST = "admin/user/list";
}
