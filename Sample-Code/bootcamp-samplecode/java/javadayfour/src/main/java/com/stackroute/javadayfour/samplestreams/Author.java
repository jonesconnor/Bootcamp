package com.stackroute.javadayfour.samplestreams;

import java.io.Serializable;

public class Author implements Serializable{

	String authorName;
	String bookTitle;
	int age;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Author [authorName=" + authorName + ", bookTitle=" + bookTitle + ", age=" + age + "]";
	}
	public Author(String authorName, String bookTitle, int age) {
		super();
		this.authorName = authorName;
		this.bookTitle = bookTitle;
		this.age = age;
	}
	
}
