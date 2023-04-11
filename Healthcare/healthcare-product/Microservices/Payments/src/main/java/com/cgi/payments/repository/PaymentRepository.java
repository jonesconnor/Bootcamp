package com.cgi.payments.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cgi.payments.model.Payment;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String>{
	
	List<Payment> findByPayerIdIgnoreCase(String payerId);
	
	List<Payment> findByPayeeIdIgnoreCase(String payeeId);
	
	Payment findByPaymentToken(String paymentToken);
}
