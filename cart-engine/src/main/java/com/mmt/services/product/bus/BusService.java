package com.mmt.services.product.bus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.mmt.services.product.IProductService;
import com.mmt.services.product.IRequest;
import com.mmt.services.product.IResponse;
import com.travis.webservices.WsCheckAvailabilityRQ;
import com.travis.webservices.WsGetCityRQ;
import com.travis.webservices.WsGetSeatMapRQ;
import com.travis.webservices.wsticketvala.wsticketvala.WsCheckAvailability1X;
import com.travis.webservices.wsticketvala.wsticketvala.WsCheckAvailability1XResponse;
import com.travis.webservices.wsticketvala.wsticketvala.WsGetCity;
import com.travis.webservices.wsticketvala.wsticketvala.WsGetCityResponse;
import com.travis.webservices.wsticketvala.wsticketvala.WsGetSeatMap;
import com.travis.webservices.wsticketvala.wsticketvala.WsGetSeatMapResponse;

@Component("busService")
public class BusService implements IProductService {

	@Autowired
	private WebServiceTemplate webServiceTemplate;

	@Autowired
	private Jaxb2Marshaller marshaller;

	public IResponse search(IRequest req) {
		BusRQ busReq = (BusRQ) req;
		WsCheckAvailability1X availReq = getAvailabilityRequest(busReq);
		WsCheckAvailability1XResponse response = (WsCheckAvailability1XResponse) webServiceTemplate
				.marshalSendAndReceive(availReq);
		return response;
	}

	private WsCheckAvailability1X getAvailabilityRequest(BusRQ busReq) {
		WsCheckAvailability1X availReq = new WsCheckAvailability1X();
		WsCheckAvailabilityRQ req = new WsCheckAvailabilityRQ();
		req.setFromCity(busReq.getOrigin());
		// req.setGroupIdOfSrviceProvider("1");
		req.setIsRoundTrip(busReq.getIsRoundTrip());
		req.setJourneyDate(busReq.getDepartureDate());
		req.setNoOfPax(busReq.getNoOfPax());
		req.setSeatType(busReq.getSeatType());
		req.setReturnJourneyDate(busReq.getReturnDate());
		req.setToCity(busReq.getDestination());
		req.setWsAuthenticate(BusUtil.getWsAuthenticate());
		availReq.setWsCheckAvailabilityRQ(req);
		return availReq;
	}

	public IResponse getSeatMap(String tripId) {
		WsGetSeatMap wsGetSeatMap = new WsGetSeatMap();
		WsGetSeatMapRQ seatMapRq = new WsGetSeatMapRQ();
		seatMapRq.setTripId(tripId);
		seatMapRq.setWsAuthenticate(BusUtil.getWsAuthenticate());
		wsGetSeatMap.setWsGetSeatMapRQ(seatMapRq);
		WsGetSeatMapResponse response = (WsGetSeatMapResponse) webServiceTemplate
				.marshalSendAndReceive(wsGetSeatMap);
		return response;
	}

	public WsGetCityResponse getCities() {
		WsGetCity appContent = getCityRequest();
		WsGetCityResponse appContentRespons = (WsGetCityResponse) webServiceTemplate
				.marshalSendAndReceive(appContent);
		return appContentRespons;
	}

	private WsGetCity getCityRequest() {
		WsGetCityRQ request = new WsGetCityRQ();
		request.setWsAuthenticate(BusUtil.getWsAuthenticate());
		WsGetCity appContent = new WsGetCity();
		appContent.setWsGetCityRQ(request);
		return appContent;
	}

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
}
