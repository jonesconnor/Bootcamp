package com.stackroute.feign.model;

import java.util.List;

public class UserNews {
	private String userId;
	private List<News> newslist;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<News> getNewslist() {
		return newslist;
	}

	public void setNewslist(List<News> newslist) {
		this.newslist = newslist;
	}


}
