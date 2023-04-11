package com.stackroute.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

/*
 * This class contains methods for adding Fruits to a List and searching the fruits from the List
 */
public class FruitsListService {

    public static List<String> addFruitsToList(String fruitNames) {
    	
    	// validate input
    	if (fruitNames == null || fruitNames.equals("")) {
    		return new ArrayList<String>();
    	}
    	
    	// Split string into list and create a LinkedHashSet to preserve insertion order while handling duplicates
    	List<String> fruitList = new ArrayList<String>(Arrays.asList(fruitNames.split(",")));
    	LinkedHashSet<String> noDuplicateFruit = new LinkedHashSet<>();
    	
    	// Changing fruits to all lowercase will ensure there are no duplicates
    	for (String fruit : fruitList) {
    		noDuplicateFruit.add(fruit.toLowerCase());
    	}
    	
    	List<String> result = new ArrayList<String>(noDuplicateFruit);
    	
    	// Iterate through result list and compare each element with the element at the same index in fruitList.
    	// If they're the same element, replace it with the one in fruitList so that the same capitalizations of the fruit is in the output.
    	for (int i = 0; i < result.size(); i++) {
    		String element = result.get(i);
    		if (element.equalsIgnoreCase(fruitList.get(i))) {
    			result.set(i, fruitList.get(i));
    		}
    	}
    	
    	return result;
    
    }

    public static int searchFruitInList(List<String> fruitList, String searchFruit) {
    	for (int i = 0; i < fruitList.size(); i++) {
    		String fruit = fruitList.get(i);
    		if (searchFruit.equals(fruit)) {
    			return i;
    		}
    	}
        return -1;
    }

    public static int searchFruitInListIgnoreCase(List<String> fruitList, String searchFruit) {
    	for (int i = 0; i < fruitList.size(); i++) {
    		String fruit = fruitList.get(i);
    		if (searchFruit.equalsIgnoreCase(fruit)) {
    			return i;
    		}
    	}
        return -1;
    }
}