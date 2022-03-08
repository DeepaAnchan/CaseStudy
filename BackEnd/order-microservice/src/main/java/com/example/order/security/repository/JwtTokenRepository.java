package com.example.order.security.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.order.security.model.JwtToken;


@Repository
public interface JwtTokenRepository extends MongoRepository<JwtToken,String>{
	List<JwtToken> findByToken(String token);
}
