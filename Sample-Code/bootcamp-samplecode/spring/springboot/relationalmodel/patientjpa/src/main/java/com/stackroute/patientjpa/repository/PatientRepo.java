package com.stackroute.patientjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.patientjpa.model.Patient;


@Repository
public interface PatientRepo extends JpaRepository<Patient,Integer>{

	
	 List<Patient> findByPatientName(String name);
	 
  List<Patient>	findByPatientIdLessThan(int patid);
}
