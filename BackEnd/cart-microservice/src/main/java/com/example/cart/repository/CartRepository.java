package com.example.cart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.cart.model.Cart;

@Repository
public interface CartRepository extends MongoRepository<Cart, String>{

}
