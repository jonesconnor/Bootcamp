package com.cgi.payments.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.cgi.payments.exception.PaymentAlreadyExistsException;
import com.cgi.payments.exception.PaymentNotFoundException;
import com.cgi.payments.model.Payment;
import com.cgi.payments.repository.PaymentRepository;
import com.cgi.payments.status.PaymentStatus;

public class PaymentServiceImplTests {
	
	@InjectMocks
	PaymentServiceImpl service;
	
	@Mock
	PaymentRepository repo;
	
	 Payment payment1 = new Payment();
	 Payment payment2 = new Payment();
	 Payment payment3 = new Payment();
	 
	 List<Payment> payments = new ArrayList<Payment>();
	 
	 @BeforeEach
     public void init()
     {
		// ensure mock objects are properly created and injected
		MockitoAnnotations.openMocks(this);

    	 payment1.setPayeeId("1");
    	 payment1.setPayerId("1");
    	 payment1.setHours(1);
    	 payment1.setPricePerHour(75);
    	 payment1.setPaymentToken("pi_example");
    	 payment1.setReceiptId("1");
    	 payment1.setServiceType("Doctor");
    	 payment1.setStatus(PaymentStatus.getValue("PENDING"));
    	 
    	 payment2.setPayeeId("1");
    	 payment2.setPayerId("1");
    	 payment2.setHours(1);
    	 payment2.setPricePerHour(80);
    	 payment2.setPaymentToken("pi_example");
    	 payment2.setReceiptId("1");
    	 payment2.setServiceType("Doctor");
    	 payment2.setStatus(PaymentStatus.getValue("PENDING"));
    	 
    	 payment3.setPayeeId("2");
    	 payment3.setPayerId("2");
    	 payment3.setHours(1);
    	 payment3.setPricePerHour(50);
    	 payment3.setPaymentToken(null);
    	 payment3.setReceiptId("3");
    	 payment3.setServiceType("Caretaker");
    	 payment3.setStatus(PaymentStatus.getValue("CONFIRMED"));
     }

	 @Test
	 public void addPaymentShouldSucceed() throws PaymentAlreadyExistsException{
		 Mockito.when(repo.save(payment1)).thenReturn(payment1);
		 Payment result = service.savePayment(payment1);
		 
		 assertEquals(result.getReceiptId(), "1");
		 verify(repo,times(1)).findById(result.getReceiptId());
		verify(repo,times(1)).save(payment1);

	 }
	 
	 @Test
	 public void addPaymentShouldFail() throws PaymentAlreadyExistsException{
		 Mockito.when(repo.findById(payment1.getReceiptId())).thenReturn(Optional.of(payment1));
		 assertThrows(PaymentAlreadyExistsException.class,()->service.savePayment(payment1));

	 }
	 
	 @Test
	 public void findAllPaymentsSucceed(){
		 payments.add(payment1);
		 
		 Mockito.when(repo.findAll()).thenReturn(payments);
		 List<Payment> result = service.getAllPayments();
		 
		 assertEquals(result, payments);
		 verify(repo,times(1)).findAll();
	 }
	 
	 @Test
	 public void findUserPaymentsSucceed(){
		 
		 Mockito.when(repo.findByPayerIdIgnoreCase("1")).thenReturn(payments);
		 List<Payment> result = service.getAllUserPayments("1");
		 
		 assertEquals(result, payments);
		 verify(repo,times(1)).findByPayerIdIgnoreCase("1");
	 }
	 
	 @Test
	 public void findServiceProviderPaymentsSucceed(){
		 
		 Mockito.when(repo.findByPayeeIdIgnoreCase("1")).thenReturn(payments);
		 List<Payment> result = service.getAllProviderPayments("1");
		 
		 assertEquals(result, payments);
		 verify(repo,times(1)).findByPayeeIdIgnoreCase("1");
	 }
	 
	 @Test
	 public void getPaymentShouldSucceed() throws PaymentNotFoundException  {
		 Mockito.when(repo.findById("1")).thenReturn(Optional.of(payment1));
		 Payment result = service.getPayment("1");
		 
		 assertEquals(result.getReceiptId(), "1");
		 verify(repo,times(1)).findById("1");
	 }
	 
	 @Test
	 public void getPaymentShouldFail() throws PaymentNotFoundException{
		 Mockito.when(repo.findById("5")).thenReturn(Optional.of(payment1));
		 assertThrows(PaymentNotFoundException.class,()->service.getPayment("2"));

	 }
	 
	 @Test
	 public void getPaymentByTokenShouldSucceed() throws PaymentNotFoundException  {
		 Mockito.when(repo.findByPaymentToken("pi_example")).thenReturn(payment1);
		 Payment result = service.getPaymentByToken("pi_example");
		 
		 assertEquals(result.getReceiptId(), "1");
		 verify(repo,times(1)).findByPaymentToken("pi_example");
	 }
	 
	 @Test
	 public void getPaymentByTokenShouldFail() throws PaymentNotFoundException{
		 Mockito.when(repo.findByPaymentToken("pi_example_failure")).thenReturn(any());
		 assertThrows(PaymentNotFoundException.class,()->service.getPaymentByToken("pi_example_failure"));

	 }
	 
	 @Test
	 public void updatePaymentShouldSucceed() throws PaymentNotFoundException, UnsupportedOperationException  {
		 Mockito.when(repo.findById(payment2.getReceiptId())).thenReturn(Optional.of(payment1));
		 Mockito.when(repo.save(payment2)).thenReturn(payment2);
		 Payment result = service.updatePayment(payment2);
		 
		 assertEquals(result.getReceiptId(), "1");
		 assertEquals(result.getPricePerHour(), 80);
		 verify(repo,times(1)).save(payment2);
	 }
	 
	 @Test
	 public void updatePaymentShouldFailPaymentNotFoundException() throws PaymentNotFoundException, UnsupportedOperationException{
		 Mockito.when(repo.save(payment3)).thenReturn(any());
		 assertThrows(PaymentNotFoundException.class,()->service.updatePayment(payment3));

	 }
	 
	 @Test
	 public void updatePaymentShouldFailUnsupportedOperationException() throws PaymentNotFoundException, UnsupportedOperationException{
		 payment3.setReceiptId("1");
		 Mockito.when(repo.save(payment3)).thenReturn(any());
		 assertThrows(PaymentNotFoundException.class,()->service.updatePayment(payment3));
		 
	 }
}
