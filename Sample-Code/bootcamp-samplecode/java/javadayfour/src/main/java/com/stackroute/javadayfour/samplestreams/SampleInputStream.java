package com.stackroute.javadayfour.samplestreams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class SampleInputStream {

	public static void main(String[] args) {
		 
		byte[] data=new byte[200];
		
		InputStream inputobj=null;
		
		try {
			inputobj=new FileInputStream("hello.txt");
			
			inputobj.read(data);
			
			System.out.println(" Content of File is  " + new String(data));
			
		} 
		
		catch (FileNotFoundException e) {
		
			System.out.println(e.getMessage());
			
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());

		}
		
		
		
		
	}

}
