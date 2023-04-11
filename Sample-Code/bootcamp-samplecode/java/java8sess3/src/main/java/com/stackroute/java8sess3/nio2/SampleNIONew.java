package com.stackroute.java8sess3.nio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SampleNIONew {

	public static void main(String[] args) {
Path path=Paths.get("D:\\cgi-canada-2023\\niosample\\product.csv");

try {
	BufferedReader breader=Files.newBufferedReader(path);
		Stream<String> filedata=		breader.lines();
	
			filedata.filter( str->str.contains("mobile")).collect(Collectors.toList()).forEach(System.out::println);
} 



catch (IOException e) {
System.out.println(e.getMessage());
}


	}

}
