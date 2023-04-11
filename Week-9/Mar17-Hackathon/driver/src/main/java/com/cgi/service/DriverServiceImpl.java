package com.cgi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.exception.DriverNotFoundException;
import com.cgi.exception.DuplicateDriverException;
import com.cgi.model.Driver;
import com.cgi.repository.DriverRepo;

@Service
public class DriverServiceImpl implements DriverService{
	
	@Autowired
	DriverRepo driverrepo;
	
	@Override
	public Driver addDriver(Driver driverobj) throws DuplicateDriverException {
		Driver savedobj = null;
		Optional<Driver> optdriverexists = driverrepo.findById(driverobj.getDriverid());
		
		if (optdriverexists.isPresent()) {
			throw new DuplicateDriverException("Driver already exists");
		} else {
			savedobj = driverrepo.save(driverobj);
		}
		
		return savedobj;
	}
	
	@Override
	public List<Driver> getAllDrivers() {
		return driverrepo.findAll();
	}
	
	@Override
	public Driver updateDriver(Driver driverobj) throws DriverNotFoundException{
		Optional<Driver> optdriver = driverrepo.findById(driverobj.getDriverid());
		
		if (optdriver.isPresent()) {
			return driverrepo.save(driverobj);
		} else {
			throw new DriverNotFoundException("Driver not found");
		}
	}
	
	@Override
	public boolean deleteDriver(int driverId) throws DriverNotFoundException {
		Optional<Driver> optdriver = driverrepo.findById(driverId);
		
		if (optdriver.isPresent()) {
			driverrepo.deleteById(driverId);
			return true;
		} else {
			throw new DriverNotFoundException("Driver not found");
		}
	}
	
	@Override
	public List<Driver> getDriverByCity(String city) {
		return driverrepo.findByCity(city);
	}
	
	@Override
	public List<Driver> getDriverByQuoteamount(long quoteamount) {
		return driverrepo.findByQuoteamountGreaterThan(quoteamount);
	}

}
