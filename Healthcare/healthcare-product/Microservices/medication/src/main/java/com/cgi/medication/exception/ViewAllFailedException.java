package com.cgi.medication.exception;

public class ViewAllFailedException extends Exception{
	
	public ViewAllFailedException() {
		String mess = "There was an error that occured while fetching medications";
	}

}
