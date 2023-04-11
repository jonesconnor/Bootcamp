package com.stackroute.consumerservice.rabbitservice;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.stackroute.consumerservice.model.Patient;

@Service
public class ConsumerService  implements RabbitListenerConfigurer{
 

	
	Patient patient;
	@Override
	public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
		// TODO Auto-generated method stub
		
	}

	@RabbitListener(queues="${spring.rabbitmq.queue}")
	public void receivedMessage(Patient pat)
	{
		this.patient=pat;
		//return this.patient;
		
		
		System.out.println(patient);
		//repo.save(patient);
	}
	
	
	
}
