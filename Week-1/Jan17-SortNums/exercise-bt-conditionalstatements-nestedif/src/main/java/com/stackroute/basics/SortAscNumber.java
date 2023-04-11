package com.stackroute.basics;
import java.util.Scanner;


public class SortAscNumber {

    public static void main(String[] args) {
        new SortAscNumber().getNumbers();
    }

    //get the numbers from user through console
    public void getNumbers() {
    	Scanner scan = new Scanner(System.in);
    	int firstNum = Integer.parseInt(scan.next());
    	int secondNum = Integer.parseInt(scan.next());
    	int thirdNum = Integer.parseInt(scan.next());
    	int fourthNum = Integer.parseInt(scan.next());
    	
    	String sortedNums = numberSorter(firstNum, secondNum, thirdNum, fourthNum);
    	System.out.println(sortedNums);
    }

    //logic to sort the numbers
    public String numberSorter(int firstNumber, int secondNumber, int thirdNumber, int fourthNumber) {
    	if (firstNumber > secondNumber) {
    		int temp = secondNumber;
    		secondNumber = firstNumber;
    		firstNumber = temp;
    	}
    	if (firstNumber > thirdNumber) {
    		int temp = thirdNumber;
    		thirdNumber = firstNumber;
    		firstNumber = temp;
    	}
    	if (firstNumber > fourthNumber) {
    		int temp = fourthNumber;
    		fourthNumber = firstNumber;
    		firstNumber = temp;
    	}
    	if (secondNumber > thirdNumber) {
    		int temp = thirdNumber;
    		thirdNumber = secondNumber;
    		secondNumber = temp;
    	}
    	if (secondNumber > fourthNumber) {
    		int temp = fourthNumber;
    		fourthNumber = secondNumber;
    		secondNumber = temp;
    	}
    	if (thirdNumber > fourthNumber) {
    		int temp = fourthNumber;
    		fourthNumber = thirdNumber;
    		thirdNumber = temp;
    	}
    	
    	String sortedNums = "Sorted:{" + firstNumber + "," + secondNumber + "," + thirdNumber + "," + fourthNumber + "}";
    	
        return sortedNums;
    }
}
