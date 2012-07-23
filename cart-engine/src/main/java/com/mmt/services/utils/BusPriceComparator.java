package com.mmt.services.utils;

import java.util.Comparator;

import com.mmt.services.product.hotels.Hotel;
import com.travis.webservices.WsCheckAvailabilityRS.Trip;

public class BusPriceComparator implements Comparator<Trip> {
	@Override
	public int compare(Trip o1, Trip o2) {
		if(o1!=null && o2!=null){
			if(Integer.parseInt(getFare(o1).split("\\.")[0])<Integer.parseInt(getFare(o2).split("\\.")[0])){
				return -1;
			}else if(Integer.parseInt(getFare(o1).split("\\.")[0])>Integer.parseInt(getFare(o2).split("\\.")[0])){
				return 1;
			}
		}
		return 0;
	}

	private String getFare(Trip o1) {
		if(o1.getSeatFare()!=null){
			return o1.getSeatFare();
		}else{
			return o1.getSleeperFare();
		}
	}
}
