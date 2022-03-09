package com.example.order.service;

import java.util.List;

import com.example.order.model.Order;

public interface OrderService {

	public Order placeOrder(Order order);
	public List<Order> getOrders(String userEmail);
}
