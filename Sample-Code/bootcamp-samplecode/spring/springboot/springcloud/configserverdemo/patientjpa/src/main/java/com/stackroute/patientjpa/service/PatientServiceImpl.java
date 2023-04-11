package com.stackroute.patientjpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.patientjpa.exception.PatientAlreadyExistException;
import com.stackroute.patientjpa.exception.PatientIdDoesNotExistException;
import com.stackroute.patientjpa.model.Patient;
import com.stackroute.patientjpa.repository.PatientRepo;

@Service
public class PatientServiceImpl implements PatientService{

	 @Autowired
	 PatientRepo patientrepo;
	 
	
	
	@Override
	public Patient addPatient(Patient patobj) throws PatientAlreadyExistException {
	
		Patient savedobj=null;
		
		Optional<Patient> optpatientexist=	 patientrepo.findById(patobj.getPatientId());
		
	if(optpatientexist.isPresent())
	throw new PatientAlreadyExistException("Duplicate patientid");
	else
	savedobj=patientrepo.save(patobj);
	
	return savedobj;
	
	}
	
	
	

	@Override
	public List<Patient> viewAllPatients() {
	 
		
		return patientrepo.findAll();
	}

	@Override
	public List<Patient> getPatientByName(String name) {
	return	   patientrepo.findByPatientName(name);
	}




	@Override
	public boolean deletePatient(int patid) throws PatientIdDoesNotExistException {
		 
		Optional<Patient> optpatient=patientrepo.findById(patid);
		
		if(optpatient.isPresent())
		{
			patientrepo.deleteById(patid);
			return true;
		}
		else
			throw new PatientIdDoesNotExistException("patientId is not available");
		
		
		 
	}




	@Override
	public Patient updatePatient(Patient patientupd) throws PatientIdDoesNotExistException {
		
		
		Optional<Patient> optpatient=patientrepo.findById(patientupd.getPatientId() );
		
		if(optpatient.isPresent())
		{
		return	patientrepo.save(patientupd);
			 
		}
		else
			throw new PatientIdDoesNotExistException("patientId is not available");
		
		
		 
	}




	@Override
	public List<Patient> getPatientIdLesstha(int id) {
		// TODO Auto-generated method stub
		return patientrepo.findByPatientIdLessThan(id);
	}

}
