package com.cgi.repository;

import org.springframework.stereotype.Repository;

import com.cgi.model.Driver;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DriverRepo extends JpaRepository<Driver,Integer>{
	
	List<Driver> findByCity(String city);
	
	List<Driver> findByQuoteamountGreaterThan(long quoteamount);
	
}
