package com.cgi.authentication.controller;

import static org.mockito.ArgumentMatchers.any;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cgi.authentication.exception.UserAlreadyExistsException;
import com.cgi.authentication.exception.UserNotFoundException;
import com.cgi.authentication.model.User;
import com.cgi.authentication.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
	
	@InjectMocks
	AuthController controller;
	
	@Mock
	AuthService service;
	
	MockMvc mockmvc;
	
	User user = new User();
	User u2 = new User();
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		mockmvc=MockMvcBuilders.standaloneSetup(controller).build();
		user.setMailid("john");
		user.setPassword("qwerty");
		user.setRole("patient");
		
		u2.setMailid("jacob");
		u2.setPassword("qwerty");
		u2.setRole("doctor");
	}
	
	@Test
	public void WhenRegisterObjectSuccess() throws JsonProcessingException, Exception {
		Mockito.when(service.register(user)).thenReturn(user);
		
		mockmvc.perform(MockMvcRequestBuilders.post("/auth/register")
						.contentType(MediaType.APPLICATION_JSON)
						.content(convertObject(user)))
						.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void whenRegisterObjectThenFail() throws JsonProcessingException, Exception {
		Mockito.when(service.register(any())).thenThrow(UserAlreadyExistsException.class);
		
		mockmvc.perform(MockMvcRequestBuilders.post("/auth/register")
   			 .contentType(MediaType.APPLICATION_JSON)
   			 .content(convertObject(user)))
   			 .andExpect(MockMvcResultMatchers.status().isConflict());
	}
	
	@Test
	public void whenLoginObjectThenSuccess() throws JsonProcessingException, Exception {
		Mockito.when(service.login(any())).thenReturn(user);
		System.out.println(user);
		mockmvc.perform(MockMvcRequestBuilders.post("/auth/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(convertObject(user)))
				.andExpect(MockMvcResultMatchers.status().isAccepted());
		
	}
	@Test
	public void whenLoginObjectThenFail() throws JsonProcessingException, Exception {
		Mockito.when(service.login(any())).thenThrow(UserNotFoundException.class);
		
		mockmvc.perform(MockMvcRequestBuilders.post("/auth/login")
	   			 .contentType(MediaType.APPLICATION_JSON)
	   			 .content(convertObject(u2)))
	   			 .andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	private String convertObject(Object obj) throws JsonProcessingException {
		ObjectMapper objmapper = new ObjectMapper();
		objmapper.registerModule(new JavaTimeModule());
		return objmapper.writeValueAsString(obj);
		
	}

}
