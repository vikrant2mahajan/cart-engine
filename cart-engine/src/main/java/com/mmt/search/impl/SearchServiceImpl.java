package com.mmt.search.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mmt.search.RequestHolder;
import com.mmt.search.ResponseHolder;
import com.mmt.search.SearchService;
import com.mmt.services.product.IProductService;
import com.mmt.services.product.IResponse;
import com.mmt.util.ProductType;

@Component("searchService")
@Scope(value = "prototype")
public class SearchServiceImpl implements SearchService {

	private IResponse response;

	@Autowired
	private IProductService flightService;

	@Autowired
	private IProductService carService;

	@Autowired
	private IProductService busService;

	@Autowired
	private IProductService hotelService;

	public IProductService getFlightService() {
		return flightService;
	}

	public void setFlightService(IProductService flightService) {
		this.flightService = flightService;
	}

	public IProductService getCarService() {
		return carService;
	}

	public void setCarService(IProductService carService) {
		this.carService = carService;
	}

	public IProductService getBusService() {
		return busService;
	}

	public void setBusService(IProductService busService) {
		this.busService = busService;
	}

	public IProductService getHotelService() {
		return hotelService;
	}

	public void setHotelService(IProductService hotelService) {
		this.hotelService = hotelService;
	}

	public ResponseHolder search(RequestHolder requestHolder) {
		switch (requestHolder.getType()) {
		case FLIGHT:
			response = flightService.search(requestHolder.getRequest());
			break;
		case CAR:
			response = carService.search(requestHolder.getRequest());
			break;
		case BUS:
			response = busService.search(requestHolder.getRequest());
			break;
		case HOTEL:
			response = hotelService.search(requestHolder.getRequest());
			break;
		default:
			break;
		}
		return getResponseHolder(response, requestHolder.getType());
	}

	private ResponseHolder getResponseHolder(IResponse response,
			ProductType type) {
		ResponseHolder holder = new ResponseHolder();
		holder.setResponse(response);
		holder.setType(type);
		return holder;
	}
}
