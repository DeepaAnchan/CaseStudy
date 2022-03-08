package com.example.login.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.login.exception.CustomException;
import com.example.login.messageSender.MessageSender;
import com.example.login.model.AuthTokenResponse;
import com.example.login.model.JwtToken;
import com.example.login.model.Role;
import com.example.login.model.User;
import com.example.login.repository.JwtTokenRepository;
import com.example.login.repository.UserRepository;
import com.example.login.securityConfig.JwtTokenProvider;
import com.example.login.service.LoginService;
import com.example.login.util.LogUtil;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private JwtTokenRepository jwtTokenRepository;

	@Autowired
	private LogUtil logUtil;	

	@Autowired
	private MessageSender messageSender;	
	
	@Override
	public User saveUser(User user) {
		logUtil.logInfo("LoginServiceImpl: Creating User");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public AuthTokenResponse login(String username, String password) {
		logUtil.logInfo("LoginServiceImpl: Logging User");
		try {
			authenticate(username, password);

			List<User> userFromDb =  userRepository.findByEmail(username);
			if (userFromDb == null || userFromDb.isEmpty() || userFromDb.get(0).getRole() == null
					|| userFromDb.get(0).getRole().isEmpty()) {
				logUtil.logError("LoginServiceImpl: User is not present in the DB.");
				throw new CustomException("User is not present in the DB.", HttpStatus.NOT_FOUND);
			}
			User user = userFromDb.get(0);

			String token = jwtTokenProvider.createToken(username,
							user.getRole().stream().map((Role role) -> "ROLE_" + role.getRole())
									.filter(Objects::nonNull)
									.collect(Collectors.toList()));
			JwtToken jwtToken = new JwtToken();
			jwtToken.setToken(token);
			jwtToken.setCreatedDate(new Date());
			jwtTokenRepository.save(jwtToken);
			return new AuthTokenResponse(token, username, user.getUserId());

		} catch (AuthenticationException e) {
			messageSender.send("Login Failure");
			logUtil.logError("LoginServiceImpl: Invalid username or password.");
			throw new CustomException("Invalid username or password.", HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			messageSender.send("Login Failure");
			logUtil.logError("LoginServiceImpl: "+e.getMessage());
			throw new CustomException(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	

    @Override
	public boolean logout(String token) {
		logUtil.logInfo("LoginServiceImpl: logout");
		try {
			List<JwtToken> jwtTokenList = jwtTokenRepository.findByToken(token);
			if(jwtTokenList!= null && !(jwtTokenList.isEmpty())) {
				String jwtTokenDbId = jwtTokenList.get(0).getTokenId();
				jwtTokenRepository.deleteById(jwtTokenDbId);
			}
		} catch (Exception e) {
			logUtil.logError("LoginServiceImpl: "+e.getMessage());
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return true;
	}

	/** 
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	private void authenticate(String username, String password) throws Exception {
		logUtil.logInfo("LoginServiceImpl: authenticate");
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			logUtil.logError("LoginServiceImpl: "+e.getMessage());
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			logUtil.logError("LoginServiceImpl: "+e.getMessage());
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
