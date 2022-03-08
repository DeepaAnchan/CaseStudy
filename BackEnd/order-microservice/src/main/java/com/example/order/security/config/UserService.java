package com.example.order.security.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.order.exception.CustomException;
import com.example.order.security.model.MongoUserDetails;
import com.example.order.security.model.Role;
import com.example.order.security.model.User;
import com.example.order.security.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		int count = 0;
		List<User> userFromDb =  userRepository.findByEmail(email);
		if (userFromDb == null || userFromDb.isEmpty() || userFromDb.get(0).getRole() == null
				|| userFromDb.get(0).getRole().isEmpty()) {
			throw new CustomException("Invalid Credentials / User is not present in the DB.", HttpStatus.NOT_FOUND);
		}
		User user = userFromDb.get(0);
		String[] authorities = new String[user.getRole().size()];
		for (Role role : user.getRole()) {
			authorities[count] = "ROLE_" + role.getRole();
			count++;
		}
		MongoUserDetails userDetails = new MongoUserDetails(user.getUserId(), user.getEmail(), user.getPassword(), authorities,
				user.getActive(), user.isLocked(), user.isExpired(), user.isEnabled());
		return userDetails;

	}

}
