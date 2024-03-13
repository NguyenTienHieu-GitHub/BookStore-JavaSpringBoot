package poly.store.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import poly.store.common.Constants;
import poly.store.service.ContactService;
import poly.store.service.UserService;

@Controller
public class ContactController {
	@Autowired
	ContactService contactService;
	@Autowired
	UserService userService;
	@GetMapping("/contact")
	public String index(Model model) {
		return Constants.USER_DISPLAY_CONTACT;
	}
}
