package poly.store.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import poly.store.dao.BlogDao;
import poly.store.dao.UserDao;
import poly.store.entity.Blog;
import poly.store.entity.User;
import poly.store.model.BlogModel;
import poly.store.service.BlogService;

@Service
@Repository
public class BlogServiceImpl implements BlogService {
	@Autowired
	BlogDao dao;

	@Autowired
	UserDao userDao;

	// Phương thức tạo mới một bài viết trên blog
	@Override
	public BlogModel createBlog(BlogModel blogModel) {
		// Lấy thông tin người dùng hiện tại từ SecurityContextHolder
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Lấy thời gian hiện tại
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		// Tìm thông tin người dùng dựa trên email
		User temp = userDao.findUserByEmail(username);

		// Tạo một đối tượng Blog từ dữ liệu trong BlogModel
		Blog blog = new Blog();
		blog.setTitle(blogModel.getTitle());
		blog.setLogo(blogModel.getLogo());
		blog.setContent(blogModel.getContent());
		blog.setBanner(blogModel.getBanner());
		blog.setUploadday(blogModel.getUploadDay());
		blog.setPersoncreate(temp.getId());
		blog.setCreateday(timestamp.toString());
		blog.setActive(blogModel.isActive());
		blog.setDescription(blogModel.getDescription());
		blog.setNamesearch(blogModel.getNameSearch());
		dao.save(blog);
		return blogModel;
	}

	// Phương thức lấy danh sách tất cả các bài viết trên blog
	@Override
	public List<Blog> findAll() {
		return dao.getListBlog();
	}

	// Phương thức xóa một bài viết trên blog
	@Override
	public void delete(Integer id) {
		// Lấy thông tin người dùng hiện tại từ SecurityContextHolder
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		// Tìm thông tin người dùng dựa trên email
		User temp = userDao.findUserByEmail(username);
		// Lấy thời gian hiện tại
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		// Tìm bài viết dựa trên ID và gán thông tin người xóa và thời gian xóa
		Blog blog = dao.findById(id).get();
		blog.setPersondelete(temp.getId());
		blog.setDeleteday(timestamp.toString());
		dao.save(blog);
	}

	// Phương thức lấy một bài viết trên blog dựa trên ID
	@Override
	public BlogModel getOneBlogById(Integer id) {
		// Tìm bài viết dựa trên ID
		Blog blog = dao.findById(id).get();
		// Tạo một đối tượng BlogModel và chuyển thông tin từ Blog sang BlogModel
		BlogModel blogModel = new BlogModel();
		blogModel.setTitle(blog.getTitle());
		blogModel.setLogo(blog.getLogo());
		blogModel.setContent(blog.getContent());
		blogModel.setBanner(blog.getBanner());
		blogModel.setUploadDay(blog.getUploadday());
		blogModel.setDescription(blog.getDescription());
		blogModel.setNameSearch(blog.getNamesearch());
		blog.setActive(blogModel.isActive());
		return blogModel;
	}

	// Phương thức cập nhật một bài viết trên blog
	@Override
	public BlogModel updateCategory(BlogModel blogModel) {
		// Lấy thông tin người dùng hiện tại từ SecurityContextHolder
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		// Lấy thời gian hiện tại
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		// Tìm thông tin người dùng dựa trên email
		User temp = userDao.findUserByEmail(username);

		// Tìm bài viết dựa trên ID và cập nhật thông tin mới
		Blog blog = dao.findById(blogModel.getId()).get();
		blog.setTitle(blogModel.getTitle());
		blog.setLogo(blogModel.getLogo());
		blog.setContent(blogModel.getContent());
		blog.setBanner(blogModel.getBanner());
		blog.setUploadday(blogModel.getUploadDay());
		blog.setUpdateday(timestamp.toString());
		blog.setPersonupdate(temp.getId());
		blog.setActive(blogModel.isActive());
		blog.setDescription(blogModel.getDescription());
		blog.setNamesearch(blogModel.getNameSearch());
		dao.save(blog);
		return blogModel;
	}

	// Phương thức lấy một bài viết trên blog dựa trên ID
	@Override
	public Blog findById(Integer id) {
		return dao.findById(id).get();
	}

	// Phương thức lấy danh sách các bài viết trên blog theo trang
	@Override
	public Page<Blog> findAllBlogActive(Pageable pageable) {
		// Lấy danh sách bài viết theo trang từ BlogDao
		Page<Blog> listBlog = dao.findAllBlogActive(pageable);
		return listBlog;
	}

	// Phương thức tìm một bài viết trên blog dựa trên tên
	@Override
	public Blog findBlogByNameSearch(String nameSearch) {
		// Tìm bài viết dựa trên tên
		Blog blog = dao.findBlogByNameSearch(nameSearch);
		return blog;
	}

	// Phương thức lấy 6 bài viết mới nhất trên blog
	@Override
	public List<Blog> getSixBlog() {
		// Lấy danh sách 6 bài viết mới nhất từ BlogDao
		List<Blog> listBlog = dao.getSixBlogs();
		return listBlog;
	}

}
