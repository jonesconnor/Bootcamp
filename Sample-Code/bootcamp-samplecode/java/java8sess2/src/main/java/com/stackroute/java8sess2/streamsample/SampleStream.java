package com.stackroute.java8sess2.streamsample;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SampleStream {

	public static void main(String[] args) {
	
	List<String> courses=Arrays.asList("datascience","mainframe","testing","programming","datastructure","science");
	
	long ans=	courses.stream().filter( str-> str.contains("data") ).count();
     System.out.println("count is " + ans);
     
	
    List<String> datalist= courses.stream().filter( str-> str.contains("data") ).collect(Collectors.toList());
     
    System.out.println(datalist); 
    
    //long count=courses.stream().limit(3).filter(str->str.startsWith("m")).count();
    
    long count=courses.stream().skip(3).filter(str->str.startsWith("m")).count();
    
    System.out.println(count);
    
    Optional<String> result= courses.parallelStream().filter( s->s.length()>5).findAny();
    
    if (result.isPresent())
    	System.out.println(result.get());
    else 
    	System.out.println("No matches found");
    
    
    
    
	}

}
