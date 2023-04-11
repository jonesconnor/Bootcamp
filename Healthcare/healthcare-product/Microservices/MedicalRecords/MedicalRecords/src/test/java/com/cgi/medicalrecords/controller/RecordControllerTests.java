package com.cgi.medicalrecords.controller;

import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.Arrays;

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

import com.cgi.medicalrecords.exception.RecordAlreadyExistsException;
import com.cgi.medicalrecords.exception.RecordNotExistException;
import com.cgi.medicalrecords.model.MedicalRecord;
import com.cgi.medicalrecords.service.RecordService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RecordControllerTests {
	
	@InjectMocks
	RecordController controller;
	
	@MockBean
	RecordService service;
	
	MockMvc mockmvc;
	
	MedicalRecord r1 = new MedicalRecord();
	MedicalRecord r2 = new MedicalRecord();
	MedicalRecord r3 = new MedicalRecord();
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(controller).build();

		r1.setId(1);
		r1.setDoctorid("10");
		r1.setPatientid("100");
		r1.setNotes("Patient presented with fever. Prescribed Tylenol.");
		r1.setDate(LocalDate.of(2023, 3, 21));
		
		r2.setId(2);
		r2.setDoctorid("20");
		r2.setPatientid("200");
		r2.setNotes("Patient presented with broken arm. Advised to go to hospital for further care.");
		r2.setDate(LocalDate.of(2023, 3, 22));

		r3.setId(3);
		r3.setDoctorid("30");
		r3.setPatientid("100");
		r3.setNotes("Patient wanted to order pizza. Advised to go to Domino's");
		r3.setDate(LocalDate.of(2023, 3, 23));
	}
	
	@Test
	public void postRecordShouldSucceed() throws JsonProcessingException, Exception {
		Mockito.when(service.addRecord(r1)).thenReturn(r1);
		 mockmvc.perform(MockMvcRequestBuilders.post("/medicalrecords/add")
    			 .contentType(MediaType.APPLICATION_JSON)
    			 .content(convertObject(r1)))
    			 .andExpect(MockMvcResultMatchers.status().isCreated());

	}
	
	@Test
	public void postRecordShouldThrowException() throws JsonProcessingException, Exception {
		Mockito.when(service.addRecord(any())).thenThrow(RecordAlreadyExistsException.class);
		mockmvc.perform(MockMvcRequestBuilders.post("/medicalrecords/add")
   			 .contentType(MediaType.APPLICATION_JSON)
   			 .content(convertObject(r1)))
   			 .andExpect(MockMvcResultMatchers.status().isConflict());
	}
	
	@Test
	public void getRecordByIdShouldSucceed() throws Exception {
		Mockito.when(service.getRecordById(r1.getId())).thenReturn(r1);
		mockmvc.perform(MockMvcRequestBuilders.get("/medicalrecords/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getRecordByIdShouldFail() throws Exception {
		Mockito.when(service.getRecordById(0)).thenThrow(RecordNotExistException.class);
		mockmvc.perform(MockMvcRequestBuilders.get("/medicalrecords/0")
   			 .contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isConflict());
	}
	
	@Test
	public void getListOfRecordsByPatientIdShouldSucceed() throws Exception {
		Mockito.when(service.getRecordsByPatientId(r1.getPatientid())).thenReturn(Arrays.asList(r1, r3));
		mockmvc.perform(MockMvcRequestBuilders.get("/medicalrecords/patient/100")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getListOfRecordsByDoctorIdAndPatientIdShouldSucceed() throws Exception {
		Mockito.when(service.getRecordsByDoctorIdAndPatientId(r1.getDoctorid(), r1.getPatientid())).thenReturn(Arrays.asList(r1));
		mockmvc.perform(MockMvcRequestBuilders.get("/medicalrecords/doctorpatient/10/100")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getListOfRecordsByPatientIdAndDateShouldSucceed() throws Exception {
		Mockito.when(service.getRecordsByPatientidAndDate(r1.getPatientid(), r1.getDate())).thenReturn(Arrays.asList(r1));
		mockmvc.perform(MockMvcRequestBuilders.get("/medicalrecords/patientdate/100/2023-03-21")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getListOfRecordsByDoctorIdAndDateShouldSucceed() throws Exception {
		Mockito.when(service.getRecordsByDoctoridAndDate(r1.getDoctorid(), r1.getDate())).thenReturn(Arrays.asList(r1));
		mockmvc.perform(MockMvcRequestBuilders.get("/medicalrecords/doctordate/10/2023-03-21")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	private String convertObject(Object obj) throws JsonProcessingException {
		ObjectMapper objmapper = new ObjectMapper();
		objmapper.registerModule(new JavaTimeModule());
		return objmapper.writeValueAsString(obj);
		
	}

}
