package com.example.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product.entity.Product;
import com.example.product.messageSender.MessageSender;
import com.example.product.serviceImpl.ProductServiceImpl;
import com.example.product.util.LogUtil;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@Autowired
	private MessageSender messageSender;	
	
	@Autowired
	private LogUtil logUtil;
	
	@GetMapping(value = "/getAllProducts")
	public List<Product> getAllProducts(){
		logUtil.logInfo("ProductController: Get all products");
		//messageSender.send("Got all products");
		return productServiceImpl.getAllProducts();		
	}

}
