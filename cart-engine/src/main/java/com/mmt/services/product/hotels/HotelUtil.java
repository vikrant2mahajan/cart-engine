package com.mmt.services.product.hotels;

import com.mmt.hotel.entity.MMTHotelSearchRequest;
import com.mmt.hotel.entity.MMTHotelSearchRequest.PromotionReferenceCodes;
import com.mmt.hotel.entity.MMTHotelSearchRequest.ResultTransformer;
import com.mmt.hotel.entity.MMTHotelSearchRequest.POS.Requestor;
import com.mmt.hotel.entity.MMTHotelSearchRequest.POS.Source;
import com.mmt.hotel.entity.MMTHotelSearchRequest.PromotionReferenceCodes.PromotionReferenceCode;

public class HotelUtil {

	public static MMTHotelSearchRequest.POS getPos(){
		MMTHotelSearchRequest.POS pos = new MMTHotelSearchRequest.POS();
		pos.setToken("amitta");
		Requestor requesto = new Requestor();
		requesto.setChannel("B2C");
		requesto.setId("AFF0987");
		requesto.setIdContext("B2C");
		requesto.setType("B2C");
		pos.getRequestor().add(requesto);
		Source source = new Source();
		source.setISOCurrency("INR");
		pos.getSource().add(source);
		return pos;
	}
	
	public static MMTHotelSearchRequest.PromotionReferenceCodes getPromotionReferenceCodes(){
		PromotionReferenceCodes prc = new PromotionReferenceCodes();
		PromotionReferenceCode code = new PromotionReferenceCode();
		code.setCodeType("campainId");
		code.setValue("tripadv-JAN");
		prc.getPromotionReferenceCode().add(code);
		return prc;
	}
	
	public static MMTHotelSearchRequest.ResultTransformer getResultTransformer(){
		ResultTransformer tr = new ResultTransformer();
		tr.setGuestRecommendationEnabled("false");
		tr.setPriceBreakupEnabled("true");
		return tr;
	}
}
