package com.target.myretail.rest.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.target.myretail.rest.dao.PricingDao;
import com.target.myretail.rest.model.Price;
import com.target.myretail.rest.model.Product;

/**
 * This is ProductServiceImpl class which implements the ProducService interface methods
 * to get the product price and name as well as to
 * update the price in MongoDB database.
 * 
 * @author bhanu
 * @version 1.0
 * @since 07/21/2017
 */
@Service
public class ProductServiceImpl implements ProductService{
	
	/**
	 * PricingDAO reusable instance is injected by Spring application context
	 */
	@Autowired
	private PricingDao pricingDao;
	
	/** 
	 * This method is used to create a product, bind the product name from API
	 * and price from MongoDB JSON object.
	 * @param id used to pass to getProductName method to get the name and price from readByProductId
	 * @return product is instance of Product returned Controller
	 * @see com.target.myretail.rest.service.ProductService#getProductPriceAndName(int)
	 */
	public Product getProductPriceAndName(int id){
		Product product = new Product();
		product.setId(id);
		product.setName(getProductName(id));
		
		Price price = pricingDao.readByProductId(id);
		product.setCurrent_price(price);
		
		return product;
	}
	
	/** 
	 * This method is used to extract product name from JSON object coming from 
	 * external API.
	 * @param id is used to get the API JSON response by adding it to external link
	 * @return productName is returned to getProductPriceAndName method
	 * @see com.target.myretail.rest.service.ProductService#getProductName(int)
	 * @throws RuntimeException on unknown productid passsed with URI in getForObject method
	 */
	public String getProductName(int id) throws RuntimeException{
		String uri = "http://redsky.target.com/v2/pdp/tcin/" +id;
		
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		
		JSONObject jsonObject = new JSONObject(result).getJSONObject("product");
		JSONObject product_description = jsonObject.getJSONObject("item").getJSONObject("product_description");
		String productName = product_description.getString("title");
		
		return productName;
	}
	
	/** 
	 * This method is used to extract the product price coming in PUT REST request,
	 * binding it to Price object and pass it updateProductPrice method in PricingDAO class.
	 * 
	 * @param id is used to pass it to PricingDAO class method
	 * @param jsonStr is JSON string similar to GET response contains name and price
	 * @return Nothing
	 * @see com.target.myretail.rest.service.ProductService#updateProductPrice(int, java.lang.String)
	 */
	public void updateProductPrice(int id,String jsonStr){
		JSONObject priceObject = new JSONObject(jsonStr);
		JSONObject current_price =  priceObject.getJSONObject("current_price");
		
		Price newPrice = new Price();
		newPrice.setValue(current_price.getDouble("value"));
		newPrice.setCurrency_code(current_price.getString("currency_code"));
		pricingDao.updateProductPrice(id, newPrice);
		
	}

}
