package com.mmt.web.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.mmt.data.dao.api.CityMapperData;
import com.mmt.data.models.BusRequestBean;
import com.mmt.data.models.CarRequestBean;
import com.mmt.data.models.FlightRequestBean;
import com.mmt.data.models.HotelRequestBean;
import com.mmt.search.RequestHolder;
import com.mmt.search.ResponseHolder;
import com.mmt.search.SearchService;
import com.mmt.services.product.bus.BusRQ;
import com.mmt.services.product.cars.CarRQ;
import com.mmt.services.product.cars.ServiceType;
import com.mmt.services.product.flights.FlightRQ;
import com.mmt.services.product.hotels.HotelRQ;
import com.mmt.services.product.hotels.RoomRQ;
import com.mmt.util.ApplicationUtil;
import com.mmt.util.ProductType;

@Controller
public class AppController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ApplicationUtil util;

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Autowired
	private CityMapperData cityMapperData;

	@Autowired
	private SearchService searchService;

	public SearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public CityMapperData getCityMapperData() {
		return cityMapperData;
	}

	public void setCityMapperData(CityMapperData cityMapperData) {
		this.cityMapperData = cityMapperData;
	}

	// @RequestMapping("home.htm")
	// public ModelAndView getHomeUI() {
	// ModelAndView modelAndView = new ModelAndView("common_listing");
	// List cities = cityMapperData.getAllCities();
	// modelAndView.addObject("cities", cities);
	//
	// RequestHolder holder = new RequestHolder();
	//
	// FlightRQ request = new FlightRQ();
	// request.setCabinClass("E");
	// request.setDepartureDate("2012-07-25T00:00:00");
	// request.setDestination("BOM");
	// request.setNoOfAdult("1");
	// request.setOrigin("DEL");
	// request.setTripType("O");
	// holder.setType(ProductType.FLIGHT);
	// holder.setRequest(request);
	//
	// ResponseHolder holder2 = searchService.search(holder);
	// modelAndView.addObject("result", holder2);
	//
	// return modelAndView;
	// }

	@RequestMapping("bus.htm")
	public ModelAndView getBusUI() {
		ModelAndView modelAndView = new ModelAndView("common_listing");
		List cities = cityMapperData.getAllCities();
		modelAndView.addObject("cities", cities);

		RequestHolder holder = new RequestHolder();

		BusRQ request = new BusRQ();
		request.setDepartureDate("24-07-2012");
		request.setDestination("412");
		request.setIsRoundTrip("N");
		request.setNoOfPax("1");
		request.setOrigin("343");
		request.setSeatType("1");

		holder.setType(ProductType.BUS);
		holder.setRequest(request);

		ResponseHolder holder2 = searchService.search(holder);
		modelAndView.addObject("result", holder2);

		return modelAndView;
	}

	@RequestMapping("home.htm")
	public ModelAndView getHomeUI() {
		ModelAndView modelAndView = new ModelAndView("home");
//		List cities = cityMapperData.getAllCities();
		List cities = util.getCityList();
		modelAndView.addObject("cities", cities);
		return modelAndView;
	}

	@RequestMapping("flightsReq.htm")
	public ModelAndView getFlightsListing(FlightRequestBean formDetails) {
		ModelAndView modelAndView = new ModelAndView("common_listing");
		RequestHolder holder = new RequestHolder();
		FlightRQ request = new FlightRQ();
		request.setCabinClass(formDetails.getFcabinClass());
		request.setDepartureDate(formDetails.getfDepDate() + "T00:00:00");
		request.setReturnDate(formDetails.getfRetDate() + "T00:00:00");
		request.setDestination(formDetails.getfRetCity());
		request.setNoOfAdult(String.valueOf(formDetails.getFadultCount()));
		request.setNoOfChild(String.valueOf(formDetails.getFinfantCount()));
		request.setNoOfInfant(String.valueOf(formDetails.getFchildCount()));
		request.setOrigin(formDetails.getfDepCity());
		request.setTripType(formDetails.getTripType());
		holder.setRequest(request);
		holder.setType(ProductType.FLIGHT);
		ResponseHolder holder2 = searchService.search(holder);
		modelAndView.addObject("result", holder2);
		return modelAndView;
	}

	@RequestMapping("carReq.htm")
	public ModelAndView getCarListing(CarRequestBean formDetails) {
		ModelAndView modelAndView = new ModelAndView("common_listing");
		try{
			RequestHolder holder = new RequestHolder();
			CarRQ request = new CarRQ();
			String depDate = formDetails.getcDepDate();
			String[] dateComponents = depDate.split("-");

			request.setDate(dateComponents[2]);
			request.setDestination(formDetails.getcRetCity());
			request.setMonth(dateComponents[1]);
			request.setYear(dateComponents[0]);
			request.setOrigin(formDetails.getcDepCity());
			request.setCapacity("4");
			request.setServiceType(ServiceType.OUTSTATION_USAGE);
			if(formDetails.getcDepCity().equalsIgnoreCase(formDetails.getcRetCity())){
				request.setServiceType(ServiceType.LOCAL_USAGE);
				request.setDestination(null);
			}
			holder.setRequest(request);
			holder.setType(ProductType.CAR);
			ResponseHolder holder2 = searchService.search(holder);
			modelAndView.addObject("result", holder2);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	@RequestMapping("busReq.htm")
	public ModelAndView getBusListing(BusRequestBean formDetails) {
		ModelAndView modelAndView = new ModelAndView("common_listing");

		try {
			RequestHolder holder = new RequestHolder();
			BusRQ request = new BusRQ();
			// 22-07-2012 2012-07-25
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
			request.setDepartureDate(dateFormat2.format(dateFormat
					.parse(formDetails.getbDepDate())));
			request.setDestination(formDetails.getbRetCity());
			request.setIsRoundTrip("N");
			request.setNoOfPax("1");
			request.setOrigin(formDetails.getbDepCity());
			request.setSeatType("1");
			holder.setRequest(request);
			holder.setType(ProductType.BUS);
			ResponseHolder holder2 = searchService.search(holder);
			modelAndView.addObject("result", holder2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return modelAndView;
	}

	@RequestMapping("hotelReq.htm")
	public ModelAndView getHotelListing(HotelRequestBean formDetails) {
		ModelAndView modelAndView = new ModelAndView("common_listing");
		try{
			RequestHolder holder = new RequestHolder();
			HotelRQ request = new HotelRQ();
			request.setCheckInDate(formDetails.gethCheckInDate());
			request.setCheckOutDate(formDetails.gethCheckOutDate());
			request.setCityCode(formDetails.gethHotCity());
			RoomRQ roomRq = new RoomRQ();
			roomRq.setNoOfAdult(String.valueOf(formDetails.getHadultcount()));
			roomRq.setNoOfChild(String.valueOf(formDetails.getHchildCount()));
			request.getNoOfRooms().add(roomRq);
			holder.setType(ProductType.HOTEL);
			holder.setRequest(request);
			ResponseHolder holder2 = searchService.search(holder);
			modelAndView.addObject("result", holder2);
		}catch (Exception e) {
			e.printStackTrace();
		}

		return modelAndView;
	}

	public ApplicationUtil getUtil() {
		return util;
	}

	public void setUtil(ApplicationUtil util) {
		this.util = util;
	}

}
