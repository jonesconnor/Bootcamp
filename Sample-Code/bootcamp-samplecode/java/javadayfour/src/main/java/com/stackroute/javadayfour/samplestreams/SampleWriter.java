package com.stackroute.javadayfour.samplestreams;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SampleWriter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	try {
		BufferedWriter bwrite=new BufferedWriter(new FileWriter("welcome.txt"));
		
		bwrite.write("We have done with try and catch");
		
		bwrite.flush();
		System.out.println("Fiel created ");
		

		
	} catch (IOException e) {
 		e.printStackTrace();
	}

	
	}

}
