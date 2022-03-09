package com.example.order.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.model.Order;
import com.example.order.serviceImpl.OrderServiceImpl;

@RestController
@RequestMapping(value = "/order-server")
public class OrderController {

	@Autowired
	private OrderServiceImpl orderService;
	
	@PostMapping(value = "/placeOrder")
	public Order placeOrder(@RequestBody Order order, Principal user) {
		order.getCart().setUserEmail(user.getName());
		return orderService.placeOrder(order);
	}
	
	/**
	 * Won't be used in the FrontEnd.
	 * @param user
	 * @return
	 */
	@GetMapping(value = "/getOrder")
	public List<Order> placeOrder(Principal user) {
		return orderService.getOrders(user.getName());
	}
}
