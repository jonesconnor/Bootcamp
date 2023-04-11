package com.stackroute.javadayfour.samplestreams;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SampleBuffOutputStream {

	public static void main(String[] args) {
		 
		
		try {
			BufferedOutputStream buffoutobj=new BufferedOutputStream(new FileOutputStream("newfile.txt"));
			
			String sentence="Never say Never";
			
			buffoutobj.write(sentence.getBytes());
			
			buffoutobj.close();
			
			
			System.out.println("File Created ");
		} 
		
		catch (FileNotFoundException e) {
			
			System.out.println("Exception " + e.getMessage());
			
		} catch (IOException e) {
			System.out.println("Exception " + e.getMessage());

		}
		
	}

}
