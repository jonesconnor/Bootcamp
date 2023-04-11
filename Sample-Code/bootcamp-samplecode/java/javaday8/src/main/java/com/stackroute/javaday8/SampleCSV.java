package com.stackroute.javaday8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class SampleCSV {

	public static void main(String[] args) {
		

		try {
			BufferedReader breader=new BufferedReader(new FileReader("empdata.csv"));
			String header=breader.readLine();
			System.out.println(header);
		String line;
			 while((line=breader.readLine())!=null)
			 {
				 String record=line;
				 String [] data=record.split(",");
				 
				 if (data[3].equals("Canada"))
					 System.out.println(record);
			 }
			
			
		} 
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		
		
	}

}
