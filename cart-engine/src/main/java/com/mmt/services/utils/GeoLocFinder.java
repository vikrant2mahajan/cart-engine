package com.mmt.services.utils;

import org.springframework.stereotype.Component;

import com.mmt.services.http.HttplClient;

@Component("locFinder")
public class GeoLocFinder {

	private HttplClient client;

	public HttplClient getClient() {
		return client;
	}

	public void setClient(HttplClient client) {
		this.client = client;
	}
}
