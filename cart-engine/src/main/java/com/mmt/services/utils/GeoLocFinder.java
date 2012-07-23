package com.mmt.services.utils;

import org.apache.commons.httpclient.params.HttpMethodParams;

import com.mmt.services.http.HttplClient;

public class GeoLocFinder {

	private HttplClient client;

	public HttplClient getClient() {
		return client;
	}

	public void setClient(HttplClient client) {
		this.client = client;
	}
	
	public String getGeoLoc(String ip){
		String url = "http://www.ip-adress.com/ip_tracer/"+ip;
		String userAgent ="Mozilla/5.0 (Windows NT 6.1; WOW64; rv:14.0) Gecko/20100101 Firefox/14.0.1";
		client.addHTTPParams(HttpMethodParams.USER_AGENT,userAgent);
		client.setUrl(url);
		client.executeMethod();
		String response = client.getResponse();
		return parse(response);
	}

	private String parse(String response) {
		String str[] = response.split("My IP address city:");
		String str5=null;
		if(str.length>1){
			String str1 =str[1].replace("</th>", "").replace("<td>", "");
			String str2 = str1.trim();
			String str3[] = str2.split("</td>");
			String str4 = str3[0];
			str5 = str4.trim();
		}
		return str5;
	}
	
}
