package com.target.myretail.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.target.myretail.rest.model.Product;
import com.target.myretail.rest.service.ProductServiceImpl;
/**
 * The ProductController is controller class which implements two methods to 
 * handle GET and PUT REST requests.
 * 
 * @author bhanu
 * @version 1.0
 * @since 07/21/2017
 */
@Controller
public class ProductController {
	
	/**
	 * Service Layer class to access get and update methods of GET and PUT requests
	 */
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	/**
	 * This is the method to handle HTTP GET request and pass productid
	 * to getProductPriceAndName method.
	 * @param productid This is productid to which response body has to get.
	 * @return product object which consists product name and price information.
	 */
	@RequestMapping(value = "products/{productid}", method = RequestMethod.GET)
	public @ResponseBody Product getProductPriceAndNameInJSON(@PathVariable("productid") int productid){
		return productServiceImpl.getProductPriceAndName(productid);
	}
	
	/**
	 * This is the method to handle HTTP PUT request and pass productid and jsonstr string 
	 * to updateProductPrice method which updates product price in MongoDB database.
	 * @param productid This is the productid for which price has to be updated
	 * @param jsonStr This is JSON object string which consists similar body as GET response
	 */
	@RequestMapping(value = "products/{productid}", method = RequestMethod.PUT)
	public @ResponseBody void updateProductPriceInMongoDB(@PathVariable("productid") int productid,
			@RequestBody String jsonStr){
		productServiceImpl.updateProductPrice(productid, jsonStr);
	}
}
