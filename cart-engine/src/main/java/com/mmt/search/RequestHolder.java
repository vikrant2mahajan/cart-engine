package com.mmt.search;

import com.mmt.services.product.IRequest;
import com.mmt.util.ProductType;

public class RequestHolder {
	private IRequest request;
	
	private ProductType type;

	public IRequest getRequest() {
		return request;
	}

	public void setRequest(IRequest request) {
		this.request = request;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}
	
}
