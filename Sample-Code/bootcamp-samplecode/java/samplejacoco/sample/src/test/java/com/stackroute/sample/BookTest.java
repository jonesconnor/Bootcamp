package com.stackroute.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookTest {

	
	Book book;
	
	@BeforeEach()
	public void intialize()
	{
	
		book=new Book(20,"Java8",500,5);
	}
	
	
	
	@Test
	public void checkbookId()
	{
		assertEquals(20,book.getBookId());
	}
	
	@Test
	public void checkBooknamesuccess()
	{
		assertEquals("Java8",book.getBookname());
	}
	
	@Test
	public void checktestbookid()
	{
		
		book.setBookId(1200);
		
		assertEquals(1200,book.getBookId());
		
	}
	
	@Test
	public void chekDiscount()
	{
		book.setPrice(50000);
		
		assertEquals( book.getPrice()*10/100, book.setDiscount());
		

	}
	
	@Test
	public void checknodiscount()
	{
		book.setPrice(300);
		assertEquals(0,book.setDiscount());
	}

	@Test
	public void checkbookname()
	{
		book.setBookname("Spring");
		assertEquals("Spring",book.getBookname());
		
	}
	
	@Test
	public void checkgetdiscount()
	{
		book.setPrice(200);
		book.setDiscount();
		
		assertEquals(0,book.getDiscount());
		
	}
	
	
}


