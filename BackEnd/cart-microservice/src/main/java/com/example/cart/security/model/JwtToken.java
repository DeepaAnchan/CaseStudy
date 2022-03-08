package com.example.cart.security.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * 
 * @author Deepa Anchan
 * A collection 'jwt-token' will be created.
 *
 */
@Document(collection = "jwt-token")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class JwtToken {
	@Id
	private String tokenId;
	private String token;
	private Date createdDate;

}