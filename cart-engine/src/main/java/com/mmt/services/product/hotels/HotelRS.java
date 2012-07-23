package com.mmt.services.product.hotels;

import java.util.List;

import com.mmt.services.product.IResponse;

public class HotelRS implements IResponse {

	private List<Hotel> hotels;
	
	private String cityName;

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}
