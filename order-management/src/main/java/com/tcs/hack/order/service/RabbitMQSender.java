package com.tcs.hack.order.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tcs.hack.order.model.OrderLIDTO;

@Service
public class RabbitMQSender {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${tcs.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${tcs.rabbitmq.routingkey}")
	private String routingkey;	
	String kafkaTopic = "tcs_hsck_topic";
	
	  public void send(OrderLIDTO order) {
          
          String skuwithItem= order.getSkuId()+"-"+order.getItemQty();
          System.out.println("Send msg = " + exchange + skuwithItem);
          amqpTemplate.convertAndSend(exchange, routingkey, skuwithItem);
          System.out.println("Send msg = " + exchange + order.toString());
       
   }

}