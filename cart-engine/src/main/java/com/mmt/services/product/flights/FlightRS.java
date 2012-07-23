package com.mmt.services.product.flights;

import com.mmt.engine.core.search.SearchResponse;
import com.mmt.services.product.IResponse;

public class FlightRS implements IResponse {

	private SearchResponse response;

	public SearchResponse getResponse() {
		return response;
	}

	public void setResponse(SearchResponse response) {
		this.response = response;
	}
}
