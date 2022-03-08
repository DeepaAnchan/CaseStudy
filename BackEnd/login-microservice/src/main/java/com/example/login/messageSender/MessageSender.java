package com.example.login.messageSender;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.example.login.model.MessageModel;
import com.example.login.util.LogUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Deepa Anchan
 * This class is meant for sending message to activemq queue.
 *
 */
@Component
public class MessageSender {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Value(value = "${message.queue.name}")
	private String queueName;
	
	@Autowired
	private LogUtil logUtil;
	
	public void send(String message) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		MessageModel messageModel = new MessageModel();
		messageModel.setMessage(message);
		messageModel.setSentDate(new Date());
		messageModel.setProducerService("Login Service");
		
		try {
			String messageAsJson = mapper.writeValueAsString(messageModel);	
			jmsTemplate.convertAndSend(queueName, messageAsJson);		
			logUtil.logInfo("Sending Message:"+messageAsJson);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}
	}
}
