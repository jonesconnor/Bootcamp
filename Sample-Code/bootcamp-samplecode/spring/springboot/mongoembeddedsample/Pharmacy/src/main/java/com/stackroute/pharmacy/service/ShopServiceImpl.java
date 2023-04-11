package com.stackroute.pharmacy.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.pharmacy.exception.MedicineNameNotFoundException;
import com.stackroute.pharmacy.exception.ShopIdDoesNotExistException;
import com.stackroute.pharmacy.model.MedicalShop;
import com.stackroute.pharmacy.model.Medicine;
import com.stackroute.pharmacy.repository.MedicineRepo;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	MedicineRepo mrepo;
	
	@Override
	public MedicalShop addMedicalShop(MedicalShop medobj) {
		
		Optional<MedicalShop> optshop=mrepo.findById(medobj.getShopid());
		
		if (optshop.isEmpty())
		{
		return	mrepo.save(medobj);
		}
		
		else
		{
			MedicalShop mshop=optshop.get();
			
			List<Medicine> existmedicine=mshop.getMedicines();
			
			List<Medicine> newmedicine=medobj.getMedicines();
			
			existmedicine.addAll(newmedicine);
			
			mshop.setMedicines(existmedicine);
			
			return mrepo.save(mshop);
			
		}
			
			
		
		
	}

	@Override
	public List<MedicalShop> getAllMedicalShops() {
		 
		return mrepo.findAll();
	}

	@Override
	public boolean deleteMedicalShop(String id) {
		// TODO Auto-generated method stub
		mrepo.deleteById(id);
		return true;
	}

	@Override
	public List<MedicalShop> getCostlyMedicalShop(int price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MedicalShop> getMedicalShopByAddr(String addr) {
	return mrepo.findByAddr(addr);
	 
	}

	@Override
	public MedicalShop addMedicine(String shopid, Medicine medicine) throws ShopIdDoesNotExistException {
		Optional<MedicalShop> optshop=mrepo.findById(shopid);
		if (optshop.isEmpty())
		{
		 throw new ShopIdDoesNotExistException();
		}
		else
		{
				MedicalShop mshop=optshop.get();
				List<Medicine> existmedicine=mshop.getMedicines();
				existmedicine.add(medicine);
				mshop.setMedicines(existmedicine);
				return mrepo.save(mshop);
		}
		
		
	}

	@Override
	public boolean deleteMedicine(String shopid, String medicineid) throws ShopIdDoesNotExistException {
		 
		Optional<MedicalShop> optshop=mrepo.findById(shopid);
		if (optshop.isEmpty())
		{
		 throw new ShopIdDoesNotExistException();
		}
		else
		{
			MedicalShop existshop=optshop.get();
			List<Medicine> existmedicinelist = existshop.getMedicines();
			
			existmedicinelist.removeIf( (med)-> med.getMedid().equals(medicineid));
			
			existshop.setMedicines(existmedicinelist);
			mrepo.save(existshop);
			
			
			return true;
		}
	 
	}

	@Override
	public Medicine getMedicineByName(String shopid, String medicinename) throws ShopIdDoesNotExistException, MedicineNameNotFoundException {
		 
		 
		Optional<MedicalShop> optshop=mrepo.findById(shopid);
		if (optshop.isEmpty())
		{
		 throw new ShopIdDoesNotExistException();
		}
		else
		{
			MedicalShop existshop=optshop.get();
			List<Medicine> existmedicinelist = existshop.getMedicines();
		
		Optional<Medicine> medresult=existmedicinelist.stream().filter( med-> med.getMedname().equals(medicinename)).findFirst();
	  		
		if(medresult.isEmpty())
			throw new MedicineNameNotFoundException();
		else
		 return medresult.get();
	}
	}

}
