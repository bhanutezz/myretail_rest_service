package com.target.myretail.rest.dao;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.target.myretail.rest.model.Price;

@Repository
public class PricingDao {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public Price readByProductId(int id){
		Price price = mongoTemplate.findOne(Query.query(Criteria.where("productid").is(id)), Price.class);
		return price;
	}
	
	public void updateProduct(int id, Price newPrice){
		Price price = readByProductId(id);
		price.setCurrency_code(newPrice.getCurrency_code());
		price.setValue(newPrice.getValue());
		mongoTemplate.save(price);
		
	}

}
