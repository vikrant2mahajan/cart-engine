package com.mmt.services.product.hotels;


import java.util.ArrayList;
import java.util.List;

import com.mmt.services.product.IRequest;

public class HotelRQ implements IRequest {

	
	private String cityCode;
	private String checkInDate;
	private String checkOutDate;
	
	private List<RoomRQ> noOfRooms = new ArrayList<RoomRQ>();

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public List<RoomRQ> getNoOfRooms() {
		return noOfRooms;
	}

	public void addRooms(RoomRQ room) {
		this.noOfRooms.add(room);
	}
	
}

