package com.stackroute.java8sess2.streamsample;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.Collectors;

 
public class StreamBooks {

	public static void main(String[] args) {
		List<Book> books=Arrays.asList( new Book("Java in Action",300), 
				new Book("Spring",250),
				new Book("Soul and Peace",450),new Book("Garden",150));

	
	List<Book> bookresult=	books.stream().filter( bk-> bk.getPrice()>250 ).collect(Collectors.toList());
	//bookresult.forEach(System.out::println);
	
	List<String> bookname=books.stream().map( bk->bk.getBookName()).collect(Collectors.toList());
	//bookname.forEach(System.out::println);
	

	OptionalInt maxprice=books.stream().mapToInt( bk->bk.getPrice()).max();
	
	   if (maxprice.isPresent())
		   System.out.println("Max price is " + maxprice.getAsInt());
	   
	
	   Function<Book,String> fun=(bk)->bk.getBookName();
	   List<Book> sortedbooks= books.stream().sorted(Comparator.comparing(fun)).collect(Collectors.toList());
	//    sortedbooks.forEach(System.out::println);
	  
	  
	List<Book> bklist=  books.stream().sorted(Comparator.comparing(Book::getBookName)).collect(Collectors.toList());
	// bklist.forEach(System.out::println);
	
	
//	List<String> bknames=books.stream().map( bk->bk.getBookName()).sorted().collect(Collectors.toList());
//	bknames.forEach(System.out::println);
	
	books.stream().map( bk->bk.getBookName()).sorted().collect(Collectors.toList()).forEach(System.out::println);
	 
	System.out.println("***********************");
	
	List<Book> bklistindesc=  books.stream().sorted(Comparator.comparing(Book::getBookName).reversed()).collect(Collectors.toList());
	bklistindesc.forEach(System.out::println);
	
	}

}
