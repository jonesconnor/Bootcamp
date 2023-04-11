package com.stackroute;

public class JunitDemo {

    public String concateTwoStringInUpperCase(String str1 , String str2) {
        if(str1==null|| str2==null){
            return "String cannot be null";
        }
        return str1.concat(str2).toUpperCase();
    }
     public int multiply(String a , String b){
       int num1 = Integer.parseInt(a);
       int num2 = Integer.parseInt(b);
       if(num1<0){
           throw new IllegalArgumentException("Num1 cannot be less then 0");
       }
       return num1 *num2;
     }

      public  boolean isEven(int number){
        return  number%2==0;
      }

      static  int array[] = {1,2,3,5,6};

}
