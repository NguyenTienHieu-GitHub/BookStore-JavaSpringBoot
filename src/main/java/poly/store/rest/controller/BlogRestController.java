package poly.store.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poly.store.entity.Blog;
import poly.store.model.BlogModel;
import poly.store.service.BlogService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/blog")
public class BlogRestController {

	@Autowired
	BlogService blogService;

	// API để tạo mới một blog
	@PostMapping("/form")
	public BlogModel create(@RequestBody BlogModel blogModel) {
		return blogService.createBlog(blogModel);
	}

	// API để lấy tất cả các blog
	@GetMapping()
	public List<Blog> getAll(){
		return blogService.findAll();
	}

	// API để lấy một blog dựa trên id
	@GetMapping("/form/{id}")
	public BlogModel getOneUserById(@PathVariable("id") Integer id) {
		return blogService.getOneBlogById(id);
	}

	// API để xóa một blog dựa trên id
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		blogService.delete(id);
	}

	// API để cập nhật thông tin một blog
	@PutMapping("/form/{id}")
	public BlogModel update(@PathVariable("id") Integer id, @RequestBody BlogModel blogModel) {
		return blogService.updateCategory(blogModel);
	}
}
