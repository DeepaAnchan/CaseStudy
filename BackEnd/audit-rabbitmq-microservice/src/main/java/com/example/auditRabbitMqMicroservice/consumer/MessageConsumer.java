package com.example.auditRabbitMqMicroservice.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.example.auditRabbitMqMicroservice.model.MessageModel;
import com.example.auditRabbitMqMicroservice.repository.MessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;



@Component
public class MessageConsumer {

	@Autowired
	private MessageRepository messageRepository;
	
	@RabbitListener(queues = { "${queue.name}" })
	public void receive(@Payload String message) {
		ObjectMapper mapper = new ObjectMapper();		
		try {
			MessageModel messageModel = mapper.readValue(message, MessageModel.class);
			messageRepository.save(messageModel);
			System.out.println("Message Received:"+messageModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
