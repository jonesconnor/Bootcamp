package com.cgi.authentication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.cgi.authentication.exception.UserAlreadyExistsException;
import com.cgi.authentication.exception.UserNotFoundException;
import com.cgi.authentication.model.User;
import com.cgi.authentication.repo.UserRepo;

public class AuthServiceTest {

	@InjectMocks
	AuthServiceImpl service;
	
	@Mock
	UserRepo repo;
	
	User user;
	List<User> users = new ArrayList<User>();
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		user = new User();
		user.setMailid("john");
		user.setPassword("qwerty");
		user.setRole("patient");
		
		User u2 = new User();
		u2.setMailid("jacob");
		u2.setPassword("qwerty");
		u2.setRole("doctor");
		
		users.add(user);
		users.add(u2);
	}
	
	@Test
	public void whenAddUserStoredSuccess() throws UserAlreadyExistsException {
		Mockito.when(repo.save(user)).thenReturn(user);
		
		User uResult = service.register(user);
		assertEquals(uResult.getMailid(),"john");
		
		verify(repo,times(1)).findById(user.getMailid());
		verify(repo,times(1)).save(user);
	}
	
	@Test
	public void whenAddUserFailedToStore() {
		Mockito.when(repo.findById("john")).thenReturn(Optional.of(user));
		
		assertThrows(UserAlreadyExistsException.class,()->service.register(user));
	}
	
	@Test
	public void whenLoginUserStoredSuccess() throws UserNotFoundException {
		Mockito.when(repo.findByMailidAndPassword(user.getMailid(),user.getPassword())).thenReturn(user);
		
		User uResult = service.login(user);
		assertEquals(uResult,user);
		verify(repo,times(1)).findByMailidAndPassword(user.getMailid(),user.getPassword());
	}
	
	@Test
	public void whenLoginUserNotStoredFail() {
		Mockito.when(repo.findById(user.getMailid())).thenReturn(Optional.empty());
		
		assertThrows(UserNotFoundException.class,()->service.login(user));
	}

}
