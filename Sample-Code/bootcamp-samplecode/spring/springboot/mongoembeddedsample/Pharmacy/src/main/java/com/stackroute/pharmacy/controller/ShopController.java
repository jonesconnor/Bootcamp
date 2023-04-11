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

import com.stackroute.pharmacy.exception.MedicineNameNotFoundException;
import com.stackroute.pharmacy.exception.ShopIdDoesNotExistException;
import com.stackroute.pharmacy.model.MedicalShop;
import com.stackroute.pharmacy.model.Medicine;
import com.stackroute.pharmacy.service.ShopService;

@RestController
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	ShopService shopservice;
	
	
	@PostMapping("/addshop")
	public ResponseEntity<?> addshop(@RequestBody MedicalShop mednew)
	{
		MedicalShop shopresult=shopservice.addMedicalShop(mednew);
		return new ResponseEntity<MedicalShop>(shopresult,HttpStatus.CREATED);
	}
	
	@PostMapping("/addmedicine/{shopid}")
	public ResponseEntity<?> addmedicine(@PathVariable("shopid") String shopid,@RequestBody Medicine medicine)
	{
		
 try {
	MedicalShop medshop=		shopservice.addMedicine(shopid, medicine);
	return new ResponseEntity<MedicalShop>(medshop,HttpStatus.CREATED);
 	}
 catch (ShopIdDoesNotExistException e) {
	return new ResponseEntity<String>("invalid shop id",HttpStatus.NOT_FOUND);
}
		
	}
	
	
	@DeleteMapping("/delete/medicine/{shopid}/{medid}")
	public ResponseEntity<?> deletemedicine(@PathVariable ("shopid") String sid, @PathVariable("medid") String medid)
	{
	try {
		boolean result=	shopservice.deleteMedicine(sid, medid);
		return new ResponseEntity<String>("Medicine deleted successfully from shop" , HttpStatus.OK);
	}  
	catch (ShopIdDoesNotExistException e) {
		return new ResponseEntity<String>("invalid shop id",HttpStatus.NOT_FOUND);
	}
	}
	
	@DeleteMapping("/delete/medicalshop/{shopid}")
	
	public ResponseEntity<?> deleteshop(@PathVariable ("shopid") String sid)
	{
	 
		boolean result=	shopservice.deleteMedicalShop(sid);
		return new ResponseEntity<String>("Medicine deleted successfully from shop" , HttpStatus.OK);
 
	}
	
	
	
	@GetMapping("/viewallshops")
	public ResponseEntity<?> getallshops()
	{
		List<MedicalShop> shops=shopservice.getAllMedicalShops();
		return new ResponseEntity<List> (shops,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/view/medicine/byname/{shopid}/{medicinename}")
	
	public ResponseEntity<?> getmedicinebyname(@PathVariable("shopid") String sid,@PathVariable("medicinename") String mname)
	{
	try {
		Medicine medicine=		shopservice.getMedicineByName(sid, mname);
		return new ResponseEntity<Medicine> (medicine,HttpStatus.OK);
	}
	catch (ShopIdDoesNotExistException e) {
		return new ResponseEntity<String>("invalid shop id",HttpStatus.NOT_FOUND);
	} catch (MedicineNameNotFoundException e) {
		return new ResponseEntity<String>("Medicine does not exist",HttpStatus.NOT_FOUND);
	}
		
		
	}
	
	
	
	
	
	
	
}


