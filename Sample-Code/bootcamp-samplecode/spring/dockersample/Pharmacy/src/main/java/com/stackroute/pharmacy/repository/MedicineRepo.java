package com.stackroute.pharmacy.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.pharmacy.model.Medicine;

public interface MedicineRepo extends MongoRepository<Medicine,String> {

	List<Medicine> findByPriceGreaterThan(int price);
	Medicine				findByMedname(String med);
	
}
