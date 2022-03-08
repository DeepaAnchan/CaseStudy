package com.example.cart.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
	private int id;
	private String role;

	@Override
	public String toString() {
		return "Role{" + "id=" + id + ", role='" + role + '\'' + '}';
	}
}
