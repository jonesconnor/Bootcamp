package com.cgi.payments.service;

import java.util.List;

import com.cgi.payments.exception.PaymentAlreadyExistsException;
import com.cgi.payments.exception.PaymentNotFoundException;
import com.cgi.payments.model.Payment;
import com.stripe.exception.StripeException;

public interface PaymentService {
	
	//Generates a payment token with stripe server
	String getPaymentToken(float totalServiceHours, float pricePerHour ) throws StripeException;
	
	Payment savePayment(Payment payment) throws PaymentAlreadyExistsException;
	
	List<Payment> getAllPayments();
	
	List<Payment> getAllUserPayments(String payerId);
	
	List<Payment> getAllProviderPayments(String payeeId);
	
	Payment getPayment(String receiptId) throws PaymentNotFoundException;
	
	//updates the payment info. The status, payee and payer Ids are immutable.
	Payment updatePayment(Payment payment) throws PaymentNotFoundException, UnsupportedOperationException;
	
	//Cancels a payment in the server, refunds the money to the user.
	boolean cancelPayment(String paymentToken) throws PaymentNotFoundException, UnsupportedOperationException, StripeException;
	
	//gets a payment entity based on the paymentToken
	Payment getPaymentByToken(String paymentToken) throws PaymentNotFoundException;
	
	//will verify the state of the payment on the stripe server only if the payment has not been already refunded.
	//It will update the status to the current on Stripe's
	String checkPaymentStatus(String paymentToken) throws PaymentNotFoundException, UnsupportedOperationException, StripeException;
}
