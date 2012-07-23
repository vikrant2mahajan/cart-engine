package com.mmt.services.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mmt.services.http.HttplClient;

@Component("distanceFinder")
public class DistanceFinder {
	
	@Autowired
	private HttplClient client;
	
	private String fromCity;
	
	private String toCity;

	public HttplClient getClient() {
		return client;
	}

	public void setClient(HttplClient client) {
		this.client = client;
	}
	
	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public void getGeoLocation(){
		String url = "http://www.world-airport-codes.com/dist/?a1="+fromCity+"&a2="+toCity;
		client.setUrl(url);
		client.executeMethod();
		String response = client.getResponse();
	}

}
