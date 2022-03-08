package com.example.login.securityConfig;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.login.constants.Constants;
import com.example.login.exception.CustomException;

/**
 * The JwtTokenRequestFilter extends the Spring Web Filter OncePerRequestFilter class. 
 * For any incoming request, this Filter class gets executed. It checks if the request has a valid JWT token. 
 * If it has a valid JWT Token, then it sets the authentication in context to specify that the current user is authenticated.
 * @author Deepa Anchan
 *
 */
@Component
public class JwtTokenRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserService customUserDetailService;

	public JwtTokenRequestFilter(JwtTokenProvider jwtTokenProvider, UserService customUserDetailService) {
		super();
		this.jwtTokenProvider = jwtTokenProvider;
		this.customUserDetailService = customUserDetailService;
	}

	public JwtTokenRequestFilter() {
		super();
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String username = null;
		String token = null;
		String header = request.getHeader(Constants.AUTHORIZATION);
		if (header != null && header.startsWith("Bearer ")) {
			token = header.replace("Bearer ", "");
			try {
				if (!jwtTokenProvider.isTokenPresentInDB(token)) {
					throw new CustomException("Invalid JWT token", HttpStatus.UNAUTHORIZED);
					
				}
				username = jwtTokenProvider.getUsernameFromToken(token);
				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

					UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
					if (jwtTokenProvider.validateToken(token, userDetails)) {
						UsernamePasswordAuthenticationToken auth = jwtTokenProvider.getAuthentication(token);
						auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(auth);
					}
				}
			} catch (Exception e) {
				throw new CustomException("Invalid JWT token", HttpStatus.UNAUTHORIZED);

			}

		}
		filterChain.doFilter(request, response);		
	}


}
