package com.cgi.payments.controller;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cgi.payments.exception.PaymentAlreadyExistsException;
import com.cgi.payments.exception.PaymentNotFoundException;
import com.cgi.payments.model.Payment;
import com.cgi.payments.service.PaymentService;
import com.cgi.payments.status.PaymentStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PaymentControllerTests {

	@InjectMocks
	PaymentController paymentController;

	@MockBean
	PaymentService paymentService;

	
	 MockMvc mockmvc;
	 
	 Payment payment1 = new Payment();
	 Payment payment2 = new Payment();
	 Payment payment3 = new Payment();
	 
	 List<Payment> payments = new ArrayList<Payment>();
	 
	 @BeforeEach
     public void init()
     {
    	 MockitoAnnotations.openMocks(this);
    	 mockmvc = MockMvcBuilders.standaloneSetup(paymentController).build();
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
	public void postRecordShouldSucceed() throws JsonProcessingException, Exception {
		Mockito.when(paymentService.savePayment(payment1)).thenReturn(payment1);
		mockmvc.perform(MockMvcRequestBuilders.post("/api/v1/patient/payment")
	    	 .contentType(MediaType.APPLICATION_JSON)
	    	 .content(convertObject(payment1)))
	    	 .andExpect(MockMvcResultMatchers.status().isCreated());

	 }

	@Test
	public void postRecordShouldFailWithException() throws JsonProcessingException, Exception {
		Mockito.when(paymentService.savePayment(any())).thenThrow(PaymentAlreadyExistsException.class);
		mockmvc.perform(MockMvcRequestBuilders.post("/api/v1/patient/payment")
	    	 .contentType(MediaType.APPLICATION_JSON)
	    	 .content(convertObject(payment1)))
	    	 .andExpect(MockMvcResultMatchers.status().isConflict());

	 }
	
	@Test
	public void getAllPaymentsShouldSucceed() throws JsonProcessingException, Exception {
		payments.add(payment1);
		
		Mockito.when(paymentService.getAllPayments()).thenReturn(payments);
		mockmvc.perform(MockMvcRequestBuilders.get("/api/v1/admin/payments"))
	    	 .andExpect(MockMvcResultMatchers.status().isOk());

	 }

	@Test
	public void getPatientPaymentsShouldSucceed() throws JsonProcessingException, Exception {
		
		Mockito.when(paymentService.getAllUserPayments("1")).thenReturn(payments);
		mockmvc.perform(MockMvcRequestBuilders.get("/api/v1/patient/payments/1"))
	    	 .andExpect(MockMvcResultMatchers.status().isOk());

	 }
	
	@Test
	public void getServiceProviderPaymentsShouldSucceed() throws JsonProcessingException, Exception {
		
		Mockito.when(paymentService.getAllProviderPayments("1")).thenReturn(payments);
		mockmvc.perform(MockMvcRequestBuilders.get("/api/v1/patient/payments/1"))
	    	 .andExpect(MockMvcResultMatchers.status().isOk());

	 }
	
	@Test
	public void getPaymentByReceiptShouldSucceed() throws JsonProcessingException, Exception {
		
		Mockito.when(paymentService.getPayment("1")).thenReturn(payment1);
		mockmvc.perform(MockMvcRequestBuilders.get("/api/v1/general/payment/receipt/1"))
	    	 .andExpect(MockMvcResultMatchers.status().isOk());

	 }
	
	@Test
	public void getPaymentByReceiptShouldFail() throws JsonProcessingException, Exception {
		
		Mockito.when(paymentService.getPayment("2")).thenThrow(PaymentNotFoundException.class);
		mockmvc.perform(MockMvcRequestBuilders.get("/api/v1/general/payment/receipt/2"))
	    	 .andExpect(MockMvcResultMatchers.status().isNotFound());

	 }
	
	@Test
	public void getPaymentByPaymentTokenShouldSucceed() throws JsonProcessingException, Exception {
		
		Mockito.when(paymentService.getPaymentByToken("pi_example")).thenReturn(payment1);
		mockmvc.perform(MockMvcRequestBuilders.get("/api/v1/general/payment/payment_token/pi_example"));

	 }
	
	@Test
	public void getPaymentByPaymentTokenShouldFail() throws JsonProcessingException, Exception {
		
		Mockito.when(paymentService.getPaymentByToken("pi_example")).thenThrow(PaymentNotFoundException.class);
		mockmvc.perform(MockMvcRequestBuilders.get("/api/v1/general/payment/payment_token/pi_example"))
	    	 .andExpect(MockMvcResultMatchers.status().isNotFound());

	 }
	
	@Test
	public void putRecordShouldSucceed() throws JsonProcessingException, Exception {
		Mockito.when(paymentService.updatePayment(payment2)).thenReturn(payment2);
		mockmvc.perform(MockMvcRequestBuilders.put("/api/v1/payment")
	    	 .contentType(MediaType.APPLICATION_JSON)
	    	 .content(convertObject(payment2)))
	    	 .andExpect(MockMvcResultMatchers.status().isOk());

	 }
	
	private String convertObject(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		return mapper.writeValueAsString(obj);
		
	}

}
