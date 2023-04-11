package com.stackroute.pharmacy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.pharmacy.exception.MedicineIdAlreadyExistException;
import com.stackroute.pharmacy.exception.MedicineIdNotFoundException;
import com.stackroute.pharmacy.model.Medicine;
import com.stackroute.pharmacy.service.MedicineService;

@RestController
@RequestMapping("pharma")
public class MedicineController {
	
	
	@Autowired
	MedicineService mservice;
	
	
	@PostMapping("addMedicine")
	public ResponseEntity addmedi(@RequestBody Medicine mobj)
	{
		
	Medicine mstored;
	try {
		mstored = mservice.addMedicine(mobj);
		
	return new ResponseEntity<Medicine>(mstored,HttpStatus.CREATED);
	} 
	
	catch (MedicineIdAlreadyExistException e) {
	 return new ResponseEntity<String>("duplicate id" , HttpStatus.CONFLICT);
	}

	}
	
	
	@GetMapping("viewgreaterprice/{maxprice}")
	public ResponseEntity viewprice(@PathVariable("maxprice") String pri)
	{
		List<Medicine> medicines=mservice.getCostlyMedicine(Integer.parseInt(pri));
		return new ResponseEntity<List>(medicines,HttpStatus.OK);
	}
	@GetMapping("viewAllDetails")
	public ResponseEntity viewalldata()
	{
		
		List<Medicine> medicines=mservice.getAllMedicines();
		return new ResponseEntity<List>(medicines,HttpStatus.OK);

	}
	
	@GetMapping("viewbyname/{name}")
	public ResponseEntity viewbyname(@PathVariable("name") String name)
	{
	 Medicine medicine=mservice.getMedicineByName(name);
		return new ResponseEntity<Medicine>(medicine,HttpStatus.OK);
	}
	
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity deletemed(@PathVariable("id") String medid)
	{
	try {
		boolean result=mservice.deleteMedicine(medid);
		
		return new ResponseEntity<String>("Deleted",HttpStatus.OK);
	} catch (MedicineIdNotFoundException e) 
	{
		 return new ResponseEntity<String>("ID not found" , HttpStatus.NOT_FOUND);
	}
		
	}

}
