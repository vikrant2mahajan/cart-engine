package com.mmt.data.models;

import java.util.HashMap;
import java.util.Map;

public class ProductCart {

	private Map<String, Object> products;

	private int fare;

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	public ProductCart() {
		products = new HashMap<String, Object>();
	}

	public Map<String, Object> getProducts() {
		return products;
	}

	public void setProducts(Map<String, Object> products) {
		this.products = products;
	}

}
