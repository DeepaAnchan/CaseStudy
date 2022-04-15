package com.example.login.messageSender;

import java.util.Date;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.login.model.MessageModel;
import com.example.login.util.LogUtil;
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
	private LogUtil logUtil;

    @Autowired
    private Queue queue;

    public void send(String message) {
    	ObjectMapper mapper = new ObjectMapper();
		MessageModel messageModel = new MessageModel();
		messageModel.setMessage(message);
		messageModel.setSentDate(new Date());
		messageModel.setProducerService("Login Service");

		try {
			String messageAsJson = mapper.writeValueAsString(messageModel);		
			logUtil.logInfo("Sending Message:"+messageAsJson);
			rabbitTemplate.convertAndSend(this.queue.getName(), messageAsJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    }


}