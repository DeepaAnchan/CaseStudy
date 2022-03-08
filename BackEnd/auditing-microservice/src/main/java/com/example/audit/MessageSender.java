package com.example.audit;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.example.audit.model.MessageModel;
import com.example.audit.util.LogUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Deepa Anchan
 * This class is meant for testing activemq.
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
		messageModel.setProducerService("Self");
		
		try {
			String messageAsJson = mapper.writeValueAsString(messageModel);	
			jmsTemplate.convertAndSend(queueName, messageAsJson);		
			logUtil.logInfo("Sending Message:"+messageAsJson);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}
	}
}
