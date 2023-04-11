package com.stackroute.pharmacy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.pharmacy.exception.MedicineIdAlreadyExistException;
import com.stackroute.pharmacy.exception.MedicineIdNotFoundException;
import com.stackroute.pharmacy.model.Medicine;
import com.stackroute.pharmacy.repository.MedicineRepo;


@Service
public class MedicineServiceImpl implements MedicineService {

	
	@Autowired
	MedicineRepo mrepo;
	
	@Override
	public Medicine addMedicine(Medicine medobj) throws MedicineIdAlreadyExistException
	{
		
		Optional<Medicine> optmedi=mrepo.findById(medobj.getMedid());
		
		if (optmedi.isEmpty())
		{
	 
	Medicine medicine=	mrepo.save(medobj);
		
		
		
		return medicine;
		}
		else
			throw new MedicineIdAlreadyExistException();
		
	}

	@Override
	public List<Medicine> getAllMedicines() {
		 
		return mrepo.findAll();
	}

	@Override
	public boolean deleteMedicine(String id) throws MedicineIdNotFoundException {
		Optional<Medicine> optmedi=mrepo.findById(id);
		
		if(optmedi.isPresent())
			mrepo.deleteById(id);
		else
			throw new MedicineIdNotFoundException();
		return true;
	}

	@Override
	public List<Medicine> getCostlyMedicine(int price) {
		 
		return mrepo.findByPriceGreaterThan(price);
	}

	@Override
	public Medicine getMedicineByName(String name) {
		 
		return mrepo.findByMedname(name);
	}

}
