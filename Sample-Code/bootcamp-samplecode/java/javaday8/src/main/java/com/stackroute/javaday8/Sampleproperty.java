package com.stackroute.javaday8;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Sampleproperty {

	public static void main(String[] args) {

		Properties myproperty=new Properties();
		
	
	    try {
	    	FileInputStream fstream=new FileInputStream("src/main/resources/mycredentails.properties");
			myproperty.load(fstream);
			
			System.out.println("User name is " + myproperty.getProperty("DB_USER"));
			System.out.println( "Password is " + myproperty.getProperty("DB_PASSWORD"));
			System.out.println("database " + myproperty.getProperty("DB_DATABASE"));
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
	}

}
