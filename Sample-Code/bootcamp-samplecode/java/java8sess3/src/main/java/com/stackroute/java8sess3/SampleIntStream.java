package com.stackroute.java8sess3;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SampleIntStream {

	public static void main(String[] args) {
	 
		
	List<Integer> numgenerate=	IntStream.iterate(0, i->i+2).limit(20).mapToObj(n->n).collect(Collectors.toList());
	
	numgenerate.forEach(System.out::println);
	}

}
