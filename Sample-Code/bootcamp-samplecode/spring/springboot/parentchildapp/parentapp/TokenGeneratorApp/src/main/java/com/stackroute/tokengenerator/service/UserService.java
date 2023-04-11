package com.stackroute.tokengenerator.service;

import com.stackroute.tokengenerator.model.UserProfile;

public interface UserService {

	UserProfile registerUser(UserProfile user);
	boolean validateUser(UserProfile user);
	
	UserProfile getUserbyName(String name);
	
	boolean deleteByName(String name);
	
}
