package com.stackroute.java8sess2;

public class Book
{
	String bookName;
	int price;
	@Override
	public String toString() {
		return "Book [bookName=" + bookName + ", price=" + price + "]";
	}
	Book()
	{
		
	}
	Book(String bname,int pri)
	{
		bookName=bname;
		price=pri;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}