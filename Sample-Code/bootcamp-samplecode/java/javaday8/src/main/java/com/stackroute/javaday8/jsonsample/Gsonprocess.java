package com.stackroute.javaday8.jsonsample;

import java.util.HashMap;
import java.util.Map;

public class Gsonprocess {

	public static void main(String[] args) throws Exception {
	 
		Map coursedata=new HashMap();
		coursedata.put("courseName","Oracle");
		coursedata.put("duration", "6 months");

	//	JsonService.storeJson(coursedata);
		
		
		Course course=JsonService.getCourse();
		
		System.out.println(course);
		
	}

}
