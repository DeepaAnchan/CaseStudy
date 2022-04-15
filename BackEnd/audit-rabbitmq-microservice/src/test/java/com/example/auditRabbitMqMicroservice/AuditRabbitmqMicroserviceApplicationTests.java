package com.example.auditRabbitMqMicroservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuditRabbitmqMicroserviceApplicationTests {

	@Autowired
	private QueueSender messageSender;

	@Test
	public void testSendAndReceive() {
		messageSender.send("Hello");
	}

}
