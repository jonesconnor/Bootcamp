package com.stackroute.java8sess1.fis;

import java.util.Arrays;
import java.util.List;

public class SampleSort {

	public static void main(String[] args) {
		List<Book> books=Arrays.asList( new Book("Java in Action",300), new Book("Spring",250), new Book("Soul and Peace",450));

	
		
		books.sort( (bk1,bk2)-> {
										if (bk1.getPrice()==bk2.getPrice())
											return 0;
										else if (bk1.getPrice()<bk2.getPrice())
											return 1;
										else
											return -1;
									});
		
		System.out.println(books);
		books.sort(   (bk1,bk2)->  bk1.getBookName().compareTo(bk2.getBookName()));
		System.out.println(books);
	
	
	}

	
}
