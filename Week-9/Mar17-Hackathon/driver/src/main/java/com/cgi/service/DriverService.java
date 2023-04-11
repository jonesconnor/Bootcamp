package com.cgi.service;

import java.util.List;

import com.cgi.exception.DriverNotFoundException;
import com.cgi.exception.DuplicateDriverException;
import com.cgi.model.Driver;

public interface DriverService {
	
	public Driver addDriver(Driver driverobj) throws DuplicateDriverException;
	
	public List<Driver> getAllDrivers();
	
	public Driver updateDriver(Driver driverobj) throws DriverNotFoundException;
	
	public boolean deleteDriver(int driverid) throws DriverNotFoundException;
	
	public List<Driver> getDriverByCity(String city);
	
	public List<Driver> getDriverByQuoteamount(long quoteamount);

}
