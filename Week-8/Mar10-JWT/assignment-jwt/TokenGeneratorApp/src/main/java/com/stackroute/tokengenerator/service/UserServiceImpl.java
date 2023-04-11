package com.stackroute.tokengenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.tokengenerator.model.UserProfile;
import com.stackroute.tokengenerator.repo.UserRepo;

//use repo methods to complete the below transactions

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo repo;
	
	@Override
	public UserProfile registerUser(UserProfile user) {
		 
		return repo.save(user);
	}

	@Override
	public boolean validateUser(UserProfile user) {
		UserProfile userobj = repo.findByMailidAndPassword(user.getMailId(), user.getPassword());
		if (userobj == null) {
			return false;
		}
		return true;
	}

}
