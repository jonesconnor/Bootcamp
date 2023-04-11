package com.stackroute.javadayfour.samplestreams;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SampleBuffStream {

	public static void main(String[] args) {
		
		try {
			BufferedInputStream bufferobj=new BufferedInputStream(new FileInputStream("hello.txt"));
			
			
			byte[] result=bufferobj.readAllBytes();
			
			System.out.println(new String(result));
			
			
			DataInputStream data=new DataInputStream(System.in);
			
			System.out.println("enter a number");
			
			   int num1= data.readInt();
			   
			   
			   System.out.println("The given input is " + num1);
			   
		}
		
		catch (FileNotFoundException e) {
		System.out.println(e.getMessage());
		} 
		
		catch (IOException e) {
			
			System.out.println(e.getMessage());

			}

	}

}
