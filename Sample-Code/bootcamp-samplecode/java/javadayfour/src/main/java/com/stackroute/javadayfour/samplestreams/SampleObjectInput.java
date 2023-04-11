package com.stackroute.javadayfour.samplestreams;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SampleObjectInput {

	public static void main(String[] args) {
		try {
			Author author=null;
			ObjectInputStream objinput=new ObjectInputStream(new FileInputStream("author.txt"));
			author=(Author) objinput.readObject();
			
			System.out.println("result from object input " + author);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
