package com.stackroute.exercises;


public class Fraction {
    //Write logic to calculate the fraction and return as a String
    public String fractionCalculator(int firstNumber, int secondNumber) {
    	int ans;
    	try {
    		ans = firstNumber / secondNumber;
    	} catch(ArithmeticException e) {
    		return e.toString();
    	}
        return "" + ans;
    }
}
