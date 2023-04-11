package com.cgi.payments.stripe;


import com.cgi.payments.status.PaymentStatus;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentIntentCreateParams.Builder;
import com.stripe.param.RefundCreateParams;

public class StripePayments {
	
	private static Builder paymentIntentParams;
	private final static double CENTS = 100;
	
	static {
		Stripe.apiKey = "sk_test_51MoTXsGoYEfXSxkZ56cixd92RYdAKZt366vF8ZJdSPvsVm4tRdrNPEvGkBSoiwLpI77A6y1Bp7dEqibnD9Ug2A1w00cfbNMABH";
				
		paymentIntentParams = PaymentIntentCreateParams
				.builder()
				 .setCurrency("cad");
	}
	
	private StripePayments(){
	}
	
	public static String generateStripePaymentKey(float price) throws StripeException{
		
		PaymentIntentCreateParams paymentIntentBuildedParams = paymentIntentParams
		    .setAmount((long) (price * CENTS)) //Multiplied by a hundred since stripe considers it as CENTS
		    .build();
		
		PaymentIntent paymentIntent = PaymentIntent.create(paymentIntentBuildedParams);
		
		return paymentIntent.getClientSecret();

	}
	
	
	private static String substractSecretFromPaymentIntent(String paymentIntent) {
		return paymentIntent.substring(0, paymentIntent.indexOf("_secret_"));
	}
	
	public static boolean refundStripePayment(String paymentIntent) throws StripeException{
		
		String formattedIntent = substractSecretFromPaymentIntent(paymentIntent);
		
		RefundCreateParams refundParams =
				  RefundCreateParams.builder().setPaymentIntent(formattedIntent).build();
		
		//Refund refund = 
		Refund.create(refundParams);
		
		return true;

	}
	
	public static int verifyPaymentStatus(String paymentIntent) throws StripeException{
		
		String formattedIntent = substractSecretFromPaymentIntent(paymentIntent);
		
		PaymentIntent stripePaymentInfo = PaymentIntent.retrieve(formattedIntent);
		
		String paymentStatus = stripePaymentInfo.getStatus();
		int status = 0;
		System.out.println(paymentStatus);
		switch (paymentStatus) {
			case "succeeded":
				status = PaymentStatus.getValue("CONFIRMED");
				break;

			default: //Since we are not capturing Cancelled, any other state will be pending.
				status = PaymentStatus.getValue("PENDING");
				break;
		}
		
		
		return status;
	}
}
