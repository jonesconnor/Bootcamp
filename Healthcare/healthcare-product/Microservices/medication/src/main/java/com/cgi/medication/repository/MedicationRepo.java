package com.cgi.medication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cgi.medication.model.Medication;

@Repository
public interface MedicationRepo extends MongoRepository<Medication, String>{

}
