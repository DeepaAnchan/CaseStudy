package com.example.login.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * 
 * @author Deepa Anchan
 * The blueprint of the authentication response consisting of the token and user data
 *  which is returned after logging in.
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthTokenResponse {
	private String token;
	private String username;
	private String userId;
}
