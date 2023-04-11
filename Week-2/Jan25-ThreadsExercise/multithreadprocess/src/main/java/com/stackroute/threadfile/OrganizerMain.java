package com.stackroute.threadfile;

import com.stackroute.threadfile.resource.DataFile;
import com.stackroute.threadfile.service.MentorProcess;
import com.stackroute.threadfile.service.StudentProcess;

import java.util.Scanner;

//Get options for reading or writing. 
public class OrganizerMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int option = 0;
		
		DataFile dataFile = new DataFile("myfile.txt");
        System.out.println("Select an option:");
        System.out.println("1. Read from file (Mentor)");
        System.out.println("2. Write to file (Student)");
        System.out.println("3. Exit Program");

        // Get user input
        option = Integer.parseInt(sc.nextLine());

        if (option == 1) {
            MentorProcess mentorThread = new MentorProcess(dataFile);
            mentorThread.start();
        } else if (option == 2) {
            StudentProcess studentThread = new StudentProcess(dataFile);
            studentThread.start();
        }
		
		sc.close();
	}

}
