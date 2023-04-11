package com.stackroute.basics;
import java.util.Scanner;


public class RectanglePerimeter {
    public static void main(String[] args) {
        new RectanglePerimeter().getValues();
    }

    //get user input from console
    public void getValues() {
    	Scanner scan = new Scanner(System.in);
    	// System.out.println("Enter the length and breadth of the rectangle");
    	double length = Double.parseDouble(scan.next());
    	double breadth = Double.parseDouble(scan.next());
    	
    	double perimeter = perimeterCalculator(length, breadth);
    	System.out.println(perimeter);
    }

    //write logic to find perimeter of rectangle here
    public double perimeterCalculator(double length, double breadth) {
    	double perimeter = (2 * length) + (2 * breadth);
        return perimeter;
    }
}
