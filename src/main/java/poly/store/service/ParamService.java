package poly.store.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ParamService {
	@Autowired
	ServletContext app;
	
	@Autowired
	HttpServletRequest request;

	public String getString(String name, String defaultValue) {
		if (name != null) {
			return request.getParameter(name);
		}
		return defaultValue;
	}

	public int getInt(String name, int defaultValue) {
		if (name != null) {
			return Integer.parseInt(request.getParameter(name));
		}
		return defaultValue;
	}
	
	public double getDouble(String name, double defaultValue) {
		if (name != null) {
			return Double.parseDouble(request.getParameter(name));
		}
		return defaultValue;
	}
	
	public Boolean getBoolean(String name, boolean defaultValue) {
		if (name != null) {
			return Boolean.parseBoolean(request.getParameter(name));
		}
		return defaultValue;
	}
	
	public Date getDate(String name, String pattern) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			String temp = request.getParameter(name);
			Date date = formatter.parse(temp);
			return date;
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public String save(MultipartFile file) {	
		String fileName = null;
		String uploadRootPath = app.getRealPath("/assets/upload");
		File uploadRootDir = new File(uploadRootPath);
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		try {
			fileName = file.getOriginalFilename();
			File serverFile = new File(uploadRootDir.getAbsoluteFile() + File.separator + fileName);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(file.getBytes());
			stream.close();
			System.out.println(serverFile.getAbsolutePath());
		} catch (Exception e) {

		}
		
		return fileName;
	}

	public String convertDate(String date) {
		String[] month = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		String[] words = date.split("-");

		// Kiểm tra xem có đúng 3 phần tử trong mảng không
		if (words.length != 3) {
			throw new IllegalArgumentException("Invalid date format");
		}

		// Kiểm tra xem có phải là số hợp lệ không
		try {
			int monthIndex = Integer.parseInt(words[1]);
			if (monthIndex < 1 || monthIndex > 12) {
				throw new IllegalArgumentException("Invalid month value");
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid month value");
		}

		// Chuyển đổi tháng thành tên tháng
		int monthIndex = Integer.parseInt(words[1]) - 1;
		words[1] = month[monthIndex];

		// Tạo chuỗi ngày tháng mới
		String result = words[1] + " " + words[2] + ", " + words[0];
		return result;
	}
}
