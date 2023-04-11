package com.stackroute.concurrencynio;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

public class SampleWatchService {

	
	WatchService watchservice;
	
	Path path=Paths.get("D:\\cgi-canada-2023\\niosample");
	
	void initializeService()
	{
		
		  try {
			watchservice=FileSystems.getDefault().newWatchService();
			
		path.register(watchservice,StandardWatchEventKinds.ENTRY_CREATE,StandardWatchEventKinds.ENTRY_DELETE,StandardWatchEventKinds.ENTRY_MODIFY);
		
		  
		  } catch (IOException e) {
			System.out.println(e.getMessage());
		}
		  
		  
	}
	void monitorFolder()
	{
		
		WatchKey wkey=null;
		
		while(true)
		{
			     try {
				
			    	 wkey= watchservice.take();
			   List<WatchEvent<?>> events=wkey.pollEvents();
					
					   for(WatchEvent evt : events)
					   {
						   	Kind kindobj=evt.kind();
						   	System.out.println(evt.context() + " is "  + kindobj);
					   }
						   
					   
					   
				} 
			     
			     catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			boolean reset=wkey.reset();
			
			 if(!reset)
				 break;
			
			
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {

		
		SampleWatchService sample=new SampleWatchService();
		
	sample.initializeService();
	sample.monitorFolder();
	}

}
