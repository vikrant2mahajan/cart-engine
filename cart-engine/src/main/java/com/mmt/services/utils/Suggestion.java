package com.mmt.services.utils;

import com.mmt.search.RequestHolder;

public class Suggestion {
	private String imgUrl;
	
	private String price;
	
	private RequestHolder request;
	
	private String description;

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public RequestHolder getRequest() {
		return request;
	}

	public void setRequest(RequestHolder request) {
		this.request = request;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
