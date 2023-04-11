package com.stackroute.javaday8;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SampleRegEx {

	public static void main(String[] args) {
	 
		String name="Mohammed";
		
		String postalcode="090-6201";
		
		Pattern patname2=Pattern.compile("M.*",Pattern.CASE_INSENSITIVE);
		
		Pattern patname=Pattern.compile("m[a-z][armn]y",Pattern.CASE_INSENSITIVE);
	//	Matcher matchobj=patname.matcher(name);
		
		
		Matcher matchobj=patname2.matcher(name);
		
		if(matchobj.matches())
			System.out.println("Valid Name");
		else
			System.out.println("Invalid name");
		
		
		Pattern patpostal=Pattern.compile("0[0-9][0-9]-\\d{6}");
		Matcher matchobj2=patpostal.matcher(postalcode);
		
		
		if(matchobj2.matches())
			System.out.println("Valid Code");
		else
			System.out.println("Invalid code");
		
		String input="12";
		// 01 or 02 or 03 --- 09 or 1 or 2 or 3 till 9 or  10, 11 , 12
		
		Pattern pata3=Pattern.compile("(0?[1-9]|1[012])");
		Matcher matchobj3=pata3.matcher(input);
		
		if(matchobj3.matches())
			System.out.println("Valid number");
		else
			System.out.println("Invalid number");
		
		// 0 to 31\
		//0 ,01,02,3,..9
		//10,11,12,13, 19
		//20,21,22,23  29
		//30,31
	
		String data="36";

		Pattern pat4=Pattern.compile("(0?[1-9]|[12][0-9]|3[01])");
		
		
Matcher matchobj5=pat4.matcher(data);
		
		if(matchobj5.matches())
			System.out.println("Valid date");
		else
			System.out.println("Invalid date");
		
		
		String dob="08/31/2005";
		
		Pattern patdate=Pattern.compile("(0?[1-9]|1[012])[/-](0?[1-9]|[12][0-9]|3[01])[/-]\\d{4}"); 
	
		Matcher matchobj6=patdate.matcher(dob);
		
		if (matchobj6.matches())
		
			System.out.println("Valid date of birth");
		else
			
			System.out.println("InValid date of birth");

		
		String sentence="Monday is always perfect session for learning than some other session";
		
		Pattern patday=Pattern.compile("day");
		Matcher  matchday=patday.matcher(sentence);
		
		if (matchday.find())
			System.out.println("Given word is found in the sentence");
		else
			System.out.println("Not found");

		
		
		//System.out.println(output);
		
		
		
	}

}



