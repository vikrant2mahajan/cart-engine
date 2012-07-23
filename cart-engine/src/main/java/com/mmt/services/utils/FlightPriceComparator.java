package com.mmt.services.utils;

import java.util.Comparator;

import com.mmt.engine.core.utils.FlightCombination;
import com.mmt.util.ApplicationUtil;

public class FlightPriceComparator implements Comparator{

	@Override
	public int compare(Object object1, Object object2) {
		FlightCombination o1=(FlightCombination) object1;
		FlightCombination o2=(FlightCombination) object2;
//		return Integer.parseInt(o1.getBookingPriceToDisplay()) - Integer.parseInt(o2.getBookingPriceToDisplay());
		if(ApplicationUtil.getCombinationPrice(o1)<ApplicationUtil.getCombinationPrice(o2)){
			return -1;
		}else if(ApplicationUtil.getCombinationPrice(o1)>ApplicationUtil.getCombinationPrice(o2)){
			return 1;
		}
		return 0;
	}

}
