package com.stackroute.concurrencynio;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SampleWalk {

	public static void main(String[] args) {

		Path path=Paths.get("D:\\cgi-canada-2023\\reports");
		
		try(Stream<Path> streamdata = Files.walk(path))
		{
			
			 streamdata.map(fileobj->fileobj.toString())
			 .filter( fname->fname.endsWith("txt"))
			 .collect(Collectors.toList()).forEach(System.out::println);
			
		}
		
		catch(Exception exce)
		{
			
		}
		
	}

}
