package com.example.login.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.constants.Constants;
import com.example.login.messageSender.MessageSender;
import com.example.login.model.AuthTokenResponse;
import com.example.login.model.LoginUser;
import com.example.login.model.User;
import com.example.login.securityConfig.JwtTokenProvider;
import com.example.login.serviceImpl.LoginServiceImpl;



@RestController
@RequestMapping("/login-server")
public class LoginController {

	@Autowired
	private LoginServiceImpl loginServiceImpl;	
	
	@Autowired
	private MessageSender messageSender;	
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
    @PostMapping("/createUser")
	public void createUser(@RequestBody User user) {
    	System.out.println("LoginController: Creating user");
    	User savedUser = loginServiceImpl.saveUser(user);
    	System.out.println("LoginController: User:"+savedUser);
	}

    
	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<AuthTokenResponse> login(@RequestBody LoginUser loginUser) {
    	System.out.println("LoginController: Logging In");
		AuthTokenResponse authTokenResponse = loginServiceImpl.login(loginUser.getUsername(), loginUser.getPassword());
		
		HttpHeaders headers = new HttpHeaders();
		List<String> headerlist = new ArrayList<>();
		List<String> exposeList = new ArrayList<>();
		headerlist.add("Content-Type");
		headerlist.add(" Accept");
		headerlist.add("X-Requested-With");
		headerlist.add("Authorization");
		headers.setAccessControlAllowHeaders(headerlist);
		exposeList.add("Authorization");
		headers.setAccessControlExposeHeaders(exposeList);
		headers.set("Authorization", authTokenResponse.getToken());

		messageSender.send("Login Success");
		return new ResponseEntity<AuthTokenResponse>(authTokenResponse, headers, HttpStatus.CREATED);
	}
	
	@PostMapping("/logout")
    @ResponseBody
	public ResponseEntity<?> logout(@RequestHeader(value = Constants.AUTHORIZATION) String bearertoken) {
    	System.out.println("LoginController: Logging Out");
		HttpHeaders headers = new HttpHeaders();
		String token = jwtTokenProvider.resolveToken(bearertoken);
		if (token!=null && !token.isEmpty() && loginServiceImpl.logout(token)) {
			headers.remove(Constants.AUTHORIZATION);
			return new ResponseEntity<>("Logout Success", headers, HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Logout Not Done", headers, HttpStatus.NOT_MODIFIED);
	}
}
