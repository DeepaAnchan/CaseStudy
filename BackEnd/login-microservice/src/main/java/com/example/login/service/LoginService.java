package com.example.login.service;

import com.example.login.model.AuthTokenResponse;
import com.example.login.model.User;

public interface LoginService {

	public User saveUser(User user);
	public AuthTokenResponse login(String username, String password);
	public boolean logout(String token);
}
