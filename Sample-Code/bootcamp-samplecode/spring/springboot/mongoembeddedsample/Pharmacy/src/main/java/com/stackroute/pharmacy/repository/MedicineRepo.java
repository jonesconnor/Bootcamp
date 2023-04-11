package com.stackroute.pharmacy.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.pharmacy.model.MedicalShop;
import com.stackroute.pharmacy.model.Medicine;


@Repository
public interface MedicineRepo extends MongoRepository<MedicalShop,String> {

  
     
	List<MedicalShop> findByAddr(String addr);
	
	
}
