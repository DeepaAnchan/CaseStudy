package com.example.cart.security.config;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


public class JwtTokenRequestFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private JwtTokenProvider jwtTokenProvider;
	private UserService customUserDetailService;

    public JwtTokenRequestFilterConfigurer(JwtTokenProvider jwtTokenProvider, UserService customUserDetailService) {
        this.jwtTokenProvider = jwtTokenProvider;
		this.customUserDetailService = customUserDetailService;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        JwtTokenRequestFilter customFilter = new JwtTokenRequestFilter(jwtTokenProvider, customUserDetailService);
        httpSecurity.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
