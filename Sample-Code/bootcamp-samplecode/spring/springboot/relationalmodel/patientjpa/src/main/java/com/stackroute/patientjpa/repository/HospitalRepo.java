package com.stackroute.patientjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.patientjpa.model.Hospital;

@Repository
public interface HospitalRepo extends JpaRepository<Hospital,String>{

}
