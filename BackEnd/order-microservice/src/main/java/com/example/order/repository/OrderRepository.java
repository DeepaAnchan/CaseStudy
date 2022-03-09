package com.example.order.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.order.model.Order;
@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
	
	@Query("{'cart._id':?0}")
    List<Order> findOrdersByUserEmail(String userEmail);
}
