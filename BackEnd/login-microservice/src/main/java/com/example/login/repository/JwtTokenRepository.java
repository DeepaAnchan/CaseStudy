package com.example.login.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.login.model.JwtToken;

@Repository
public interface JwtTokenRepository extends MongoRepository<JwtToken,String>{
	List<JwtToken> findByToken(String token);
}
