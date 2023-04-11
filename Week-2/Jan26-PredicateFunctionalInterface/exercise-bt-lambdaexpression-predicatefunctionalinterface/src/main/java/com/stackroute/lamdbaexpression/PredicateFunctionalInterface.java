package com.stackroute.lamdbaexpression;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicateFunctionalInterface {
    //write logic to find the values that starts with letter I in the given list
    public List<String> findPattern(List<String> list) {
    	
    	List<String> filteredList = new ArrayList<>();
    	
    	Predicate<String> filterI = (val)->val.startsWith("I");
    	
    	for (String item : list) {
    		if ((filterI).test(item) && !filteredList.contains(item)) {
    			filteredList.add(item);
    		}
    	}
    	
        return filteredList;
    }
}
