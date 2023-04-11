package com.stackroute.threadfile.service;

import com.stackroute.threadfile.resource.DataFile;

public class StudentProcess extends Thread {
	

	DataFile datafile;
	public StudentProcess(DataFile datafileobj)
	{
		this.datafile = datafileobj;
	}
	
	public void run() {
		datafile.fileOperations("write");
	}

}
