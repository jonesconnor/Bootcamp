package com.stackroute.patientjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.patientjpa.model.Hospital;
import com.stackroute.patientjpa.repository.HospitalRepo;

@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	HospitalRepo repo;
	
	@Override
	public Hospital addHospital(Hospital hobj) {
		 
		return repo.save(hobj);
	}

	@Override
	public List<Hospital> viewHospital() {
		 
		return repo.findAll();
	}

}
