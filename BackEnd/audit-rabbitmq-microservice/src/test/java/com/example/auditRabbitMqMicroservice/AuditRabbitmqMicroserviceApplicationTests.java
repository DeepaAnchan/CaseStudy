package com.example.auditRabbitMqMicroservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.auditRabbitMqMicroservice.sender.MessageSender;

@SpringBootTest
class AuditRabbitmqMicroserviceApplicationTests {

	@Autowired
	private MessageSender messageSender;

	@Test
	public void testSendAndReceive() {
		messageSender.send("Hello");
	}

}
