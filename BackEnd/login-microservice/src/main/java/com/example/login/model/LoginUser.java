package com.example.login.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * 
 * @author Deepa Anchan
 * The class type of the credentials given when logging in.
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {
	private String username;
    private String password;
}
