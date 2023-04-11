package com.stackroute.threadfile.resource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//Use parameterized constructor for getting the file name. make the transaction to be synchronized



public class DataFile {

	String filename;
	
	public DataFile(String filename) {
		this.filename = filename;
	}
	
	public String getFileName() {
		return filename;
	}
    	
    public synchronized void fileOperations (String type)
    {
    	if (type.equalsIgnoreCase("read")) {
    		try {
    			FileReader fileReader = new FileReader(getFileName());
    			BufferedReader bufferedReader = new BufferedReader(fileReader);
    			String line;
    			while ((line = bufferedReader.readLine()) != null) {
    				System.out.println(line);
    			}
    			bufferedReader.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	} else if (type.equalsIgnoreCase("write")) {
    		try {
    			FileWriter fileWriter = new FileWriter(getFileName());
    			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
    			bufferedWriter.write("my assignment completed\n");
    			bufferedWriter.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }
}
