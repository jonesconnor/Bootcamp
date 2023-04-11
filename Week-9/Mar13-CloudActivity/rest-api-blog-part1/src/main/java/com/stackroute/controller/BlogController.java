package com.stackroute.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.domain.Blog;
import com.stackroute.exceptions.BlogAlreadyExistsException;
import com.stackroute.exceptions.BlogNotFoundException;
import com.stackroute.service.BlogService;

/* Add annotation to declare this class as REST Controller */
@RestController
@RequestMapping("/blog")
public class BlogController {

    /* Provide implementation code for these methods */
	@Autowired
	BlogService bserve;

    /*This method should save blog and return savedBlog Object */
	@PostMapping("/saveblog")
    public ResponseEntity<?> saveBlog(@RequestBody Blog newBlog) throws BlogAlreadyExistsException{
    	try {
    		Blog blogadded = bserve.saveBlog(newBlog);
    		return new ResponseEntity<Blog>(blogadded,HttpStatus.CREATED);
    	} catch(BlogAlreadyExistsException e) {
    		return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT );
    	}
    }

    /*This method should fetch all blogs and return the list of all blogs */
	@GetMapping("/viewblogs")
    public ResponseEntity<?> getAllBlogs() {
		List<Blog> blogs = bserve.getAllBlogs();
        return new ResponseEntity<List<Blog>>(blogs,HttpStatus.OK);
    }

    /*This method should fetch the blog taking its id and return the respective blog */
	// change made here -- was public ResponseEntity<Blog>
	@GetMapping("/blog/{id}")
    public ResponseEntity<?> getBlogById(@PathVariable("id") String blogId){
    	try {
    		Blog desiredBlog = bserve.getBlogById(Integer.parseInt(blogId));
    		return new ResponseEntity<Blog>(desiredBlog, HttpStatus.FOUND);
    	} catch (RuntimeException e) {
    		return ResponseEntity.notFound().build();
    	}
    }

    /*This method should delete the blog taking its id and return the deleted blog */
	@DeleteMapping("/deleteblog/{blogid}")
    public ResponseEntity<?> getBlogAfterDeleting(@PathVariable("blogid") String blogId) throws BlogNotFoundException {
		try {
			Blog result = bserve.deleteBlog(Integer.parseInt(blogId));
			return new ResponseEntity<Blog>(result, HttpStatus.OK);
		} catch (BlogNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT );
		}
    }

    /*This method should update blog and return the updatedBlog */
	@PutMapping("/updateblog")
    public ResponseEntity<?> updateBlog(@RequestBody Blog blogtoupdate) throws BlogNotFoundException {
		try {
			Blog resultobj = bserve.updateBlog(blogtoupdate);
			return new ResponseEntity<Blog>(resultobj, HttpStatus.FOUND);
		} catch (BlogNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT );
		}
    }
}