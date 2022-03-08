package com.example.cart.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(value = "cart")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Cart {
	@Id
	private String userEmail;
	private List<Product> products;
	private long price;
}
