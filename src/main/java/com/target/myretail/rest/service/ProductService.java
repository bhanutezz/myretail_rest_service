package com.target.myretail.rest.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.target.myretail.rest.dao.PricingDao;
import com.target.myretail.rest.model.Price;
import com.target.myretail.rest.model.Product;


@Service
public class ProductService {
	
	@Autowired
	private PricingDao pricingDao;
	
	public Product getProduct(int id){
		Product product = new Product();
		product.setId(id);
		product.setName(getProductName(id));
		
		Price price = pricingDao.readByProductId(id);
		product.setCurrent_price(price);
		
		return product;
	}
	
	public String getProductName(int id){
		String uri = "http://redsky.target.com/v2/pdp/tcin/" +id;
		
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		
		JSONObject jsonObject = new JSONObject(result).getJSONObject("product");
		JSONObject product_description = jsonObject.getJSONObject("item").getJSONObject("product_description");
		String productName = product_description.getString("title");
		
		return productName;
	}
	
	public void updateProduct(int id,String jsonStr){
		JSONObject priceObject = new JSONObject(jsonStr);
		JSONObject current_price =  priceObject.getJSONObject("current_price");
		
		Price newPrice = new Price();
		newPrice.setValue(current_price.getDouble("value"));
		newPrice.setCurrency_code(current_price.getString("currency_code"));
		pricingDao.updateProduct(id, newPrice);
		
	}

}
