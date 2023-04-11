package com.stackroute.rabbitproduce.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stackroute.rabbitproduce.model.Patient;

@Service
public class PatientServiceImpl implements PatientService {

	RabbitTemplate rtemplate;
	
	@Autowired
	public PatientServiceImpl(RabbitTemplate rtemp)
	{
		this.rtemplate=rtemp;
	}
	
	@Value("${spring.rabbitmq.exchange}")
	String exchange;
	
	@Value("${spring.rabbitmq.routingkey}")
	String rkey;
	
	@Override
	public Patient addPatient(Patient patient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void send(Patient patient) {
		 
		rtemplate.convertAndSend(exchange,rkey,patient);
		
		
	}

}
