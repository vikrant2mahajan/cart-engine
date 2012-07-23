package com.mmt.data.models;

public class CarRequestBean extends HotelRequestBean{
    String cDepCity;
	
	String cRetCity;
	
	String cDepDate;
	
	String cRetDate;

	public String getcDepCity() {
		return cDepCity;
	}

	public void setcDepCity(String cDepCity) {
		this.cDepCity = cDepCity;
	}

	public String getcRetCity() {
		return cRetCity;
	}

	public void setcRetCity(String cRetCity) {
		this.cRetCity = cRetCity;
	}

	public String getcDepDate() {
		return cDepDate;
	}

	public void setcDepDate(String cDepDate) {
		this.cDepDate = cDepDate;
	}

	public String getcRetDate() {
		return cRetDate;
	}

	public void setcRetDate(String cRetDate) {
		this.cRetDate = cRetDate;
	}
	
	
	
}
