package com.stackroute.newz.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newz.model.News;
import com.stackroute.newz.model.UserNews;
import com.stackroute.newz.repository.NewsRepository;
import com.stackroute.newz.util.exception.NewsNotFoundException;


@Service
public class NewsServiceImpl implements NewsService {

	/*
	 * Autowiring should be implemented for the NewsDao and MongoOperation.
	 * (Use Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */
	@Autowired
	NewsRepository newsrepo;
	

	/*
	 * This method should be used to save a new news.
	 */
	@Override
	public boolean addNews(String userid,News news) {
		Optional<UserNews> optUserNews = newsrepo.findById(userid);
		if (optUserNews.isPresent()) {
			UserNews userNews = optUserNews.get();
			List<News> newsList = userNews.getNewslist();
			newsList.add(news);
			userNews.setNewslist(newsList);
			newsrepo.save(userNews);
			return true;	
		}
		return false;
	}

	/* This method should be used to delete an existing news. */
	
	public boolean deleteNews(String userId, int newsId) {
		Optional<UserNews> optUserNews = newsrepo.findById(userId);
		if (optUserNews.isPresent()) {
			UserNews userNews = optUserNews.get();
			List<News> newsList = userNews.getNewslist();
			newsList.removeIf(news -> news.getNewsId() == newsId);
			newsrepo.save(userNews);
			return true;
		}
		return false;
	}

	/* This method should be used to delete all news for a  specific userId. */
	
	public boolean deleteAllNews(String userId) throws NewsNotFoundException {
		Optional<UserNews> optUserNews = newsrepo.findById(userId);
		if (optUserNews.isPresent()) {
			UserNews userNews = optUserNews.get();
			List<News> newsList = userNews.getNewslist();
			if (newsList.isEmpty() || newsList == null) {
				throw new NewsNotFoundException("No news to delete");
			}
			newsList.clear();
			newsrepo.save(userNews);
			return true;
		}
		return false;
	}

	/*
	 * This method should be used to update a existing news.
	 */

	public News updateNews(News news, int newsId, String userId) throws NewsNotFoundException {
		Optional<UserNews> optUserNews = newsrepo.findById(userId);
		if (optUserNews.isPresent()) {
			UserNews userNews = optUserNews.get();
			List<News> newsList = userNews.getNewslist();
			News newsToReplace = getNewsByNewsId(userId, newsId);
			Collections.replaceAll(newsList, newsToReplace, news);
			newsrepo.save(userNews);
			return newsToReplace;
		}
		return null;
	}

	/*
	 * This method should be used to get a news by newsId created by specific user
	 */

	public News getNewsByNewsId(String userId, int newsId) throws NewsNotFoundException {
		Optional<UserNews> optUserNews = newsrepo.findById(userId);
		if (optUserNews.isPresent()) {
			UserNews userNews = optUserNews.get();
			List<News> newsList = userNews.getNewslist();
			Optional<News> optNews = newsList.stream().filter(newsobj -> newsobj.getNewsId() == newsId).findFirst();
			if (optNews.isPresent()) {
				return optNews.get();
			} else {
				throw new NewsNotFoundException("News not found");
			}
		}
		return null;
	}

	/*
	 * This method should be used to get all news for a specific userId.
	 */

	public List<News> getAllNewsByUserId(String userId) {
		Optional<UserNews> optUserNews = newsrepo.findById(userId);
		if (optUserNews.isPresent()) {
			UserNews userNews = optUserNews.get();
			return userNews.getNewslist();
		}
		return null;
	}

	@Override
	public UserNews addUser(UserNews user) {
		// TODO Auto-generated method stub
		Optional<UserNews> optUserNews = newsrepo.findById(user.getUserId());
		if (optUserNews.isEmpty()) {
			return newsrepo.save(user);
		}
		return optUserNews.get();
	}



}
