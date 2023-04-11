package com.stackroute.java8sess3.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class SampleNIO {

	public static void main(String[] args) {

		Path path=Paths.get("D:\\cgi-canada-2023\\niosample\\someinput.txt");
		
		if (Files.isWritable(path))
			System.out.println("File has write permission");
		else
			System.out.println("File do not have write permission");
		
		
		Path source=Paths.get("D:\\cgi-canada-2023\\niosample\\someinput.txt");
		Path destin=Paths.get("D:\\cgi-canada-2023\\backup\\test.txt");
		
		
		
		try {
			Files.copy(source,destin,StandardCopyOption.REPLACE_EXISTING);
		} 
		
		catch (IOException e) {
		System.out.println(e.getMessage());
		}
		
		try {
			Files.delete(source);
			System.out.println("source file is deleted");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		
	}

}
