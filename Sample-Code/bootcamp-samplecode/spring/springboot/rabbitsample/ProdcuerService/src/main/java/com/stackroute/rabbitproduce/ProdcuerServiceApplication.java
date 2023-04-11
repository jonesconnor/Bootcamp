package com.stackroute.rabbitproduce;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProdcuerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdcuerServiceApplication.class, args);
	}
	
	
	@Value("${spring.rabbitmq.host}")
	String host;
	
	@Value("${spring.rabbitmq.username}")
	String uname;
	
	
	
	@Value("${spring.rabbitmq.password}")
	String password;
	
	@Bean
	ConnectionFactory getconnection()
	{
		CachingConnectionFactory  cachfactory=new CachingConnectionFactory(host);
		cachfactory.setUsername(uname);
		cachfactory.setPassword(password);
		return cachfactory;
	}

  	@Bean
  	public MessageConverter  jsonmessageConvertor()
  	{
  		
  		return new Jackson2JsonMessageConverter();
  		
  	}
	
  	@Bean
  	public RabbitTemplate gettemplate(ConnectionFactory fact)
  	{
  		RabbitTemplate rtemp=new RabbitTemplate(fact);
  		rtemp.setMessageConverter(jsonmessageConvertor());
  		return rtemp;
  		
  	}
	
	
}
