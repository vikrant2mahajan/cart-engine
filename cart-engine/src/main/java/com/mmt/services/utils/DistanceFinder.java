package com.mmt.services.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mmt.services.http.HttplClient;

@Component("distanceFinder")
@Scope(value="prototype")
public class DistanceFinder {
	
	@Autowired
	private HttplClient client;
	
	private String fromCity;
	
	private String toCity;

	
	public DistanceFinder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DistanceFinder(String fromCity, String toCity) {
		super();
		this.fromCity = fromCity;
		this.toCity = toCity;
	}

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

	public String getDistance(){
		String url = "http://www.world-airport-codes.com/dist/?a1="+fromCity+"&a2="+toCity;
		client.setUrl(url);
		client.executeMethod();
		String response = client.getResponse();
		return parse(response);
	}

	private String parse(String res){
		String str[] = res.split("The distance has been calculated as being:");
		String str5 = null;
		if(str.length>1){
			String str1[] =str[1].split("kilometres");
			String str2 = str1[0].trim();
			String str3 = str2.replace("<b>", "");
			String str4 = str3.replace("</b>", "");
			str5 = str4.trim();
		} else {
			System.out.println("ERROR::: ");	
		}
		return str5;
	}

}
