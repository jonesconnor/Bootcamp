package com.cgi.authentication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.authentication.exception.UserAlreadyExistsException;
import com.cgi.authentication.exception.UserNotFoundException;
import com.cgi.authentication.model.User;
import com.cgi.authentication.repo.UserRepo;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	UserRepo repo;

	@Override
	public User register(User user) throws UserAlreadyExistsException {
		if(repo.findById(user.getMailid()).isEmpty())
			return repo.save(user);
		else
			throw new UserAlreadyExistsException();
	}

	@Override
	public User login(User user) throws UserNotFoundException {
		User userobj = repo.findByMailidAndPassword(user.getMailid(), user.getPassword());
		if(userobj!=null) {
			return userobj;
		}
		throw new UserNotFoundException();
	}

	@Override
	public User changePassword(String mailid,String oldpwd,String newpwd) throws UserNotFoundException {
		User uptuser = new User();
		User userobj = repo.findByMailidAndPassword(mailid, oldpwd);
		if(userobj!=null) {
			uptuser.setMailid(mailid);
			uptuser.setPassword(newpwd);
			uptuser.setRole(userobj.getRole());
			uptuser.setCreateTime(userobj.getCreateTime());
			return repo.save(uptuser);
		}
		else {
			throw new UserNotFoundException();
		}
	}

	@Override
	public boolean deleteById(String mailid) throws UserNotFoundException {
		Optional<User> userobj = repo.findById(mailid);
		if(userobj.isPresent()) {
			repo.deleteById(mailid);
			return true;
		}
		else {
			throw new UserNotFoundException();
		}
	}
	

}
