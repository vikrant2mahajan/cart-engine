package com.mmt.services.product.hotels;

public class Hotel {
	private String hotelId;

	private String hotelName;

	private String urlPic;
	
	private String starRating;
	
	private String lowestRate;
	
	private boolean isRestaurantOrBar;
	
	private boolean isInternet;
	
	private boolean isRecreationAvail;
	
	private boolean isSwimmingPoolAvail;
	
	private boolean isParkingAvail;

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getUrlPic() {
		return urlPic;
	}

	public void setUrlPic(String urlPic) {
		this.urlPic = urlPic;
	}

	public String getStarRating() {
		return starRating;
	}

	public void setStarRating(String starRating) {
		this.starRating = starRating;
	}

	public String getLowestRate() {
		return lowestRate;
	}

	public void setLowestRate(String lowestRate) {
		this.lowestRate = lowestRate;
	}

	public boolean isRestaurantOrBar() {
		return isRestaurantOrBar;
	}

	public void setRestaurantOrBar(boolean isRestaurantOrBar) {
		this.isRestaurantOrBar = isRestaurantOrBar;
	}

	public boolean isInternet() {
		return isInternet;
	}

	public void setInternet(boolean isInternet) {
		this.isInternet = isInternet;
	}

	public boolean isRecreationAvail() {
		return isRecreationAvail;
	}

	public void setRecreationAvail(boolean isRecreationAvail) {
		this.isRecreationAvail = isRecreationAvail;
	}

	public boolean isSwimmingPoolAvail() {
		return isSwimmingPoolAvail;
	}

	public void setSwimmingPoolAvail(boolean isSwimmingPoolAvail) {
		this.isSwimmingPoolAvail = isSwimmingPoolAvail;
	}

	public boolean isParkingAvail() {
		return isParkingAvail;
	}

	public void setParkingAvail(boolean isParkingAvail) {
		this.isParkingAvail = isParkingAvail;
	}
	
	
}
