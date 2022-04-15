package com.example.auditRabbitMqMicroservice;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
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
			System.out.println("Message " + message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
