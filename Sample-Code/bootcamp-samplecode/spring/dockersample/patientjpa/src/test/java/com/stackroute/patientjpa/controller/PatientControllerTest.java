package com.stackroute.patientjpa.controller;

import static org.mockito.ArgumentMatchers.any;

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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.patientjpa.exception.PatientAlreadyExistException;
import com.stackroute.patientjpa.exception.PatientIdDoesNotExistException;
import com.stackroute.patientjpa.model.Patient;
import com.stackroute.patientjpa.service.PatientService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc

public class PatientControllerTest {
	
	@InjectMocks
	PatientController  pcontroller;
	
	@MockBean
	PatientService pservice;
	
	
     MockMvc mockmvc;
     
     Patient patient=new Patient();
     
     @BeforeEach
     public void init()
     {
    	 MockitoAnnotations.openMocks(this);
    	 mockmvc=MockMvcBuilders.standaloneSetup(pcontroller).build();
    	 patient.setPatientId(100);
    	 patient.setPatientName("Roman");
    	 patient.setAddr("Canada");
    	 patient.setProblem("Tooth ache");
     }
     
     
     @Test
     public void whenPostObjectSuccess() throws JsonProcessingException, Exception
     {
    	 
    	 Mockito.when(pservice.addPatient(patient)).thenReturn(patient);
    	 
    	 
    	 mockmvc.perform(MockMvcRequestBuilders.post("/patient/addpatient")
    			 .contentType(MediaType.APPLICATION_JSON)
    			 .content(convertObject(patient)))
    			 .andExpect(MockMvcResultMatchers.status().isCreated());
    	 
     }
     
     
     @Test
     public void whenPostObjectThenFailed() throws JsonProcessingException, Exception
     {
    	 
    	 Mockito.when(pservice.addPatient(any())).thenThrow(PatientAlreadyExistException.class);
    	 
    	 
    	 mockmvc.perform(MockMvcRequestBuilders.post("/patient/addpatient")
    			 .contentType(MediaType.APPLICATION_JSON)
    			 .content(convertObject(patient)))
    			 .andExpect(MockMvcResultMatchers.status().isConflict());
    	 
     }
     
     
     private String convertObject(Object obj) throws JsonProcessingException
     {
    	 ObjectMapper objmapper=new ObjectMapper();
    	return objmapper.writeValueAsString(obj);
     }
     
     @Test
     
     public void deletePatientSuccess() throws Exception
     {
    	 Mockito.when(pservice.deletePatient(100)).thenReturn(true);
    	 
    mockmvc.perform(MockMvcRequestBuilders.delete("/patient/delete/100")
    		.contentType(MediaType.APPLICATION_JSON))
    	.andExpect(MockMvcResultMatchers.status().isOk())
    	.andDo(MockMvcResultHandlers.print());
    
    		
    	 
     }
     
 @Test
     
     public void deletePatientFailure() throws Exception
     {
    	 Mockito.when(pservice.deletePatient(100)).thenThrow(PatientIdDoesNotExistException.class);
    	 
    	 
    mockmvc.perform(MockMvcRequestBuilders.delete("/patient/delete/100")
    		.contentType(MediaType.APPLICATION_JSON))
    	.andExpect(MockMvcResultMatchers.status().isNotFound())
    	.andDo(MockMvcResultHandlers.print());
    
    		
    	 
     }
     
}









