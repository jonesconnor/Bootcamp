package com.cgi.medication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.cgi.medication.exception.ViewAllFailedException;
import com.cgi.medication.model.Medication;

import com.cgi.medication.repository.MedicationRepo;

public class MedicationServiceTest {
	
	@InjectMocks
	MedicationService serve;
	
	@Mock
	MedicationRepo repo;
	
	
	List<Medication> trying = new ArrayList<Medication>();
	Medication testingmed;

	
	@BeforeEach
	public void setup() throws ViewAllFailedException {
		MockitoAnnotations.openMocks(this);
		
		
		testingmed = serve.getAllMedications();
		trying.add(testingmed);
		repo.save(testingmed);
		
	}
	
	@Test
	public void whenGetAllMedication() throws ViewAllFailedException {
		
		Mockito.when(repo.findAll()).thenReturn(trying);
		Medication medpart = trying.get(0);
		
		Medication meds = serve.getAllMedications();
		
		assertEquals(meds.getMedid(), medpart.getMedid());
	}
	
	

}
