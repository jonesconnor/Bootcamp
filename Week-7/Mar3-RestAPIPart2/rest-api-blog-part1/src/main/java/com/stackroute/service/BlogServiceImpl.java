package com.stackroute.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.domain.Blog;
import com.stackroute.exceptions.BlogAlreadyExistsException;
import com.stackroute.exceptions.BlogNotFoundException;
import com.stackroute.repository.BlogRepository;

/* Add annotation to declare this class as Service class.
 * Also it should implement BlogService Interface and override all the implemented methods.*/
@Service
public class BlogServiceImpl implements BlogService{
	
	@Autowired
	BlogRepository blogrepo;
	
	@Override
	public Blog saveBlog(Blog blog) throws BlogAlreadyExistsException {
		
		Optional<Blog> optblogexists = blogrepo.findById(blog.getBlogId());
		if(optblogexists.isPresent()) {
			throw new BlogAlreadyExistsException("Blog already exists");
		} else {
			return blogrepo.save(blog);
		}
	}
	
	@Override
	public List<Blog> getAllBlogs() {
		
		return blogrepo.findAll();
		
	}
	
	@Override
	public Blog getBlogById(int id) {
		return blogrepo.findById(id).orElse(null);
	}
	
	@Override
	public Blog deleteBlog(int id) throws BlogNotFoundException {
		
		Optional<Blog> optblog = blogrepo.findById(id);
		
		if (optblog.isPresent()) {
			blogrepo.deleteById(id);
			return optblog.get();
		} else {
			throw new BlogNotFoundException("Blog not found");
		}
		
	}
	
	@Override
	public Blog updateBlog(Blog blog) throws BlogNotFoundException {
		
		Optional<Blog> optblog = blogrepo.findById(blog.getBlogId());
		
		if (optblog.isPresent()) {
			return blogrepo.save(blog);
		} else {
			throw new BlogNotFoundException("Blog not found");
		}
		
	}

}
