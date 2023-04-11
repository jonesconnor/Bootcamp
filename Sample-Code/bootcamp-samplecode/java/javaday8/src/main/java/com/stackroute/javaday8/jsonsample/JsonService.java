package com.stackroute.javaday8.jsonsample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonService {

	public static void storeJson(Map course)
	{
		Gson gsobj=new GsonBuilder().setPrettyPrinting().create();
		

		
		try
		{
			String jsoncourse=	gsobj.toJson(course);
			
			BufferedWriter bwriter=new BufferedWriter(new FileWriter("course.data"));
			
			bwriter.write(jsoncourse);
			bwriter.flush();
			System.out.println("File created");
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	
	public static Course getCourse() throws Exception
	{
		
		
		
		
 FileInputStream inputdata=new FileInputStream("course.data");
 	String data=new String(inputdata.readAllBytes());
 	
 	Gson gsobj=new GsonBuilder().setPrettyPrinting().create();
 Course course=	gsobj.fromJson(data, Course.class);
 
 return course;
		
	}
	
	
}
