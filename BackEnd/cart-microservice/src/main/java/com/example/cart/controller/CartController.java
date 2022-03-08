package com.example.cart.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cart.model.Cart;
import com.example.cart.serviceImpl.CartServiceImpl;

@RestController
@RequestMapping(value = "/cart-server")
public class CartController {
	@Autowired
	private CartServiceImpl cartService;
	
	@PostMapping(value = "/saveCart")
	public Cart saveCart( @RequestBody Cart cart, Principal user ) {
		cart.setUserEmail(user.getName());
		return cartService.saveCart(cart);
		
	}
	
	@GetMapping(value = "/getCartById")
	public Cart getCartByUserId(Principal user) {
		Cart cart = cartService.getCartByUser(user.getName());
		if(cart!= null) {
			return cart;
		}
		return null;
	}
	
	@PutMapping(value = "/updateCart")
	public Cart updateCart(@RequestBody Cart updatedCart, Principal user) {
		updatedCart.setUserEmail(user.getName());
		return cartService.updateCart(updatedCart);		
	}
	
	@DeleteMapping(value = "/deleteCart")
	public void deleteCart(Principal user) {
		 cartService.deleteCartOfUser(user.getName());		
	}
}
