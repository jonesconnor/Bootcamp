package com.stackroute.javadaytwo;

import java.util.Scanner;

public class SampleArray {

	public static void main(String[] args) {
		
		Scanner scanobj=new Scanner(System.in);
		
		System.out.println("Enter the size of array");
		int size=scanobj.nextInt();
		
		int [] scores=new int[size];
		
		
		scores=new int[5];
//		
//		for(int i=0;i<size;i++)
//			scores[i]=scanobj.nextInt();
//
//		int count=0;
//		for(int score : scores)
//
//		{
//			
//			if(score>=50)
//				count++;
//		}
			
			
		//System.out.println("Count of element with >50 score is " + count)	;
		
		int[] source= {10,11};
		int[] dest= {23,56,55,45,10};
		
		System.arraycopy(source, 0, dest, 3, source.length);
		
		for(int data : dest)
		System.out.println(data);
		
		int[][] days=new int[3][ ];
		
		days[0]=new int[3];
		
		days[1]=new int[2];
		
		days[2]=new int[4];
		
		
		
	}

}
