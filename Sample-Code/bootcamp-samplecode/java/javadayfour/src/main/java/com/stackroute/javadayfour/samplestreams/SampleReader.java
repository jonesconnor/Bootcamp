package com.stackroute.javadayfour.samplestreams;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SampleReader {

	public static void main(String[] args) {

		try {
			BufferedReader breader=new BufferedReader(new FileReader("hello.txt"));
			
		String line;//breader.readLine();

		
		
		while( (line=breader.readLine())!=null)
			System.out.println(line);
		
		} 
		
		catch (FileNotFoundException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			 
			e.printStackTrace();
		}
	}

}
