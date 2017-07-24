package com.target.myretail.rest.model;
/**
 * This is Product model class represents the product and price data
 * coming from API and MongoDB database.
 * 
 * @author bhanu
 * @version 1.0
 * @since 07/21/2017
 */
public class Product {

	private int id;
	private String name;
	/**
	 * Price class is defined to create has-a relation with Product to bind it for REST GET response 
	 */
	private Price current_price;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the current_price
	 */
	public Price getCurrent_price() {
		return current_price;
	}

	/**
	 * @param current_price the current_price to set
	 */
	public void setCurrent_price(Price current_price) {
		this.current_price = current_price;
	}

}
