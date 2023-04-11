package com.cgi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cgi.exception.DriverNotFoundException;
import com.cgi.exception.DuplicateDriverException;
import com.cgi.model.Driver;
import com.cgi.repository.DriverRepo;

@ExtendWith(MockitoExtension.class)
public class DriverServiceImplTest {
	
	@InjectMocks
	private DriverServiceImpl driverService;

	@Mock
	private DriverRepo driverRepo;

	@Test
	public void addDriverTest() throws DuplicateDriverException {
		Driver driver = new Driver(1, "John", 1234567890, "123 Main St", "Toronto", "V6", 5000);
		when(driverRepo.findById(1)).thenReturn(Optional.empty());
		when(driverRepo.save(driver)).thenReturn(driver);
		Driver result = driverService.addDriver(driver);
		assertEquals(driver, result);
	}

	@Test
	public void addDuplicateDriverTestThrowsDuplicateDriverException() {
		Driver driver = new Driver(1, "John", 1234567890, "123 Main St", "Toronto", "V6", 5000);
		when(driverRepo.findById(1)).thenReturn(Optional.of(driver));
		DuplicateDriverException thrown = assertThrows(DuplicateDriverException.class, () -> driverService.addDriver(driver));
		assertEquals("Driver already exists", thrown.getMessage());
	}

	@Test
	public void getAllDriversTest() {
		List<Driver> driverList = new ArrayList<>();
		driverList.add(new Driver(1, "John", 1234567890, "123 Main St", "Toronto", "V6", 5000));
		driverList.add(new Driver(2, "Jane", 1345678901, "456 Elm St", "Vancouver", "V8", 6000));
		when(driverRepo.findAll()).thenReturn(driverList);
		List<Driver> result = driverService.getAllDrivers();
		assertEquals(driverList, result);
	}

	@Test
	public void updateNonExistingDriverTestThrowsDriverNotFoundException(){
		Driver driver = new Driver(1, "John", 1234567890, "123 Main St", "Toronto", "V6", 5000);
		when(driverRepo.findById(1)).thenReturn(Optional.empty());
		DriverNotFoundException thrown = assertThrows(DriverNotFoundException.class, () -> driverService.updateDriver(driver));
		assertEquals("Driver not found", thrown.getMessage());
	}

	@Test
	public void deleteDriverTest() throws DriverNotFoundException {
		when(driverRepo.findById(1)).thenReturn(Optional.of(new Driver(1, "John", 1234567890, "123 Main St", "Toronto", "V6", 5000)));
		doNothing().when(driverRepo).deleteById(1);
		boolean result = driverService.deleteDriver(1);
		assertTrue(result);
	}

	@Test
	public void deleteNonExistingDriverTestThrowsDriverNotFoundException(){
		when(driverRepo.findById(1)).thenReturn(Optional.empty());
		DriverNotFoundException thrown = assertThrows(DriverNotFoundException.class, () -> driverService.deleteDriver(1));
		assertEquals("Driver not found", thrown.getMessage());
	}

	@Test
	public void getDriverByCityTest() {
		List<Driver> driverList = new ArrayList<>();
		driverList.add(new Driver(1, "John", 1234567890, "123 Main St", "Toronto", "V6", 5000));
		driverList.add(new Driver(2, "Jane", 1345678901, "456 Elm St", "Toronto", "V8", 6000));
		when(driverRepo.findByCity("Toronto")).thenReturn(driverList);
		List<Driver> result = driverService.getDriverByCity("Toronto");
		assertEquals(driverList, result);
	}

}
