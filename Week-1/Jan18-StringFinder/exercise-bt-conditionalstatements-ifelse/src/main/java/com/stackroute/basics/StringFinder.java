package com.stackroute.basics;
import java.util.Scanner;

public class StringFinder {
    //Create Scanner object as instance variable
	private Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        //Get three strings from the user
    	StringFinder StrFind = new StringFinder();
    	
    	String search = StrFind.getInput();
    	String firstStr = StrFind.getInput();
    	String secondStr = StrFind.getInput();
    	
    	int result = StrFind.findString(search, firstStr, secondStr);
//      System.out.println("RESULT: " + result);
        StrFind.displayResult(result);
    	
    	StrFind.closeScanner();
    }

    public String getInput() {
    	String str = scan.nextLine();
        return str;
    }

    public void displayResult(int result) {
        //displays the result
    	if (result == 1) {
    		System.out.println("Found as expected");
    	}
    	else if (result == 0) {
    		System.out.println("Not found");
    	} else {
    		System.out.println("Empty string or null");
    	}
    }

    public int findString(String searchString, String firstString, String secondString) {
    	
    	if (searchString == null || firstString == null || secondString == null) {
    		return -1;
    	}
    	
    	else if ( searchString.equals("") || firstString.equals("") || secondString.equals("")) {
    		return -1;
    	}
    	// if searchString contains firstString and secondString
    	else if (searchString.contains(firstString) && searchString.contains(secondString)) {
    		// use indexOf to ensure they're in the right order
    		int firstIndex = searchString.indexOf(firstString);
        	int secondIndex = searchString.indexOf(secondString);
        	
        	if (firstIndex < secondIndex) {
        		return 1;
        	} else {
        		return 0;
        	}
    	} else if ((!searchString.contains(firstString) && searchString.contains(secondString)) || (searchString.contains(firstString) && !searchString.contains(secondString))){
    		return 0;
    	} else {
    		return -1;
    	}

    }

    public void closeScanner() {
    	scan.close();
    }
}
