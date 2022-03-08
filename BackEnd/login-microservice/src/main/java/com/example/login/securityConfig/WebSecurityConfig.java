package com.example.login.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
	
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
	@Autowired
	private UserService customUserDetailService;
    
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		httpSecurity.csrf().disable();
		
		httpSecurity.authorizeRequests()
		// don't authenticate these particular requests
		.antMatchers("/login-server/createUser",
					"/login-server/login",
					"/actuator",
					"/actuator/**").permitAll()
		// all other requests need to be authenticated
		.anyRequest().authenticated();

		//unauthenticated requests are handled in this handler.
		httpSecurity.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
		// make sure we use stateless session; session won't be used to store user's state.
		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
		httpSecurity.apply(new JwtTokenRequestFilterConfigurer(jwtTokenProvider, customUserDetailService));

	}

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

}
