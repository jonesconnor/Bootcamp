package com.cgi.medication.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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

import com.cgi.medication.exception.ViewAllFailedException;
import com.cgi.medication.model.Medication;
import com.cgi.medication.service.MedicationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc

public class MedicationControllerTest {
	
	@InjectMocks
	MedicationController cont;
	
	@MockBean
	MedicationService serve;
	
	MockMvc mock;
	
	Medication testing;
	
	@BeforeEach
	public void init() throws ViewAllFailedException {
		MockitoAnnotations.openMocks(this);
		mock=MockMvcBuilders.standaloneSetup(cont).build();
		testing = serve.getAllMedications();
	}
	
	@Test
	public void whenGetAllSuccess() throws JsonProcessingException, Exception {
		Mockito.when(serve.getAllMedications()).thenReturn(testing);
		
		mock.perform(MockMvcRequestBuilders.get("/viewall")
					.contentType(MediaType.APPLICATION_JSON)
					.content(convertObject(testing)))
					.andExpect(MockMvcResultMatchers.status().isOk());
					
	}
	
	private String convertObject(Object obj) throws JsonProcessingException{
		ObjectMapper objmap = new ObjectMapper();
		return objmap.writeValueAsString(obj);
	}
	
	@Test
	public void whenGetAllFailure() throws Exception{
		Mockito.when(serve.getAllMedications()).thenThrow(ViewAllFailedException.class);
		
		mock.perform(MockMvcRequestBuilders.get("/viewall")
		.contentType(MediaType.APPLICATION_JSON)
		.content(convertObject(testing)))
		.andExpect(MockMvcResultMatchers.status().isMethodFailure());
	}
}
