package com.mmt.services.product.flights;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mmt.engine.core.clients.RMIServiceLocator;
import com.mmt.engine.core.search.SearchCriteria;
import com.mmt.engine.core.search.SearchRequest;
import com.mmt.engine.core.search.SearchResponse;
import com.mmt.services.product.IProductService;
import com.mmt.services.product.IRequest;
import com.mmt.services.product.IResponse;

@Component("flightService")
@Scope(value = "prototype")
public class FlightService implements IProductService {

	@Autowired
	private RMIServiceLocator rmiService = null;

	public RMIServiceLocator getRmiService() {
		return rmiService;
	}

	public void setRmiService(RMIServiceLocator rmiService) {
		this.rmiService = rmiService;
	}

	public IResponse search(IRequest req) {
		FlightRQ fltReq = (FlightRQ) req;
		SearchRequest request = new SearchRequest();
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setTripType("O");
		searchCriteria.setTripTypeDUP("O");
		searchCriteria.setSClass(fltReq.getCabinClass());
		searchCriteria.setCabinClass(fltReq.getCabinClass());
		searchCriteria.setDestination(fltReq.getDestination());
		searchCriteria.setDepartureDate(fltReq.getDepartureDate());
		searchCriteria.setOrigin(fltReq.getOrigin());
		searchCriteria.setAdults("1");
		searchCriteria.setChildren("0");
		searchCriteria.setInfants("0");
		searchCriteria.setResidentFlag("true");
		searchCriteria.setDataType("S");
		searchCriteria.setSearchType("FlightSearch");
		searchCriteria.setUserExperiments(null);
		request.setSearchCriteria(searchCriteria);
		SearchResponse response = null;
		try {
			response = (SearchResponse) rmiService.search(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		FlightRS fltRes = new FlightRS();
		fltRes.setResponse(response);
		return fltRes;
	}
}
