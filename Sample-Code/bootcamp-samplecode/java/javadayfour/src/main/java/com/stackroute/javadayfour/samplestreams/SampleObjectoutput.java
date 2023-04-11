package com.stackroute.javadayfour.samplestreams;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SampleObjectoutput {

	public static void main(String[] args) {
		
		Author authorobj=new Author("Mortein","Spring", 47);
		
		FileOutputStream fileobj;
		try {
			fileobj = new FileOutputStream("author.txt");
			
			ObjectOutputStream objoutput=new ObjectOutputStream(fileobj);
			
			objoutput.writeObject(authorobj);
			
			System.out.println("Object stored in a file");
		} 
		
  catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
