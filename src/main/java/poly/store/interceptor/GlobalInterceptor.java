package poly.store.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import poly.store.service.CategoryService;
import poly.store.service.InformationShopService;
import poly.store.service.SessionService;
import poly.store.service.ShoppingCartService;
/**
 * Lớp `GlobalInterceptor` là một interceptor trong Spring MVC được sử dụng để xử lý logic chung trước hoặc sau khi một request được xử lý bởi một controller.
 *
 * Các dependencies:
 * - categoryService: Service để thực hiện các thao tác liên quan đến danh mục sản phẩm.
 * - informationService: Service để thực hiện các thao tác liên quan đến thông tin cửa hàng.
 * - sessionService: Service để thực hiện các thao tác liên quan đến session của người dùng.
 * - cartService: Service để thực hiện các thao tác liên quan đến giỏ hàng.
 *
 * Phương thức `postHandle` được gọi sau khi một request được xử lý bởi một controller và trước khi trả về một response cho client.
 * Trong phương thức này, các danh mục sản phẩm và thông tin cửa hàng được lấy từ các service và đặt vào các thuộc tính của request để sử dụng trong các view.
 * Ngoài ra, service `cartService` được lưu vào session dưới tên `sessionProduct` để duy trì trạng thái của giỏ hàng giữa các request.
 */
@Component
public class GlobalInterceptor implements HandlerInterceptor {
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	InformationShopService informationService;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	ShoppingCartService cartService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.setAttribute("categories", categoryService.findAll());
		request.setAttribute("information", informationService.getOneInformationShop());
		sessionService.set("sessionProduct", cartService);
	}
}
