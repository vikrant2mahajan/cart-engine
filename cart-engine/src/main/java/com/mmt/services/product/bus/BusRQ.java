package com.mmt.services.product.bus;

import com.mmt.services.product.IRequest;

public class BusRQ implements IRequest{
	
	private String origin;
	private String destination;
	private String departureDate;
	private String isRoundTrip;
	private String noOfPax;
	private String seatType;
	private String returnDate;
	
	
	public String getOrigin() {
		return origin;
	}
	
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	/*
	 * The Following Format will be returned.
	 * DD-MM-YYYY
	 */
	public String getDepartureDate() {
		return departureDate;
	}
	
	/*
	 * Use the Following Format.
	 * DD-MM-YYYY
	 */
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getIsRoundTrip() {
		return isRoundTrip;
	}

	public void setIsRoundTrip(String isRoundTrip) {
		this.isRoundTrip = isRoundTrip;
	}

	public String getNoOfPax() {
		return noOfPax;
	}

	public void setNoOfPax(String noOfPax) {
		this.noOfPax = noOfPax;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
}
