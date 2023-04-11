package com.stackroute.javadaytwo;

public class SampleString {

	public static void main(String[] args) {
		
		
		String companyName="CGI";
		
		String trainingOrg=companyName;
		
		System.out.println(companyName.hashCode());
		
		companyName="Stackroute";
		
		System.out.println(companyName.hashCode());
		
		
		System.out.println(trainingOrg.hashCode());
		
		
		String temp="CGI";
		
		System.out.println("temp valu" + temp.hashCode());
		
		temp="CGI-CAnada";
		
		StringBuilder sentence=new StringBuilder("Hello");
		
		
		System.out.println(sentence + " " + sentence.hashCode());
		
		sentence.append(" World");
		
		System.out.println(sentence + " " + sentence.hashCode());

		
		

	}

}
