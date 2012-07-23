package com.mmt.services.utils;

import java.util.Comparator;

import com.mmt.services.product.hotels.Hotel;
import com.mmt.util.ApplicationUtil;

public class HotelPriceComparator implements Comparator<Hotel>{

	@Override
	public int compare(Hotel o1, Hotel o2) {
		if(Integer.parseInt(o1.getLowestRate())<Integer.parseInt(o2.getLowestRate())){
			return -1;
		}else if(Integer.parseInt(o1.getLowestRate())>Integer.parseInt(o2.getLowestRate())){
			return 1;
		}
		return 0;
//		return Integer.parseInt(o1.getLowestRate()) - Integer.parseInt(o2.getLowestRate());
	}

}
