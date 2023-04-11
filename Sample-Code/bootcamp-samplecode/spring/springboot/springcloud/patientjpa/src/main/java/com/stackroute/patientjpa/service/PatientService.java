package com.stackroute.patientjpa.service;

import java.util.List;

import com.stackroute.patientjpa.exception.PatientAlreadyExistException;
import com.stackroute.patientjpa.exception.PatientIdDoesNotExistException;
import com.stackroute.patientjpa.model.Patient;

public interface PatientService {

	public Patient addPatient(Patient patobj) throws PatientAlreadyExistException;
	public List<Patient> viewAllPatients();
	
	boolean deletePatient(int patid) throws PatientIdDoesNotExistException;
	
	Patient	updatePatient(Patient patientupd) throws PatientIdDoesNotExistException;
		
	
	public List<Patient> getPatientByName(String name);
	
	
	public List<Patient> getPatientIdLesstha(int id);
	
	
}
