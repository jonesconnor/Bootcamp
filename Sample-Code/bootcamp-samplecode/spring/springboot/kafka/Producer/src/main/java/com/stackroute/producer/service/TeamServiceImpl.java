package com.stackroute.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.stackroute.producer.model.Team;

@Service
public class TeamServiceImpl implements TeamService {
	
	@Autowired 
	KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private Gson gson;
	
    private static final String TOPIC = "NewTopic";
    
	@Override
	public Team addTeam(Team team) {
		//repo.save .....
		
		
		kafkaTemplate.send(TOPIC, gson.toJson(team));
		
		return team;
	}

}
