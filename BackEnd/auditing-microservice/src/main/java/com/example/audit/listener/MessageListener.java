package com.example.audit.listener;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.audit.model.MessageModel;
import com.example.audit.repository.MessageAuditRepository;
import com.example.audit.util.LogUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MessageListener {
	
	@Autowired
	private LogUtil logUtil;
	
	@Autowired
	private MessageAuditRepository messageRepository;
	
	@JmsListener(destination = "${message.queue.name}")
	public void Receive(String message) {		
		MessageModel messageModel;
		ObjectMapper mapper = new ObjectMapper();		
		try {
			messageModel = mapper.readValue(message, MessageModel.class);
			messageRepository.save(messageModel);
			logUtil.logInfo("Message Received:"+messageModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
