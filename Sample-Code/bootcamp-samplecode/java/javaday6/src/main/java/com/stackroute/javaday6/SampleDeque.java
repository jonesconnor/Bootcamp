package com.stackroute.javaday6;

import java.util.ArrayDeque;
import java.util.Deque;

public class SampleDeque {

	public static void main(String[] args) {
 
Deque dqueobj=new ArrayDeque();

dqueobj.add("Red");
dqueobj.addLast("blue");
dqueobj.addFirst("green");

System.out.println(dqueobj);

System.out.println(dqueobj.peekFirst());

System.out.println(dqueobj);
	}

}
