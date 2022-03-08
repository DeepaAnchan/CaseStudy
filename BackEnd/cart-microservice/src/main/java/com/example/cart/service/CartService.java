package com.example.cart.service;

import com.example.cart.model.Cart;

public interface CartService {
	public Cart saveCart(Cart cart);
	public Cart getCartByUser(String userEmail);
	public Cart updateCart(Cart cart);
	public void deleteCartOfUser(String userEmail);
}
