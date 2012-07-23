package com.mmt.web.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.mmt.data.dao.api.CityMapperData;
import com.mmt.data.models.BusRequestBean;
import com.mmt.data.models.CarRequestBean;
import com.mmt.data.models.FlightRequestBean;
import com.mmt.data.models.HotelRequestBean;
import com.mmt.data.models.ProductCart;
import com.mmt.search.RequestHolder;
import com.mmt.search.ResponseHolder;
import com.mmt.search.SearchService;
import com.mmt.services.product.bus.BusRQ;
import com.mmt.services.product.cars.CarRQ;
import com.mmt.services.product.cars.ServiceType;
import com.mmt.services.product.flights.FlightRQ;
import com.mmt.services.product.flights.FlightRS;
import com.mmt.services.product.hotels.HotelRQ;
import com.mmt.services.product.hotels.HotelRS;
import com.mmt.services.product.hotels.RoomRQ;
import com.mmt.services.utils.BusPriceComparator;
import com.mmt.services.utils.FlightPriceComparator;
import com.mmt.services.utils.HotelPriceComparator;
import com.mmt.services.utils.ISuggestionService;
import com.mmt.util.ApplicationUtil;
import com.mmt.util.ProductType;
import com.travis.webservices.WsCheckAvailabilityRS.Trip;
import com.travis.webservices.wsticketvala.wsticketvala.WsCheckAvailability1XResponse;

@Controller
public class AppController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ApplicationUtil util;
	
	@Autowired
	private ISuggestionService suggestionService;

	public ISuggestionService getSuggestionService() {
		return suggestionService;
	}

	public void setSuggestionService(ISuggestionService suggestionService) {
		this.suggestionService = suggestionService;
	}

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
	public ModelAndView getFlightsListing(FlightRequestBean formDetails,HttpServletRequest httpRequest) {
		String geoLoc = "DEL";
		if(httpRequest.getParameter("geoLoc")!=null && !"".equalsIgnoreCase(httpRequest.getParameter("geoLoc"))){
			geoLoc= httpRequest.getParameter("geoLoc");
		}
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
		holder.setIp(geoLoc);
		suggestionService.getSuggestions(holder);
		ResponseHolder holder2 = searchService.search(holder);
		Collections.sort(((FlightRS)(holder2.getResponse())).getResponse().getResults(), new FlightPriceComparator());
		modelAndView.addObject("result", holder2);
		return modelAndView;
	}

	@RequestMapping("carReq.htm")
	public ModelAndView getCarListing(CarRequestBean formDetails,HttpServletRequest httpRequest) {
		String geoLoc = "DEL";
		if(httpRequest.getParameter("geoLoc")!=null && !"".equalsIgnoreCase(httpRequest.getParameter("geoLoc"))){
			geoLoc= httpRequest.getParameter("geoLoc");
		}
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
			holder.setIp(geoLoc);
			suggestionService.getSuggestions(holder);
			ResponseHolder holder2 = searchService.search(holder);
//			Collections.sort(((CarRS)(holder2.getResponse())).getResponse(),new CarPriceComparator());
			modelAndView.addObject("result", holder2);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	@RequestMapping("busReq.htm")
	public ModelAndView getBusListing(BusRequestBean formDetails,HttpServletRequest httpRequest) {
		String geoLoc = "DEL";
		if(httpRequest.getParameter("geoLoc")!=null && !"".equalsIgnoreCase(httpRequest.getParameter("geoLoc"))){
			geoLoc= httpRequest.getParameter("geoLoc");
		}
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
			request.setSeatType("0");
			holder.setRequest(request);
			holder.setType(ProductType.BUS);
			holder.setIp(geoLoc);
			ResponseHolder holder2 = searchService.search(holder);
//			suggestionService.getSuggestions(holder);
			WsCheckAvailability1XResponse response = (WsCheckAvailability1XResponse) holder2.getResponse();
			List<Trip> busList = response.getWsCheckAvailabilityRS().getTrip();
			Collections.sort(busList,new BusPriceComparator());
			modelAndView.addObject("result", holder2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return modelAndView;
	}

	@RequestMapping("hotelReq.htm")
	public ModelAndView getHotelListing(HotelRequestBean formDetails,HttpServletRequest httpRequest) {
		String geoLoc = "DEL";
		if(httpRequest.getParameter("geoLoc")!=null && !"".equalsIgnoreCase(httpRequest.getParameter("geoLoc"))){
			geoLoc= httpRequest.getParameter("geoLoc");
		}
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
			holder.setIp(geoLoc);
//			suggestionService.getSuggestions(holder);
			ResponseHolder holder2 = searchService.search(holder);
			Collections.sort(((HotelRS)(holder2.getResponse())).getHotels(), new HotelPriceComparator());
			modelAndView.addObject("result", holder2);
		}catch (Exception e) {
			e.printStackTrace();
		}

		return modelAndView;
	}

	@RequestMapping("addItemToCart.htm")
	public @ResponseBody
	boolean addItemToCart(@RequestParam("item") String item,
			@RequestParam("type") String type, @RequestParam("fare") int fare,
			HttpSession session) {
		ObjectMapper mapper = new ObjectMapper();

		Object cart = session.getAttribute("cart");
		if (cart != null) {
			ProductCart prodCart = (ProductCart) cart;
			if (prodCart.getProducts().containsKey(type))
				return false;
			else {
				prodCart.getProducts().put(type, item);
				try {
					HashMap map = mapper.readValue(item, HashMap.class);
					prodCart.getProducts().put(type + "M", map);
				} catch (JsonParseException e) {
					e.printStackTrace();
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				prodCart.setFare(fare);
				return true;
			}
		} else {
			ProductCart prodCart = new ProductCart();
			prodCart.setFare(fare);

			prodCart.getProducts().put(type, item);
			try {
				HashMap map = mapper.readValue(item, HashMap.class);
				prodCart.getProducts().put(type + "M", map);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			session.setAttribute("cart", prodCart);
			return true;
		}
	}

	@RequestMapping("getCart.htm")
	public @ResponseBody
	ProductCart getCart(HttpSession session) {
		return (ProductCart) session.getAttribute("cart");
	}

	public ApplicationUtil getUtil() {
		return util;
	}

	public void setUtil(ApplicationUtil util) {
		this.util = util;
	}
	
	@RequestMapping("selectBus.htm")
	public ModelAndView selectBus(
			@RequestParam(required = false, defaultValue = "", value = "data") String data) {
		ObjectMapper mapper = new ObjectMapper();
		ModelAndView modelAndView = new ModelAndView("review");
		modelAndView.addObject("data", data);

		try {
			HashMap map = mapper.readValue(data, HashMap.class);
			modelAndView.addObject("mapdata", map);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return modelAndView;
	}


}
