package com.stackroute.exercises;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class FileMigration {
	
    //Write here logic to read content of first file and write it in second file
    public String fileContentDuplicator(String firstFile, String secondFile) throws IOException {
    	
    	if (firstFile == null || secondFile == null) {
    		return "Given fileName to read or write is empty, null or blank space";
    	}
    	else if (firstFile.trim() == "" || secondFile.trim() == "") {
    		return "Given fileName to read or write is empty, null or blank space";
    	}
    	
    	File file1 = new File(firstFile.trim());
    	File file2 = new File(secondFile.trim());
    	
    	if ((file1.getName().endsWith(".txt")) && (file2.getName().endsWith(".txt"))) {
    		FileInputStream fis = new FileInputStream(file1);
    		BufferedInputStream bis = new BufferedInputStream(fis);
    		FileOutputStream fos = new FileOutputStream(file2);
    		BufferedOutputStream bos = new BufferedOutputStream(fos);
    		
    		StringBuilder sb = new StringBuilder();
    		
    		int data;
    		while ((data = bis.read()) != -1) {
    			bos.write(data);
    			sb.append((char) data);
    		}
    		bis.close();
    		fis.close();
    		bos.close();
    		fos.close();
    		
    		return sb.toString();
    	} else {
    		throw new IOException("Invalid file name");
    	}
    }
}
