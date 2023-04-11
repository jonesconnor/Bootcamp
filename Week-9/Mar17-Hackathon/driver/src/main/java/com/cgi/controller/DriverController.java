package com.cgi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.exception.DriverNotFoundException;
import com.cgi.exception.DuplicateDriverException;
import com.cgi.model.Driver;
import com.cgi.service.DriverService;

@RestController
@RequestMapping("driver")
public class DriverController {
	
	@Autowired
	DriverService dservice;
	
	@PostMapping("/adddriver")
	public ResponseEntity<?> adddriver(@RequestBody Driver newdriver) {
		try {
			Driver driveradded = dservice.addDriver(newdriver);
			return new ResponseEntity<Driver>(driveradded, HttpStatus.CREATED);
		} catch(DuplicateDriverException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/viewall")
	public ResponseEntity<?> viewdrivers() {
		List<Driver> drivers = dservice.getAllDrivers();
		return new ResponseEntity<List<Driver>>(drivers, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{driverid}")
	public ResponseEntity<?> deletedriver(@PathVariable("driverid") String driverid) {
		try {
			boolean result = dservice.deleteDriver(Integer.parseInt(driverid));
			return new ResponseEntity<String>("Driver deleted successfully", HttpStatus.OK);
		} catch (DriverNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateDriver(@RequestBody Driver drivertoupdate) {
		try {
			Driver resultobj = dservice.updateDriver(drivertoupdate);
			return new ResponseEntity<Driver>(resultobj, HttpStatus.OK);
		} catch (DriverNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/viewbycity/{city}")
	public ResponseEntity<?> viewByCity(@PathVariable("city") String city) {
		List<Driver> drivers = dservice.getDriverByCity(city);
		return new ResponseEntity<List<Driver>>(drivers, HttpStatus.OK);
	}
	
	@GetMapping("/viewbyquote/{quoteamount}")
	public ResponseEntity<?> viewByQuoteAmount(@PathVariable("quoteamount") String quoteamount) {
		List<Driver> drivers = dservice.getDriverByQuoteamount(Long.parseLong(quoteamount));
		return new ResponseEntity<List<Driver>>(drivers, HttpStatus.OK);
	}
	
}
