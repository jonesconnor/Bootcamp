package com.stackroute.patientjpa.service;

import java.util.List;

import com.stackroute.patientjpa.model.Hospital;

public interface HospitalService {

	Hospital addHospital(Hospital hobj);
	List<Hospital> viewHospital();
}
