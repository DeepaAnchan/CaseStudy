package com.example.cart.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cart.model.Cart;
import com.example.cart.repository.CartRepository;
import com.example.cart.service.CartService;
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public Cart saveCart(Cart cart) {
		return cartRepository.save(cart);
	}

	@Override
	public Cart getCartByUser(String userEmail) {
		Optional<Cart> cart = cartRepository.findById(userEmail);
		if (cart != null && cart.isPresent()) {
			return cart.get();
		}
		return null;
	}

	@Override
	public Cart updateCart(Cart cart) {
		return cartRepository.save(cart);
	}
	
	@Override
	public void deleteCartOfUser(String userEmail) {
		Cart cart = getCartByUser(userEmail);
		if (cart != null) {
			cartRepository.delete(cart);
		}
	}

}
