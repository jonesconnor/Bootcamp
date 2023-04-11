package com.stackroute.pharmacy.service;

import java.util.List;

import com.stackroute.pharmacy.exception.MedicineNameNotFoundException;
import com.stackroute.pharmacy.exception.ShopIdDoesNotExistException;
import com.stackroute.pharmacy.model.MedicalShop;
import com.stackroute.pharmacy.model.Medicine;
 

public interface ShopService {

	
	MedicalShop		addMedicalShop(MedicalShop medobj) ;
	
	List<MedicalShop> getAllMedicalShops();
	
	boolean deleteMedicalShop(String id) ;
	
	boolean deleteMedicine(String shopid, String medicineid) throws ShopIdDoesNotExistException;
	
	List<MedicalShop> getCostlyMedicalShop(int price);
	
	List<MedicalShop> getMedicalShopByAddr(String addr);
	
	MedicalShop		addMedicine(String shopid, Medicine medicine) throws ShopIdDoesNotExistException;
	
	Medicine getMedicineByName(String shopid,String medicinename)  throws ShopIdDoesNotExistException,MedicineNameNotFoundException;
	
}
