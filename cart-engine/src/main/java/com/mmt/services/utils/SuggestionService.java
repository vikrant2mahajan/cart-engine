package com.mmt.services.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mmt.car.ws.Result;
import com.mmt.engine.core.clients.RMIServiceLocator;
import com.mmt.engine.core.utils.Flight;
import com.mmt.search.RequestHolder;
import com.mmt.search.ResponseHolder;
import com.mmt.search.SearchService;
import com.mmt.services.product.bus.BusRQ;
import com.mmt.services.product.bus.BusRS;
import com.mmt.services.product.cars.CarRQ;
import com.mmt.services.product.cars.CarRS;
import com.mmt.services.product.cars.ServiceType;
import com.mmt.services.product.flights.FlightRQ;
import com.mmt.services.product.hotels.Hotel;
import com.mmt.services.product.hotels.HotelRQ;
import com.mmt.services.product.hotels.HotelRS;
import com.mmt.services.product.hotels.RoomRQ;
import com.mmt.util.ApplicationUtil;
import com.mmt.util.ProductType;
import com.travis.webservices.WsCheckAvailabilityRS.Trip;
import com.travis.webservices.wsticketvala.wsticketvala.WsCheckAvailability1XResponse;
@Component("suggestionService")
public class SuggestionService implements ISuggestionService{

	@Autowired
	private DistanceFinder distanceFinder;
	
	@Value("${app.suggestion.car.after.hotel}")
	private int maxCarAfterHotel;
	
	@Autowired
	private ApplicationUtil util;
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private RMIServiceLocator rmiService = null;

	@Override
	public List<Suggestion> getSuggestions(RequestHolder request) {
		List<Suggestion> suggestionList = new ArrayList<Suggestion>();
		switch (request.getType()) {
		case HOTEL:
			populateHotelRule(request, suggestionList);
			break;
		case BUS:
			populateBusRule(request, suggestionList);
			break;
		case CAR:
			populateCarRule(request, suggestionList);
			break;
		case FLIGHT:
			populateFlightRule(request, suggestionList);
			break;
		default:
			break;
		}
		return null;
	}

	// Rule1 : Hotel Bases
	private void populateHotelRule(RequestHolder request, List<Suggestion> suggestionList) {
		HotelRQ req = (HotelRQ) request.getRequest();
		String distance = findDistanceBetweenTwocities(request.getIp(),req.getCityCode());
		if(request.getIp().equalsIgnoreCase(req.getCityCode())){
			//only car
			populateCar(req.getCheckInDate(), request.getIp(), req.getCityCode(), suggestionList,"Car at door step");
		}else if(distance!=null){
			if(Integer.parseInt(distance)>maxCarAfterHotel){
				//Only flight suggestion
				populateFlight(req.getCheckInDate(), "1", request.getIp(), req.getCityCode(), suggestionList,"Flight to Hotel Destination");
				//Airport drop car
				populateCar(req.getCheckInDate(), request.getIp(), request.getIp(), suggestionList,"Airport Drop");
			}else{
				//Outstation Car
				populateCar(req.getCheckInDate(), request.getIp(),req.getCityCode(), suggestionList,"Car to Destination");
				//Bus
				populateBus(req.getCheckInDate(),request.getIp(),req.getCityCode(),"Bus to destination?",suggestionList);
			}
		}
	}
	
