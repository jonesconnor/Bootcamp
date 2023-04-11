## Problem Statement: Create ArrayList and Search elements in the List

#### Complete the class `FruitsListService` as per the below requirement
   
-   Define the below static methods in class `FruitsListService`:
    
        +addFruitsToList(String) : List<String>      
            - Should take a String input containing names of fruits separated by comma(see below), and return a List of the Fruit names
                "apple,mango,Cherry"
            - Should not add duplicate fruits to the list (Same fruit name in upper/lower/mixed should be also considered duplicates)
    
        +searchFruitInList(List<String>, String) : int
            - Should take a fruit list and fruit name to be searched as parameters
            - Should return the index of the fruit in the list, if it's found
            - Should return -1, if the fruit is not found
            - Should do a "case-sensitive" search   
                        
        +searchFruitInListIgnoreCase(List<String>, String) : int
            - Should take a fruit list and fruit name to be searched as parameters
            - Should return the index of the fruit in the list, if it's found
            - Should return -1, if the fruit is not found
            - Should do a "case-insensitive" search       
            

## Instructions
- Do not change the provided class/method names unless instructed
- Ensure your code compiles without any errors/warning/deprecations 
- Follow best practices while coding