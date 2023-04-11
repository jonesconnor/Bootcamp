package com.stackroute.consumerservice;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfiguration {

	
	@Value("${spring.rabbitmq.host}")
	String host;
	
	@Value("${spring.rabbitmq.username}")
	String uname;
	
	
	
	@Value("${spring.rabbitmq.password}")
	String password;
	
	@Value("${spring.rabbitmq.exchange}")
	String exchange;
	
	@Value("${spring.rabbitmq.routingkey}")
	String rkey;
	
	@Value("${spring.rabbitmq.queue}")
	String queue;
	
	@Bean
	Queue getqueue()
	{
		return new Queue(queue,true);
	}
	
	@Bean 
	Exchange getExchange()
	{
		return ExchangeBuilder.directExchange(exchange).durable(true).build();
	}
	
	@Bean
	Binding getBindig()
	{
		return BindingBuilder.bind(getqueue()).to(getExchange()).with(rkey).noargs();
	}
	
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
