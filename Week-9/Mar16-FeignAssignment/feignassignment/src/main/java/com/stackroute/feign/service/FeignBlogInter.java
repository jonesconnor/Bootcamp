package com.stackroute.feign.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stackroute.feign.model.Blog;

@FeignClient("blogservice")
public interface FeignBlogInter {
	
	@RequestMapping(method=RequestMethod.GET, value="/api/v1/blogs")
	public List<Blog> getBlogs();
	
}
