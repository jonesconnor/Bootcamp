package com.cgi.authentication.service;

import com.cgi.authentication.exception.UserAlreadyExistsException;
import com.cgi.authentication.exception.UserNotFoundException;
import com.cgi.authentication.model.User;

public interface AuthService {
	
	public User register(User user) throws UserAlreadyExistsException;
	public User login(User user) throws UserNotFoundException;
	public User changePassword(String mailid,String oldpwd,String newpwd) throws UserNotFoundException;
	public boolean deleteById(String mailid) throws UserNotFoundException;

}
