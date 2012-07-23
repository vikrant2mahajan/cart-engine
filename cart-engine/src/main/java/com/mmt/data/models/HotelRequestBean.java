package com.mmt.data.models;

public class HotelRequestBean {
    String hHotCity;
	
	String fRetCity;
	
    String hCheckInDate;
	
	String hCheckOutDate;

	int    rooms;
	
	int hchildCount;
	
	int hadultcount;

	public String gethHotCity() {
		return hHotCity;
	}

	public void sethHotCity(String hHotCity) {
		this.hHotCity = hHotCity;
	}

	public String getfRetCity() {
		return fRetCity;
	}

	public void setfRetCity(String fRetCity) {
		this.fRetCity = fRetCity;
	}

	public String gethCheckInDate() {
		return hCheckInDate;
	}

	public void sethCheckInDate(String hCheckInDate) {
		this.hCheckInDate = hCheckInDate;
	}

	public String gethCheckOutDate() {
		return hCheckOutDate;
	}

	public void sethCheckOutDate(String hCheckOutDate) {
		this.hCheckOutDate = hCheckOutDate;
	}

	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	public int getHchildCount() {
		return hchildCount;
	}

	public void setHchildCount(int hchildCount) {
		this.hchildCount = hchildCount;
	}

	public int getHadultcount() {
		return hadultcount;
	}

	public void setHadultcount(int hadultcount) {
		this.hadultcount = hadultcount;
	}
	
}
