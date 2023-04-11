package com.stackroute.consumer;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.google.gson.Gson;

@RestController
public class TeamController {
	
	@Autowired
	
	Gson gson;
	
	@KafkaListener(topics = "NewTopic",groupId = "group_id")
	public void consume(String team)
	{

	///teamobj=gson.fromJson(team,Team.class);
//	 System.out.println("received message = " + teamobj.getTeamname() );
		
		System.out.println("received message = " + team);
	 
	}
	
	
	// end points for storing....
	
}
