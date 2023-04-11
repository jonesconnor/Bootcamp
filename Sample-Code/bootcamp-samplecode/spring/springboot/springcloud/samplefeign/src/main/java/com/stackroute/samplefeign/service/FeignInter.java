package com.stackroute.samplefeign.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stackroute.samplefeign.model.Medicine;

@FeignClient("pharmacyapp")
public interface FeignInter {
	
	@RequestMapping(method=RequestMethod.GET, value="/pharma/viewAllDetails")
	public List<Medicine> getMedicines();

}
