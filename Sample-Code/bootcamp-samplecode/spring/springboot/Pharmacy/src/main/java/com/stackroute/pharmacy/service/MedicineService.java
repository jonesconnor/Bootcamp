package com.stackroute.pharmacy.service;

import java.util.List;

import com.stackroute.pharmacy.exception.MedicineIdAlreadyExistException;
import com.stackroute.pharmacy.exception.MedicineIdNotFoundException;
import com.stackroute.pharmacy.model.Medicine;

public interface MedicineService {

	Medicine		addMedicine(Medicine medobj) throws MedicineIdAlreadyExistException;
	
	List<Medicine> getAllMedicines();
	
	boolean deleteMedicine(String id) throws MedicineIdNotFoundException;
	
	List<Medicine> getCostlyMedicine(int price);
	
	Medicine getMedicineByName(String name);
	
	
}
