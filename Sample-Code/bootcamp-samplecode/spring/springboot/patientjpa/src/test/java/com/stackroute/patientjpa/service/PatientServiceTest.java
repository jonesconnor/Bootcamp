package com.stackroute.patientjpa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import com.stackroute.patientjpa.exception.PatientAlreadyExistException;
import com.stackroute.patientjpa.exception.PatientIdDoesNotExistException;
import com.stackroute.patientjpa.model.Patient;
import com.stackroute.patientjpa.repository.PatientRepo;

public class PatientServiceTest {

	
	@InjectMocks
	PatientServiceImpl pservice;
	
	@Mock
	PatientRepo prepo;
	
	
	Patient patient;
	
	List<Patient> patients=new ArrayList<Patient>();
	
	
	@BeforeEach
	public void setup()
	{
		MockitoAnnotations.openMocks(this);
		patient=new Patient();
		patient.setPatientId(10);
		patient.setPatientName("Ruban");
		patient.setAddr("Singapore");
		patient.setProblem("Headache");
		
		
		Patient patient2=new Patient();
		patient2.setPatientId(20);
		patient2.setPatientName("Ratan");
		patient2.setAddr("Thai");
		patient2.setProblem("Red Eye");
		
		patients.add(patient2);
		patients.add(patient);
		
		
	}
	
	
	@Test
	public void whenAddpatientStoredsuccess() throws PatientAlreadyExistException
	{
		//stubbing
		Mockito.when(prepo.save(patient)).thenReturn(patient);
		
		Patient patresult=pservice.addPatient(patient);
		
		assertEquals(patresult.getPatientName(),"Ruban");
		
		verify(prepo,times(1)).findById(patient.getPatientId());
		verify(prepo,times(1)).save(patient);
		
	}
	
	@Test
	public void whenAddpatientFailedToStore()
	{
		
		Mockito.when(prepo.findById(10)).thenReturn(Optional.of(patient));
		
		assertThrows(PatientAlreadyExistException.class,()->pservice.addPatient(patient));
		
		
		
		
	}
	
	
	@Test
	public void whenGetListViewAllObject()
	{
		Mockito.when(prepo.findAll()).thenReturn(patients);
		
	List<Patient> patientlist=			pservice.viewAllPatients();
	
	
	assertEquals(patientlist,patients);
	
	
	}
	
	@Test
	public void whenDeleteThenSuccess() throws PatientIdDoesNotExistException
	{
		Mockito.when(prepo.findById(patient.getPatientId())).thenReturn(Optional.of(patient));
		
	boolean result=		pservice.deletePatient(patient.getPatientId());
		
	assertTrue(result);
	
	verify(prepo,times(1)).deleteById(patient.getPatientId());
	
	
	}
	
	@Test
	
	public void whenDeleteThenFailed()
	{
		 Mockito.when(prepo.findById(patient.getPatientId())).thenReturn(Optional.empty());
		 
		 assertThrows(PatientIdDoesNotExistException.class,()-> pservice.deletePatient(patient.getPatientId()));
		 
			verify(prepo,times(0)).deleteById(patient.getPatientId());
	}
	
}



