package com.stackroute.sample;

public class Book {
	
	int bookId;
	String bookname;
	int price;
	int discount;
	public Book(int bookId, String bookname, int price, int discount) {
		super();
		this.bookId = bookId;
		this.bookname = bookname;
		this.price = price;
		this.discount = discount;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDiscount() {
		return discount;
	}
	public int setDiscount() {
		if (this.getPrice()>2000)
		 	discount=this.getPrice()*10/100;
		else
			discount=0;
		
		return discount;
	}
	

}
