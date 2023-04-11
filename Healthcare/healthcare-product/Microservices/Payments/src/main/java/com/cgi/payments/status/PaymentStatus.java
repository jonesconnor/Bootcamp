package com.cgi.payments.status;


public class PaymentStatus {

	public static int getValue(String status){
		switch (status) {
			case "CONFIRMED":
				return 2;
			case "CANCELLED":
				return 1;
			case "PENDING":
				return 0;
			default:
				return 0;
		}
	}
	
	public static String getEquivalentString(int status){
		switch (status) {
			case 2:
				return "CONFIRMED";
			case 1:
				return "CANCELLED";
			case 0:
				return "PENDING";
			default:
				return "PENDING";
		}
	}
}