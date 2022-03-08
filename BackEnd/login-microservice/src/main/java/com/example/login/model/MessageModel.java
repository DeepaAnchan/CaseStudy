package com.example.login.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Deepa Anchan
 * The message blueprint that needs to be sent to the auditing server.
 *
 */
@Document(collection = "audit_messages")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MessageModel {
	@Id
    private String id;
	
	private String message;
	
	private Date sentDate;
	
	private String producerService;
	
	
	@Override
	public String toString() {
		return "MessageModel [Id=" + id + " , message=" + message + " , date="+ sentDate+ " ,producer server:"+producerService+"]";
	}
}
