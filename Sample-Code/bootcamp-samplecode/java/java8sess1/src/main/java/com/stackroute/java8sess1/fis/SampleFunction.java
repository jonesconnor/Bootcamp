package com.stackroute.java8sess1.fis;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;



public class SampleFunction {

	public static void main(String[] args) {
		
		List<Book> books=Arrays.asList( new Book("Java in Action",300), new Book("Spring",250), new Book("Soul and Peace",450));
		
		Function<Book,String> fun= (bobj)->{
												if (bobj.getPrice()>=300)
													return bobj.getBookName();
											
													return "";
											};
		
		
		for (Book book : books )
		{
			String ans=fun.apply(book);
			if(!ans.isBlank())
			System.out.println("Book name is  " + ans);
			
		}
	}

}
