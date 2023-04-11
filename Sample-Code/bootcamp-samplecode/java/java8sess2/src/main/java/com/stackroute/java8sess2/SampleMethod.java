package com.stackroute.java8sess2;

import java.util.Arrays;
import java.util.List;



interface iMaths
{
	int squareData(int num);
}
// parameter and return type  : method sigature

interface iValidate
{
	boolean checkDate(int day1 ,int day2);
}

interface iStringPlay
{
	String format(String line);
}

interface iProcessBook
{
	Book getBook();
}

public class SampleMethod {

	public static void main(String[] args) {
	 
	//	iMaths mathobj=(a)->a*a;
		
		iMaths mathobj1=new MyMethod()::squareIt;
		
	System.out.println(mathobj1.squareData(5));
		
	iValidate validobj=MyMethod::checkData;
	
    System.out.println(validobj.checkDate(26, 28));
    
    iProcessBook process=Book::new;
    
   Book bknew= process.getBook();
   
	List<Book> books=Arrays.asList( new Book("Java in Action",300), new Book("Spring",250), new Book("Soul and Peace",450));

	
	books.forEach(System.out:: println);
	
	books.forEach(e->System.out.println(e));

	}

}
