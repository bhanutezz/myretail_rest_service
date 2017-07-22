package com.target.myretail.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.target.myretail.rest.model.Product;
import com.target.myretail.rest.service.ProductService;
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "products/{productid}", method = RequestMethod.GET)
	public @ResponseBody Product products(@PathVariable("productid") int productid){
		return productService.getProduct(productid);
	}
	
}
