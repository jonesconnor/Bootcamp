package com.stackroute.concurrencynio;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class FileBackup implements Runnable
{
	String dbackup;
	
	FileBackup(String dup)
	{
		dbackup=dup;
	}

	@Override
	public void run() {
	
		System.out.println( " backup in process on " + dbackup + " time " + new Date().getSeconds());
	}
	
}

public class SampleSchedule {

	public static void main(String[] args) {
	
		ScheduledThreadPoolExecutor shobj= (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(3);		
	FileBackup fileobj=new FileBackup("31stJan");
	
shobj.scheduleWithFixedDelay(fileobj, 1, 2, TimeUnit.SECONDS);
		

	}

}
