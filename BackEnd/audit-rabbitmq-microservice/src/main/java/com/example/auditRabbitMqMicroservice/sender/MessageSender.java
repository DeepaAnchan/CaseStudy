package com.example.auditRabbitMqMicroservice.sender;

import java.util.Date;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.auditRabbitMqMicroservice.model.MessageModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MessageSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    public void send(String message) {
    	ObjectMapper mapper = new ObjectMapper();
		MessageModel messageModel = new MessageModel();
		messageModel.setMessage(message);
		messageModel.setSentDate(new Date());
		messageModel.setProducerService("Self");

		try {
			String messageAsJson = mapper.writeValueAsString(messageModel);	
			System.out.println("Sending Message:"+messageAsJson);
			rabbitTemplate.convertAndSend(this.queue.getName(), messageAsJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    }


}
