package com.example.cart.messageSender;

import java.util.Date;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.cart.model.MessageModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Deepa Anchan
 * This class is meant for sending message to rabbitMq queue.
 *
 */
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
		messageModel.setProducerService("Cart Service");

		try {
			String messageAsJson = mapper.writeValueAsString(messageModel);		
			System.out.println("Sending Message:"+messageAsJson);
			rabbitTemplate.convertAndSend(this.queue.getName(), messageAsJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    }


}