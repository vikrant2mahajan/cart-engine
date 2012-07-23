package com.mmt.search;

import com.mmt.services.product.IResponse;
import com.mmt.util.ProductType;

public class ResponseHolder {
	private IResponse response;
	
	private ProductType type;

	public IResponse getResponse() {
		return response;
	}

	public void setResponse(IResponse response) {
		this.response = response;
	}

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}
}
