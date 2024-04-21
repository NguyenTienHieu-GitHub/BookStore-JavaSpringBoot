package poly.store.service;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
	@Autowired
	HttpSession session;

	// Thiết lập giá trị của một thuộc tính trong session
	public void set(String name, Object value) {
		session.setAttribute(name, value);
	}

	// Lấy giá trị của một thuộc tính từ session
	@SuppressWarnings("unchecked")
	public <T> T get(String name) {
		return (T) session.getAttribute(name);
	}

	// Xóa một thuộc tính từ session
	public void remove(String name) {
		session.removeAttribute(name);
	}
}
