package com.mmt.data.models;

public class BusRequestBean extends HotelRequestBean{
    String bDepCity;

	String bRetCity;
	
	String bDepDate;
	

	public String getbDepCity() {
		return bDepCity;
	}

	public void setbDepCity(String bDepCity) {
		this.bDepCity = bDepCity;
	}

	public String getbRetCity() {
		return bRetCity;
	}

	public void setbRetCity(String bRetCity) {
		this.bRetCity = bRetCity;
	}

	public String getbDepDate() {
		return bDepDate;
	}

	public void setbDepDate(String bDepDate) {
		this.bDepDate = bDepDate;
	}

}
