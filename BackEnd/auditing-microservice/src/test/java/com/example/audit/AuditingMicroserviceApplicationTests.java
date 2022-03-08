package com.example.audit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuditingMicroserviceApplicationTests {

	@Autowired
	private MessageSender messageSender;
	
	@Test
	public void testSendAndReceive() {
		messageSender.send("Hello");
	}

}
