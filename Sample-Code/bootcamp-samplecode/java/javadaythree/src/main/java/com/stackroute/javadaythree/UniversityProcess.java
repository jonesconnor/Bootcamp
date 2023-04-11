package com.stackroute.javadaythree;

public class UniversityProcess {

	public static void main(String[] args) {

//		 Student student1=new Student("Tiny","Cananda",21,"ComputerScience","A+");
		 
//		 student1.display();
		 
//		 System.out.println("*******************");
		 
		 
//		 Mentor mentor1=new Mentor("ComputerScience",15);
	
//		 mentor1.printDetails();
		 
	//	 student1.displayData();
		
		 //Student student2=new Person();
		
		
		 Person person; // can not be instantiated
		 
		 
		 
		  person=new Student("Tiny","Cananda",21,"ComputerScience","A+");
		  person.display();
		  person.getData();
		  
		
		  
		  
		  System.out.println("______________________");
		 
		 //or
		  
		  person=new Mentor();
		  person.display();
		 
		 /* 
		  *  hetrogenious array
		  Person [] people= new Person[3];
		  people[0]=new Person();
		  people[1]=new Student();
		  people[2]=new Mentor();
		  */
		  
		  System.out.println("________________________");
		  
		  RankHolder rankobj=new RankHolder();
		  
		  rankobj.showAll();
		  
		  
		 
		 
		 
		 
		 
	}

}
