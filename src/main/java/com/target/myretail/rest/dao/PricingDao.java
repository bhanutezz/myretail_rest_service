package com.target.myretail.rest.dao;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.target.myretail.rest.model.Price;
/**
 * This PricingDAO class defines the methods to get and update the product price in MongoDB.
 * @author bhanu
 * @version 1.0
 * @since 07/21/2017
 */
@Repository
public class PricingDao {
	
	/**
	 * MongoTemplate reusable instance is injected by Spring to use it throughout the application.
	 */
	@Autowired
	private MongoTemplate mongoTemplate;
	
	/**
	 * This method is used to get the product price executing Query with id
	 * @param id This is used to query MongoDB documents.
	 * @return price This is the product price returned by query.
	 */
	public Price readByProductId(int id){
		// findOne method has similar select functionality in MongoDB.
		Price price = mongoTemplate.findOne(Query.query(Criteria.where("productid").is(id)), Price.class);
		return price;
	}
	
	/**
	 * This method is used to update the product price in MongoDB for a productid.
	 * @param id This is the product id to be referenced in MongoDB.
	 * @param newPrice This is the Price object consists new price which has to be updated in MongoDB.
	 * @return Nothing 
	 */
	public void updateProductPrice(int id, Price newPrice){
		Price price = readByProductId(id);
		price.setCurrency_code(newPrice.getCurrency_code());
		price.setValue(newPrice.getValue());
		//save method is used to update existing or insert new document in MongoDB. 
		mongoTemplate.save(price);
		
	}

}
