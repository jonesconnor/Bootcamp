package com.stackroute.rabbitproduce.service;

import com.stackroute.rabbitproduce.model.Patient;

public interface PatientService {

	Patient addPatient(Patient patient);
	public void send(Patient patient);
	
}