	private void populateFlight(String depDate, String noOfAdlt, String origin, String destination, List<Suggestion> suggestionList, String desc) {
		RequestHolder holder = new RequestHolder();
		FlightRQ request = new FlightRQ();
		request.setCabinClass("E");
		request.setDepartureDate(depDate + "T00:00:00");
//		request.setReturnDate(formDetails.getfRetDate() + "T00:00:00");
		request.setDestination(destination);
		request.setNoOfAdult(String.valueOf(noOfAdlt));
//		request.setNoOfChild(String.valueOf(formDetails.getFinfantCount()));
//		request.setNoOfInfant(String.valueOf(formDetails.getFchildCount()));
		request.setOrigin(origin);
		request.setTripType("O");
		holder.setRequest(request);
		holder.setType(ProductType.FLIGHT);
		holder.setIp("DEL");
		ResponseHolder holder2 = searchService.search(holder);
//		FlightRS response = (FlightRS) holder2.getResponse();
//		Flight flight = null;
		List<Flight> fltList = null;
		try {
			fltList= (List<Flight>) rmiService.getCheapestFlight(origin, destination, depDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Flight cheapestFlight = ApplicationUtil.getFlightPrice(fltList);
		Suggestion suggestion = new Suggestion();
		suggestion.setRequest(holder);
		suggestion.setPrice(String.valueOf(ApplicationUtil.getFlightPrice(fltList)));
		suggestion.setDescription(desc);
		suggestionList.add(suggestion);
	}

	// Rule2 : Flight Based
	private void populateFlightRule(RequestHolder request, List<Suggestion> suggestionList) {
		FlightRQ req = (FlightRQ) request.getRequest();
		String distance = findDistanceBetweenTwocities(request.getIp(),req.getOrigin());
		if(request.getIp().equalsIgnoreCase(req.getOrigin())){
			//Local usage car
			populateCar(req.getDepartureDate().split("T")[0], request.getIp(),req.getOrigin(), suggestionList,"Airport Drop");
			populateCar(req.getDepartureDate().split("T")[0], req.getDestination(),req.getDestination(), suggestionList,"Airport Pickup");
		}else if(distance!=null && Integer.parseInt(distance)<maxCarAfterHotel){
			//Outstation usage car
			populateCar(req.getDepartureDate().split("T")[0], request.getIp(),req.getOrigin(), suggestionList, "Airport Drop");
		}
		String distance1 = findDistanceBetweenTwocities(req.getOrigin(),req.getDestination());
		if(distance1!=null && Integer.parseInt(distance1)<maxCarAfterHotel){
			populateCar(req.getDepartureDate().split("T")[0], req.getOrigin(),req.getDestination(), suggestionList, "Flexible for taking a car to destination?");
			
			populateBus(req.getDepartureDate().split("T")[0],req.getOrigin(),req.getDestination(),"Flexible for taking a bus to destination?",suggestionList);
		}
		//Hotel on destination -- Done
		populateHotelOnDestination(req.getDestination(),req.getDepartureDate().split("T")[0],req.getNoOfAdult(),suggestionList,"Cheapest hotel at destination");
	}
	
	private void populateBus(String deptDate, String origin, String destination, String desc, List<Suggestion> suggestionList) {
		RequestHolder holder = new RequestHolder();
		BusRQ request = new BusRQ();
		// 22-07-2012 2012-07-25
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
		try {
			request.setDepartureDate(dateFormat2.format(dateFormat.parse(deptDate)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		request.setDestination(util.getCityMapByCode().get(destination).getCtyBuscode());
		request.setIsRoundTrip("N");
		request.setNoOfPax("1");
		request.setOrigin(util.getCityMapByCode().get(origin).getCtyBuscode());
		request.setSeatType("0");
		holder.setRequest(request);
		holder.setType(ProductType.BUS);
		holder.setIp("DEL");
		ResponseHolder holder2 = searchService.search(holder);
		if(holder2!=null && holder2.getResponse()!=null){
			WsCheckAvailability1XResponse response = (WsCheckAvailability1XResponse) holder2.getResponse();
			if(response!=null && response.getWsCheckAvailabilityRS()!=null && response.getWsCheckAvailabilityRS().getTrip()!=null
					&& response.getWsCheckAvailabilityRS().getTrip().size()>0){
				List<Trip> busList = response.getWsCheckAvailabilityRS().getTrip();
				Collections.sort(busList,new BusPriceComparator());
				Suggestion suggestion = new Suggestion();
				suggestion.setRequest(holder);
				suggestion.setPrice(busList.get(0).getSeatFare());
				suggestion.setDescription(desc);
				suggestionList.add(suggestion);
			}
		}
	}

	// Rule3 : Bus Based
	private void populateBusRule(RequestHolder request, List<Suggestion> suggestionList) {
		BusRQ req = (BusRQ) request.getRequest();
		//Hotel on destination --Done
		populateHotelOnDestination(util.getCityMapByBusCode().get(req.getDestination()).getCtyHtlcode(), changeDateFormat(req.getDepartureDate().split("T")[0]),req.getNoOfPax(),suggestionList,
				"Cheapest hotel at destination");
		//Figure out car's fare and compare with bus
		populateCar(changeDateFormat(req.getDepartureDate()), util.getCityMapByBusCode().get(req.getOrigin()).getCtyCarcode(), 
				util.getCityMapByBusCode().get(req.getDestination()).getCtyCarcode(), suggestionList,"You can think about taking car");
	}
	
	private String changeDateFormat(String string) {
		String[] date = string.split("-");
		return date[2]+"-"+date[1]+"-"+date[0];
	}

	private void populateCar(String depDate, String origin, String destination, List<Suggestion> suggestionList, String desc) {
		RequestHolder holder = new RequestHolder();
		CarRQ request = new CarRQ();
		String[] dateComponents = depDate.split("-");

		request.setDate(dateComponents[2]);
		request.setDestination(destination);
		request.setMonth(dateComponents[1]);
		request.setYear(dateComponents[0]);
		request.setOrigin(origin);
		request.setCapacity("4");
		request.setServiceType(ServiceType.OUTSTATION_USAGE);
		if(origin.equalsIgnoreCase(destination)){
			request.setServiceType(ServiceType.LOCAL_USAGE);
			request.setOrigin(null);
		}
		holder.setRequest(request);
		holder.setType(ProductType.CAR);
		holder.setIp("DEL");
		ResponseHolder holder2 = searchService.search(holder);
		CarRS response = (CarRS) holder2.getResponse();
		if(response!=null && response.getResponse()!=null && response.getResponse().size()>0){
			Result cheapestCar = response.getResponse().get(0);
			Suggestion suggestion = new Suggestion();
			suggestion.setRequest(holder);
			suggestion.setPrice(cheapestCar.getBookingPriceToDisplay());
			suggestion.setImgUrl(cheapestCar.getCarLogo());
			suggestion.setDescription(desc);
			suggestionList.add(suggestion);
		}
	}

	private Result populateCheapestCar(List<Result> response) {
		Collections.sort(response, new CarPriceComparator());
		return response.get(0);
	}

	// Rule4 : Car Based
	private void populateCarRule(RequestHolder request, List<Suggestion> suggestionList) {
		CarRQ req = (CarRQ) request.getRequest();
		String distance = findDistanceBetweenTwocities(request.getIp(),req.getOrigin());
		if(request.getIp().equalsIgnoreCase(req.getOrigin())){
			//Do nothing
		}else if(distance!=null && Integer.parseInt(distance)>maxCarAfterHotel){
			//Flight
			populateFlight(req.getYear()+"-"+req.getMonth()+"-"+req.getDate(),"1", request.getIp(), req.getOrigin(), suggestionList,"You might wanna fly to your car location");
		}
		//Hotel on destination -- Done
		populateHotelOnDestination(req.getDestination(),populateCheckinDate(req.getDate(),req.getMonth(),req.getYear()),"1",suggestionList,"Cheapest hotel at destination");
	}
	
	private void populateHotelOnDestination(String destination,String populateCheckinDate,String noOfAdlt,List<Suggestion> suggestionList, String desc) {
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(populateCheckinDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.add(Calendar.DAY_OF_MONTH, 2);
		RequestHolder holder = new RequestHolder();
		HotelRQ request = new HotelRQ();
		request.setCheckInDate(populateCheckinDate);
		request.setCheckOutDate(sdf.format(cal.getTime()));
		request.setCityCode(destination);
		RoomRQ roomRq = new RoomRQ();
		roomRq.setNoOfAdult(noOfAdlt);
		roomRq.setNoOfChild("0");
		request.getNoOfRooms().add(roomRq);
		holder.setType(ProductType.HOTEL);
		holder.setRequest(request);
		holder.setIp("DEL");
		ResponseHolder holder2 = searchService.search(holder);
		Suggestion suggestion = new Suggestion();
		suggestion.setRequest(holder);
		HotelRS response = (HotelRS) holder2.getResponse();
//		List<Hotel> threeStarHotelRating  = populateHotelList(response.getHotels());
		Collections.sort(response.getHotels(), new HotelPriceComparator());
		suggestion.setPrice(response.getHotels().get(0).getLowestRate());
		suggestion.setImgUrl(response.getHotels().get(0).getUrlPic());
		suggestion.setDescription(desc);
		suggestionList.add(suggestion);
	}

	private List<Hotel> populateHotelList(List<Hotel> hotels) {
		List<Hotel> hotelList = new ArrayList<Hotel>();
		if(hotels!=null && hotels.size()>0){
			for(Hotel hotel:hotels){
				if(Integer.parseInt(hotel.getStarRating())==3){
					hotelList.add(hotel);
				}
			}
		}
		return hotelList;
	}

	private String populateCheckinDate(String date, String month, String year) {
		return year+"-"+month+"-"+date;
	}

	private String findDistanceBetweenTwocities(String fromCity, String toCity){
		distanceFinder.setFromCity(fromCity);
		distanceFinder.setToCity(toCity);
		return distanceFinder.getDistance();
	}

	public DistanceFinder getDistanceFinder() {
		return distanceFinder;
	}

	public void setDistanceFinder(DistanceFinder distanceFinder) {
		this.distanceFinder = distanceFinder;
	}

	public ApplicationUtil getUtil() {
		return util;
	}

	public void setUtil(ApplicationUtil util) {
		this.util = util;
	}

	public SearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}
	
	public RMIServiceLocator getRmiService() {
		return rmiService;
	}

	public void setRmiService(RMIServiceLocator rmiService) {
		this.rmiService = rmiService;
	}

}
