package com.stackroute.samplefeign.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stackroute.samplefeign.model.Medicine;
import com.stackroute.samplefeign.model.Patient;

@FeignClient("patientapp")
public interface FeignPatient {
	
	@RequestMapping(method=RequestMethod.GET, value="/patient/viewall")
	public List<Patient> getPatients();

}