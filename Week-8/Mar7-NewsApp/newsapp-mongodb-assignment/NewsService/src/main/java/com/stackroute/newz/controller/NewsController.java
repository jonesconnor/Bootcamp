package com.stackroute.newz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.newz.model.News;
import com.stackroute.newz.model.UserNews;
import com.stackroute.newz.service.NewsService;
import com.stackroute.newz.util.exception.NewsNotFoundException;

/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation.A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 */
@RestController
public class NewsController {

	/*
	 * Autowiring should be implemented for the NewsService. (Use Constructor-based
	 * autowiring) Please note that we should not create any object using the new
	 * keyword
	 */
	private NewsService newsservice;
	
	@Autowired
	public NewsController(NewsService newsService) {
		this.newsservice = newsService;
	}
	
	@PostMapping("/api/v1/adduser")
	public ResponseEntity<?> addUser(@RequestBody UserNews user) {
		UserNews result = newsservice.addUser(user);
		return new ResponseEntity<UserNews>(result, HttpStatus.CREATED);
	}

	/*
	 * Define a handler method which will create a specific news by reading the
	 * Serialized object from request body and save the news details in the
	 * database.This handler method should return any one of the status messages
	 * basis on different situations: 
	 * 1. 201(CREATED) - If the news created successfully. 
	 * 2. 409(CONFLICT) - If the newsId conflicts with any existing user.
	 * 
	 * This handler method should map to the URL "/api/v1/news/{userid}" using HTTP POST method
	 */
	@PostMapping("/api/v1/news/{userid}")
	public ResponseEntity<String> addNews(@PathVariable("userid") String userId, @RequestBody News news) {
		boolean result = newsservice.addNews(userId, news);
		if (result) {
			return new ResponseEntity<String>("News successfully added", HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("NewsId conflicts with existing user", HttpStatus.CONFLICT);
			
	}

	/*
	 * Define a handler method which will delete a news from a database.
	 * This handler method should return any one of the status messages basis 
	 * on different situations: 
	 * 1. 200(OK) - If the news deleted successfully from database. 
	 * 2. 404(NOT FOUND) - If the news with specified newsId is not found.
	 *
	 * This handler method should map to the URL "/api/v1/news/{userId}/{newsId}" 
	 * using HTTP Delete method where "userId" should be replaced by a valid userId 
	 * without {} and "newsId" should be replaced by a valid newsId 
	 * without {}.
	 * 
	 */
	@DeleteMapping("/api/v1/news/{userId}/{newsId}")
	public ResponseEntity<String> deleteNews(@PathVariable("userId") String userId, @PathVariable("newsId") int newsId) {
		boolean result = newsservice.deleteNews(userId, newsId);
		if (result) {
			return new ResponseEntity<String>("News successfully deleted", HttpStatus.OK);
		}
		return new ResponseEntity<String>("News not found", HttpStatus.NOT_FOUND);
	}

	/*
	 * Define a handler method which will delete all the news of a specific user from 
	 * a database. This handler method should return any one of the status messages 
	 * basis on different situations: 
	 * 1. 200(OK) - If the newsId deleted successfully from database. 
	 * 2. 404(NOT FOUND) - If the note with specified newsId is not found.
	 *
	 * This handler method should map to the URL "/api/v1/news/{userId}" 
	 * using HTTP Delete method where "userId" should be replaced by a valid userId 
	 * without {} and "newsid" should be replaced by a valid newsId 
	 * without {}.
	 * 
	 */
	@DeleteMapping("/api/v1/news/{userId}")
	public ResponseEntity<String> deleteAllNews(@PathVariable("userId") String userId) {
		try {
			boolean result = newsservice.deleteAllNews(userId);
			if (result) {
				return new ResponseEntity<String>("News successfully deleted", HttpStatus.OK);
			}
			return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
		} catch (NewsNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	/*
	 * Define a handler method which will update a specific news by reading the
	 * Serialized object from request body and save the updated news details in a
	 * database. 
	 * This handler method should return any one of the status messages
	 * basis on different situations: 
	 * 1. 200(OK) - If the news updated successfully.
	 * 2. 404(NOT FOUND) - If the news with specified newsId is not found.
	 * 
	 * This handler method should map to the URL "/api/v1/news/{userId}/{newsId}" using 
	 * HTTP PUT method where "userId" should be replaced by a valid userId 
	 * without {} and "newsid" should be replaced by a valid newsId without {}.
	 * 
	 */
	@PutMapping("/api/v1/news/{userId}/{newsId}")
	public ResponseEntity<?> updateNews(@PathVariable("userId") String userId, @PathVariable("newsId") int newsId, @RequestBody News updatedNews) {
		try {
			News news = newsservice.updateNews(updatedNews, newsId, userId);
			if (news != null) {
				return new ResponseEntity<String>("News successfully updated", HttpStatus.OK);
			}
			return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
		} catch (NewsNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	/*
	 * Define a handler method which will get us the specific news by a userId.
	 * This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the news found successfully. 
	 * 2. 404(NOT FOUND) - If the news with specified newsId is not found.
	 * 
	 * This handler method should map to the URL "/api/v1/news/{userId}/{newsId}" 
	 * using HTTP GET method where "userId" should be replaced by a valid userId 
	 * without {} and "newsid" should be replaced by a valid newsId without {}.
	 * 
	 */
	@GetMapping("/api/v1/news/{userId}/{newsId}" )
	public ResponseEntity<?> getNewsById(@PathVariable("userId") String userId, @PathVariable("newsId") int newsId) {
		try {
			News result = newsservice.getNewsByNewsId(userId, newsId);
			if (result != null) {
				return new ResponseEntity<News>(result, HttpStatus.OK);
			}
			return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
		} catch (NewsNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	

	/*
	 * Define a handler method which will show details of all news created by specific 
	 * user. This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the news found successfully. 
	 * 2. 404(NOT FOUND) - If the news with specified newsId is not found.
	 * This handler method should map to the URL "/api/v1/news/{userId}" using HTTP GET method
	 * where "userId" should be replaced by a valid userId without {}.
	 * 
	 */
	@GetMapping("/api/v1/news/{userId}")
	public ResponseEntity<?> getAllNews(@PathVariable("userId") String userId) {
		List<News> result = newsservice.getAllNewsByUserId(userId);
		if (result.size() > 0) {
			return new ResponseEntity<List<News>>(result, HttpStatus.OK);
		}
		return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
	}



}
