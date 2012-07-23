package com.mmt.services.utils;

import java.util.Comparator;

import com.mmt.car.ws.Result;
import com.mmt.services.product.hotels.Hotel;

public class CarPriceComparator implements Comparator<Result>{

	@Override
	public int compare(Result o1, Result o2) {
		if(Integer.parseInt(o1.getBookingPriceToDisplay())<Integer.parseInt(o2.getBookingPriceToDisplay())){
			return -1;
		}else if(Integer.parseInt(o1.getBookingPriceToDisplay())>Integer.parseInt(o2.getBookingPriceToDisplay())){
			return 1;
		}
		return 0;
//		return Integer.parseInt(o1.getBookingPriceToDisplay()) - Integer.parseInt(o2.getBookingPriceToDisplay());
	}

}
