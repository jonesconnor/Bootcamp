package com.stackroute.javaday6;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
      
        
        List<String> fruits=new LinkedList();
        
       fruits.add("Apple");
       
       fruits.add("Grape");
       
       fruits.add("Banana");
       
       fruits.add(1, "Papaya");
       
       
     
       System.out.println(fruits);
       
       Collections.sort(fruits);
       
       System.out.println(fruits);
    }
}
