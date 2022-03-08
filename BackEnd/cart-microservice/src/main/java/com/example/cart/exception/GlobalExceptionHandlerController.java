package com.example.cart.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException e, HttpServletRequest request) throws IOException {
		return prepareResponseBody(e, e.getHttpStatus());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException( AccessDeniedException e,  HttpServletRequest request) throws IOException {
		return prepareResponseBody("Access denied", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException( IllegalArgumentException e,  HttpServletRequest request) throws IOException {
    	return prepareResponseBody("Something went wrong", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e, HttpServletRequest request) throws IOException {
    	return prepareResponseBody("Something went wrong", HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> prepareResponseBody(String message, HttpStatus status) {
    	return new ResponseEntity<>(message, status);
    }
    
    private ResponseEntity<Object> prepareResponseBody(Exception e, HttpStatus status) {
    	return new ResponseEntity<>(e.getMessage(), status);
    }
    
//    private ResponseEntity<Object> prepareResponseBody(Exception e) {
//    	return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    	 //return ResponseEntity.status(403).body(new ErrorResponse(e.getMessage()));
//    }

}
