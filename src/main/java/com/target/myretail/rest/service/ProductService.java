package com.target.myretail.rest.service;

import com.target.myretail.rest.model.Product;

/**
 * The ProductService is an interface which has the method
 * definitions to get the product price and name as well as to
 * update the price in MongoDB database.
 * 
 * @author bhanu
 * @version 1.0
 * @since 07/21/2017
 */
public interface ProductService {
	public Product getProductPriceAndName(int id);
	public String getProductName(int id);
	public void updateProductPrice(int id,String jsonStr);

}
