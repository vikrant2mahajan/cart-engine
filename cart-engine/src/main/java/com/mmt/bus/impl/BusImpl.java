package com.mmt.bus.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.mmt.hotel.entity.MMTHotelSearchRequest;
import com.mmt.hotel.entity.MMTHotelSearchResponse;
import com.mmt.hotel.entity.MMTHotelSearchRequest.POS;
import com.mmt.hotel.entity.MMTHotelSearchRequest.PromotionReferenceCodes;
import com.mmt.hotel.entity.MMTHotelSearchRequest.ResultTransformer;
import com.mmt.hotel.entity.MMTHotelSearchRequest.SearchCriteria;
import com.mmt.hotel.entity.MMTHotelSearchRequest.POS.Requestor;
import com.mmt.hotel.entity.MMTHotelSearchRequest.POS.Source;
import com.mmt.hotel.entity.MMTHotelSearchRequest.PromotionReferenceCodes.PromotionReferenceCode;
import com.mmt.hotel.entity.MMTHotelSearchRequest.SearchCriteria.Criterion;
import com.mmt.hotel.entity.MMTHotelSearchRequest.SearchCriteria.Criterion.Area;
import com.mmt.hotel.entity.MMTHotelSearchRequest.SearchCriteria.Criterion.RoomStayCandidates;
import com.mmt.hotel.entity.MMTHotelSearchRequest.SearchCriteria.Criterion.StayDateRanges;
import com.mmt.hotel.entity.MMTHotelSearchRequest.SearchCriteria.Criterion.RoomStayCandidates.RoomStayCandidate;
import com.mmt.hotel.entity.MMTHotelSearchRequest.SearchCriteria.Criterion.RoomStayCandidates.RoomStayCandidate.GuestCounts;
import com.mmt.hotel.entity.MMTHotelSearchRequest.SearchCriteria.Criterion.RoomStayCandidates.RoomStayCandidate.GuestCounts.GuestCount;
import com.mmt.hotel.entity.MMTHotelSearchRequest.SearchCriteria.Criterion.StayDateRanges.StayDateRange;
import com.travis.webservices.WsAuthenticate;
import com.travis.webservices.WsGetCityRQ;
import com.travis.webservices.wsticketvala.wsticketvala.WsGetCity;
import com.travis.webservices.wsticketvala.wsticketvala.WsGetCityResponse;

@Component("busImpl")
public class BusImpl {

	@Autowired
	private WebServiceTemplate webServiceTemplate;
	
	@Autowired
	private Jaxb2Marshaller marshaller;

	public WebServiceTemplate getWebServiceTemplate() {
		return webServiceTemplate;
	}

	public void setWebServiceTemplate(WebServiceTemplate webServiceTemplate) {
		this.webServiceTemplate = webServiceTemplate;
	}
	
	public Jaxb2Marshaller getMarshaller() {
		return marshaller;
	}

	public void setMarshaller(Jaxb2Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	public Object getBusCities(){
		WsAuthenticate au = new WsAuthenticate();
		au.setGroupId("1");
		au.setUserId("transportation");
		au.setPassword("tr@nsp0rt@t10n@mmt");
		
		WsGetCityRQ request = new WsGetCityRQ();
		request.setWsAuthenticate(au);
		WsGetCity appContent = new WsGetCity();
		appContent.setWsGetCityRQ(request);
		WsGetCityResponse appContentRespons = (WsGetCityResponse) webServiceTemplate.marshalSendAndReceive(appContent);
		return appContentRespons;
	}
	
	public Object getHotelSearch(){
		RestTemplate restTemplate = new RestTemplate();
		MMTHotelSearchRequest request = new MMTHotelSearchRequest();
		POS pos =getPos();
		PromotionReferenceCodes prc = new PromotionReferenceCodes();
		PromotionReferenceCode code = new PromotionReferenceCode();
		code.setCodeType("campainId");
		code.setValue("tripadv-JAN");
		prc.getPromotionReferenceCode().add(code);
		ResultTransformer tr = new ResultTransformer();
		tr.setGuestRecommendationEnabled("false");
		tr.setPriceBreakupEnabled("true");
		SearchCriteria criteria = new SearchCriteria();
		Criterion cr = new Criterion();
		//Setting area
		Area area = new Area();
		area.setCityCode("BOM");
		area.setCountryCode("IN");
		cr.getArea().add(area);
		
		//Set room stay candidate
		RoomStayCandidates rsc = new RoomStayCandidates();
		RoomStayCandidate rs = new RoomStayCandidate();
		GuestCounts gcs = new GuestCounts();
		GuestCount gc = new GuestCount();
		gc.setAgeQualifyingCode("10");
		gc.setCount("1");
		gcs.getGuestCount().add(gc);
		rs.getGuestCounts().add(gcs);
		rsc.getRoomStayCandidate().add(rs);
		cr.getRoomStayCandidates().add(rsc);
		
		//Set date range
		StayDateRanges sdrs = new StayDateRanges();
		StayDateRange sdr = new StayDateRange();
		sdr.setStart("2012-07-21");
		sdr.setEnd("2012-07-22");
		sdrs.getStayDateRange().add(sdr);
		cr.getStayDateRanges().add(sdrs);
		
		criteria.getCriterion().add(cr);
		request.getPOSOrPromotionReferenceCodesOrResultTransformer().add(prc);
		request.getPOSOrPromotionReferenceCodesOrResultTransformer().add(pos);
		request.getPOSOrPromotionReferenceCodesOrResultTransformer().add(criteria);
		request.getPOSOrPromotionReferenceCodesOrResultTransformer().add(tr);
		
		ResponseEntity<MMTHotelSearchResponse> response = restTemplate.postForEntity("http://10.86.11.43/HotelsSOA/hotels/search/v1.0/hotelSearch", request, MMTHotelSearchResponse.class);
		System.out.println("");
		return null;
	}

	private MMTHotelSearchRequest.POS getPos() {
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
}
