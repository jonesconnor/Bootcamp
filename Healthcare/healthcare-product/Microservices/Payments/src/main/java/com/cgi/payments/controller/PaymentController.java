package com.cgi.payments.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.payments.exception.PaymentAlreadyExistsException;
import com.cgi.payments.exception.PaymentNotFoundException;
import com.cgi.payments.model.Payment;
import com.cgi.payments.service.PaymentService;
import com.google.gson.Gson;
import com.stripe.exception.StripeException;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	@GetMapping("/payment/token/{totalServiceHours}/{pricePerHour}")
	public ResponseEntity<?> getNewStripeToken(
			@PathVariable("totalServiceHours") String totalServiceHours,
			@PathVariable("pricePerHour") String pricePerHour)
	{
		try {
			Gson gson = new Gson(); 
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("paymentToken", paymentService.getPaymentToken(Float.parseFloat(totalServiceHours), Float.parseFloat(pricePerHour)));
			String json = gson.toJson(map); 
			return new ResponseEntity<String>(json, HttpStatus.OK);
		} catch (NumberFormatException e) {
			return new ResponseEntity<String>("Price and total hours must be numbers", HttpStatus.NOT_ACCEPTABLE);
		} catch (StripeException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/patient/payment")
	public ResponseEntity<?>addPayment(@RequestBody Payment payment)
	{
		try {
			return new ResponseEntity<Payment>(paymentService.savePayment(payment), HttpStatus.CREATED);
		}
		catch(PaymentAlreadyExistsException e)
		{
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/admin/payments")
	public ResponseEntity<?> getAllPayments()
	{	
		return new ResponseEntity<List<Payment>>(paymentService.getAllPayments(), HttpStatus.OK);
	}

	@GetMapping("/patient/payments/{payerId}")
	public ResponseEntity<?> getAllUserPayments(@PathVariable("payerId") String payerId)
	{
		try {
			return new ResponseEntity<List<Payment>>(paymentService.getAllUserPayments(payerId), HttpStatus.OK);
		} catch (NumberFormatException e) {
			return new ResponseEntity<String>("Payer Id must be a number", HttpStatus.NOT_ACCEPTABLE);
		}
		
	}

	@GetMapping("/serviceProvider/payments/{payeeId}")
	public ResponseEntity<?> getAllProviderPayments(@PathVariable("payeeId") String payeeId)
	{
		try {
			return new ResponseEntity<List<Payment>>(paymentService.getAllProviderPayments(payeeId), HttpStatus.OK);
		} catch (NumberFormatException e) {
			return new ResponseEntity<String>("Payee Id must be a number", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping("/general/payment/receipt/{receiptId}")
	public ResponseEntity<?> getPayment(@PathVariable("receiptId") String receiptId)
	{
		try {
			return new ResponseEntity<Payment>(paymentService.getPayment(receiptId), HttpStatus.OK);
		} catch (PaymentNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		catch (NumberFormatException e) {
			return new ResponseEntity<String>("Receipt Id must be a number", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping("/general/payment/paymentToken/{paymentToken}")
	public ResponseEntity<?> getPaymentByPaymentToken(@PathVariable("paymentToken") String paymentToken)
	{
		try {
			return new ResponseEntity<Payment>(paymentService.getPaymentByToken(paymentToken), HttpStatus.OK);
		} catch (PaymentNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	

	@PutMapping("/payment")
	public ResponseEntity<?> updatePayment(@RequestBody Payment payment)
	{
		try {
			return new ResponseEntity<Payment>(paymentService.updatePayment(payment), HttpStatus.OK);
		} catch (PaymentNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		catch (UnsupportedOperationException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping("/payment/verificationStatus/{paymentIntent}")
	public ResponseEntity<?> checkPaymentStatus(@PathVariable String paymentIntent)
	{
		try {
			Gson gson = new Gson(); 
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("status", paymentService.checkPaymentStatus(paymentIntent));
			String json = gson.toJson(map); 
			return new ResponseEntity<String>(json, HttpStatus.OK);
		} catch (PaymentNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		catch (UnsupportedOperationException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		} catch (StripeException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FORBIDDEN);
		}
	}

	@DeleteMapping("/general/payment/{paymentIntent}")
	public ResponseEntity<?> deletePayment(@PathVariable("paymentIntent") String paymentIntent)
	{
		try {
			Gson gson = new Gson(); 
			HashMap<String, String> map = new HashMap<String,String>();
			map.put("status", "" + paymentService.cancelPayment(paymentIntent));
			String json = gson.toJson(map); 
			return new ResponseEntity<String>(json, HttpStatus.OK);
		} catch (PaymentNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		catch (UnsupportedOperationException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		catch (NumberFormatException e) {
			return new ResponseEntity<String>("Receipt Id must be a number", HttpStatus.NOT_ACCEPTABLE);
		} catch (StripeException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.FORBIDDEN);
		}
	}
}
