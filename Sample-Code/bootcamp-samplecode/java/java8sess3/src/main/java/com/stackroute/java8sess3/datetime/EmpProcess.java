package com.stackroute.java8sess3.datetime;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class EmpProcess {

	public static void main(String[] args) {
		
		DateTimeFormatter dtpattern=DateTimeFormatter.ofPattern("yyyy/MM/dd");
		
		List<Employee> employees=Arrays.asList(new Employee("John", LocalDate.parse("2002/12/29",dtpattern),LocalDate.of(2022,05,06)),
								new Employee("Vincent", LocalDate.parse("1989/12/12",dtpattern),LocalDate.of(2007,05,06)),
											new Employee("Shanon", LocalDate.parse("1965/05/02",dtpattern),LocalDate.of(2021,12,18)));
		
		LocalDate today=LocalDate.now();
		
	Optional<Employee> optempt=employees.stream().min(Comparator.comparing(Employee::getDoj));
	
	
	Employee senior=optempt.get();
	
	System.out.println(senior);
		Period datediff=Period.between(senior.getDoj(), today );
		
		System.out.println(datediff);
		
		for(Employee emp : employees)
		{
			Period agecalcu=Period.between(emp.getDob(),today);
			
			if(agecalcu.getYears()==57 || agecalcu.getYears()==58)
			{
				System.out.println(" Expecting retirement  " + emp);
			}
			
		}
			
			
			
	}

}
