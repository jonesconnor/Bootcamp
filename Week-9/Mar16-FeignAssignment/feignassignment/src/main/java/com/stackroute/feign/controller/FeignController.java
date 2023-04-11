package com.stackroute.feign.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.feign.model.Blog;
import com.stackroute.feign.model.News;
import com.stackroute.feign.service.FeignBlogInter;
import com.stackroute.feign.service.FeignNewsInter;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class FeignController {
	
	@Autowired
	FeignBlogInter blogservice;
	
	@Autowired
	FeignNewsInter newsservice;
	
	@GetMapping("client/blog/view")
	@CircuitBreaker(name="blogservice",fallbackMethod="blogFail")
	public ResponseEntity<List<Blog>> getBlogs() {
		List<Blog> blogs = blogservice.getBlogs();
		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
	}
	
	@GetMapping("client/news/view")
	@CircuitBreaker(name="newsservice",fallbackMethod="newsFail")
	public ResponseEntity<List<News>> getNews() {
		List<News> news = newsservice.getAllNews();
		return new ResponseEntity<List<News>>(news, HttpStatus.OK);
	}
	
}
