package com.stackroute.tokengenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.tokengenerator.model.UserProfile;
import com.stackroute.tokengenerator.repo.UserRepo;


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
UserProfile userobj=	 repo.findByMailidAndPassword(user.getMailid(), user.getPassword());

 if (userobj==null)
		return false;
 else
	 return true;
	}

	@Override
	public UserProfile getUserbyName(String name) {
		 
		return repo.getByName(name);
	}

	@Override
	public boolean deleteByName(String name) {
		int result=repo.deleteUsingName(name);
		
		if (result==0)
		return false;
		else
			return true;
	}

}
