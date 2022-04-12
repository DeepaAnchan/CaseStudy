package com.example.cart.security.config;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * This class will extend Spring's AuthenticationEntryPoint class and override its method to commence.
 * It rejects every unauthenticated request and sends error code 401.
 * @author Deepa Anchan
 *
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = 3510126320883351834L;

	@Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

		// response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT
		// token.");
		Map<String, String> rspMap = new HashMap<>();
		rspMap.put("resp_msg", authException.getMessage());

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(rspMap));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			throw new ServletException();
		}

	}
}