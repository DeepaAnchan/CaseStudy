package com.example.login.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.login.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	public List<User> findByName(String name);
	public List<User> findByEmail(String email);

}
