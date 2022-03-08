package com.example.cart.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class CustomException extends RuntimeException {
	private static final long serialVersionUID = 9131218248131489414L;
	private final String message;
	private final HttpStatus httpStatus;
}
