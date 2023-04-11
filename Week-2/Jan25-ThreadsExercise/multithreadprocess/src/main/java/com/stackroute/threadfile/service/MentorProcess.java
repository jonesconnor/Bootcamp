package com.stackroute.threadfile.service;

import com.stackroute.threadfile.resource.DataFile;

//use DataFile 

public class MentorProcess  extends Thread{

	DataFile datafile;
	 public MentorProcess(DataFile datafileobj)
	 {
		 this.datafile = datafileobj;
	 }
	 
	 public void run() {
		 datafile.fileOperations("read");
	 }
}
