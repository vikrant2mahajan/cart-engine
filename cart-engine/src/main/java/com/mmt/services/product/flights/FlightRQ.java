package com.mmt.services.product.flights;

import com.mmt.services.product.IRequest;

public class FlightRQ implements IRequest {
	
	String origin;
	String destination;
	String tripType;
	String departureDate;
	String returnDate;
	String cabinClass;
	String noOfAdult;
	String noOfChild;
	String noOfInfant;
	
	
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
	public String getTripType() {
		return tripType;
	}
	public void setTripType(String tripType) {
		this.tripType = tripType;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getCabinClass() {
		return cabinClass;
	}
	public void setCabinClass(String cabinClass) {
		this.cabinClass = cabinClass;
	}
	public String getNoOfAdult() {
		return noOfAdult;
	}
	public void setNoOfAdult(String noOfAdult) {
		this.noOfAdult = noOfAdult;
	}
	public String getNoOfChild() {
		return noOfChild;
	}
	public void setNoOfChild(String noOfChild) {
		this.noOfChild = noOfChild;
	}
	public String getNoOfInfant() {
		return noOfInfant;
	}
	public void setNoOfInfant(String noOfInfant) {
		this.noOfInfant = noOfInfant;
	}
	
}
