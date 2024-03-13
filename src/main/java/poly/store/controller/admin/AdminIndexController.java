/**
 * @(#)AdminIndexController.java 2021/09/10.
 * 
 * Copyright(C) 2021 by PHOENIX TEAM.
 * 
 * Last_Update 2021/09/10.
 * Version 1.00.
 */
package poly.store.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import poly.store.common.Constants;
import poly.store.model.StatisticalRevenue;
import poly.store.service.OrderService;
import poly.store.service.ProductService;
import poly.store.service.UserService;

/**
 * Class de hien thi trang chu quan tri
 * 
 * @author khoa-ph
 * @version 1.00
 */
@Controller
public class AdminIndexController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;
	
	
	/**
	 * Hien thi trang chu cua giao dien nguoi dung
	 * 
	 * @return trang admin index.html
	 */
	@GetMapping("/admin/index")
	public String index(Model model) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    Date date = new Date();
		String[] today = formatter.format(date).toString().split("-");
	    
	    
		long order = orderService.getStatisticalTotalOrderOnMonth(Integer.parseInt(today[1]), Integer.parseInt(today[0])).getOrderSuccess();
		double revenue = 0;
		List<StatisticalRevenue> listRevenue = orderService.listStatisticalRevenue(Integer.parseInt(today[1]), Integer.parseInt(today[0]));
		
		for(StatisticalRevenue item: listRevenue) {
			revenue = revenue + item.getPrice();
		}
		
		long customer = userService.findAllUserNotRoleAdmin().size();
		
		long product = productService.findAll().size();
		
		model.addAttribute("product", product);
		model.addAttribute("customer", customer);
		model.addAttribute("revenue", revenue);
		model.addAttribute("order", order);
		return Constants.USER_DISPLAY_ADMIN_INDEX;
	}
}
