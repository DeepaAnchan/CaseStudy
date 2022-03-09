package com.example.order.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.order.model.Order;
import com.example.order.repository.OrderRepository;
import com.example.order.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Order placeOrder(Order order) {
		return orderRepository.save(order);
	}
	
	@Override
	public List<Order> getOrders(String userEmail) {
		return orderRepository.findOrdersByUserEmail(userEmail);
	}

}
