package com.example.product.security.config;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.product.constants.Constants;
import com.example.product.security.model.JwtToken;
import com.example.product.security.repository.JwtTokenRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * This class is responsible for performing JWT operations like creation and validation of JWT Token. 
 * @author Deepa Anchan
 *
 */
@Component
public class JwtTokenProvider {

	@Autowired
	private UserService customUserDetailService;

	@Autowired
	private JwtTokenRepository jwtTokenRepository;

	public String createToken(String username, List<String> roles) {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put(Constants.AUTH, roles);
		Date now = new Date();
		Date validity = new Date(now.getTime() + Constants.validityInMilliseconds);
		return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, Constants.SECRET_KEY).compact();

	}

	public String resolveToken(String bearerToken) {
		String token = null;
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			token = bearerToken.replace("Bearer ", "");
		}
		return token;
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(Constants.SECRET_KEY).parseClaimsJws(token).getBody();
	}


	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}


	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public boolean isTokenPresentInDB(String token) {
		List<JwtToken> jwtTokenList = jwtTokenRepository.findByToken(token);
		if(jwtTokenList!= null && !(jwtTokenList.isEmpty())) {
			return true;
		}
		return false;
	}

	public boolean validateToken(String token, UserDetails userDetails) throws JwtException, IllegalArgumentException {
		String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public UsernamePasswordAuthenticationToken getAuthentication(String token) {
		UserDetails userDetails = customUserDetailService.loadUserByUsername(getUsernameFromToken(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

}
