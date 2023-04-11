package com.stackroute.basics;
import java.util.Scanner;

public class NumberAverage {

    public static void main(String[] args) {
        new NumberAverage().getArrayValues();
    }

    //get the values of the array from the user
    public void getArrayValues() {
    	Scanner scan = new Scanner(System.in);
    	
//    	System.out.println("Enter the number of elements in the array:");
    	int n = scan.nextInt();
//    	scan.nextLine();

//    	System.out.println("Enter the values: ");
//    	String[] vals = scan.nextLine().split(" ");
    	int[] arr = new int[n];
    	
    	if (arr.length == 0) {
    		System.out.println("Empty array");
    	} else {
    		for (int i = 0; i < n; i++) {
//        	    nums[i] = Integer.parseInt(vals[i]);
    			arr[i] = scan.nextInt();
        	}
            
//            String average = findAverage(nums);
//    		String average = findAverage(arr);
            System.out.println(findAverage(arr));
    	}
        
    }

    //write here logic to calculate the average an array
    public String findAverage(int[] inputArray) {
    	
    	int average = 0;
    	
    	for (int i : inputArray) {
    		if (i < 0) {
    			return "Give proper positive integer values";
    		}
    		average += i;
    	}
    	
    	if (inputArray.length != 0) {
    		average /= inputArray.length;
    		return "The average value is: " + average;
    	} else {
    		return "Empty array";
    	}
    	
    }
}
