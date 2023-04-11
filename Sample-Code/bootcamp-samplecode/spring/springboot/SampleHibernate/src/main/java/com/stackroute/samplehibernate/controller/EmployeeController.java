package com.stackroute.samplehibernate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.samplehibernate.model.Employee;
import com.stackroute.samplehibernate.repo.EmployeeRepo;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeRepo emprepo;
	
	
	@PostMapping("/employee/add")
	public ResponseEntity addEmp(@RequestBody Employee emp)
	{
		String result=emprepo.addEmployee(emp);
		
	return new ResponseEntity(result,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/employee/view")
	public ResponseEntity<List> viewEmp()
	{
		List<Employee> employees=emprepo.getEmployees();
		
	return new ResponseEntity(employees,HttpStatus.OK);
	}

}
