package com.cgi.payments.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.payments.exception.PaymentAlreadyExistsException;
import com.cgi.payments.exception.PaymentNotFoundException;
import com.cgi.payments.model.Payment;
import com.cgi.payments.repository.PaymentRepository;
import com.cgi.payments.status.PaymentStatus;
import com.cgi.payments.stripe.StripePayments;
import com.stripe.exception.StripeException;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	PaymentRepository paymentRepo;
	
	@Override
	public String getPaymentToken( float totalServiceHours, float pricePerHour) throws StripeException {
		// HERE GOES STRIPE OPERATION
		return StripePayments.generateStripePaymentKey(pricePerHour * totalServiceHours);
	}

	@Override
	public Payment savePayment(Payment payment) throws PaymentAlreadyExistsException {
		Optional<Payment> optPayment = paymentRepo.findById(payment.getReceiptId());
		
		if(optPayment.isPresent()) {
			throw new PaymentAlreadyExistsException("Payment with this id has already been created.");
		}
		else {
			
			//When saving the payment, the status will be always pending. verify this.
			payment.setStatus(PaymentStatus.getValue("PENDING"));
			return paymentRepo.save(payment);
		}
	}

	@Override
	public List<Payment> getAllPayments() {
	
		return paymentRepo.findAll();
	}

	@Override
	public List<Payment> getAllUserPayments(String payerId) {
		return paymentRepo.findByPayerIdIgnoreCase(payerId);
	}

	@Override
	public List<Payment> getAllProviderPayments(String payeeId) {
		return paymentRepo.findByPayeeIdIgnoreCase(payeeId);
	}

	@Override
	public Payment getPayment(String receiptId) throws PaymentNotFoundException{
		Optional<Payment> optPayment = paymentRepo.findById(receiptId);
		
		if(optPayment.isEmpty()) throw new PaymentNotFoundException("Not found. Please verify receipt id");
		
		return optPayment.get();
	}

	@Override
	public Payment getPaymentByToken(String paymentToken) throws PaymentNotFoundException{
		Payment optPayment = paymentRepo.findByPaymentToken(paymentToken);
		
		if(optPayment != null) return optPayment;
		
		throw new PaymentNotFoundException("Not found. Please verify paymentToken");
	}
	
	//This endpoint does not accept changes on status, payee nor payer ids.
	@Override
	public Payment updatePayment(Payment payment) throws PaymentNotFoundException, UnsupportedOperationException{
		Payment paymentToUpdate = getPayment(payment.getReceiptId());
		
		if(payment.getPayeeId() != paymentToUpdate.getPayeeId() 
					|| payment.getPayerId() != paymentToUpdate.getPayerId()) {
			throw new UnsupportedOperationException("Payer id and payee id must not be modified ");
		}
		
		//Make sure that the status is not modified here, no need to throw an error since it is not critical.
		payment.setStatus(paymentToUpdate.getStatus());
			
		return paymentRepo.save(payment);
		
	}

	@Override
	public boolean cancelPayment(String paymentToken) throws PaymentNotFoundException, UnsupportedOperationException, StripeException{
		Payment paymentToCancel = getPaymentByToken(paymentToken);
		
		//Only allow refund if the payment status is confirmed
		//Continue with operation if Stripe cancel is successful
		if(paymentToCancel.getStatus() == PaymentStatus.getValue("CONFIRMED") 
				&& StripePayments.refundStripePayment(paymentToCancel.getPaymentToken())) {
			paymentToCancel.setStatus(PaymentStatus.getValue("CANCELLED"));
			paymentRepo.save(paymentToCancel);
		}
		else throw new UnsupportedOperationException("Only confirmed payments can be refunded");
		
		return true;
	}

	@Override
	public String checkPaymentStatus(String paymentToken) throws PaymentNotFoundException, UnsupportedOperationException, StripeException {
		
		Payment paymentToVerify = getPaymentByToken(paymentToken);
		
		if(paymentToVerify.getStatus() == PaymentStatus.getValue("CANCELLED")) throw new UnsupportedOperationException("This payment has been already cancelled");
		
		int currentStatus = StripePayments.verifyPaymentStatus(paymentToken);
		
		//Check if there is a difference in status between Stripe and the server
		if(paymentToVerify.getStatus() != currentStatus) {
			paymentToVerify.setStatus(currentStatus);
			
			paymentRepo.save(paymentToVerify);
		}
		
		return PaymentStatus.getEquivalentString(currentStatus);
	}

}
