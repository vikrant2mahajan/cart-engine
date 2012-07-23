package com.mmt.util;

import com.mmt.services.product.flights.FlightRQ;
import com.mmt.services.product.flights.FlightService;
import com.mmt.services.utils.DistanceFinder;

public class AppInit {
	public static void main(String[] args) {
		DistanceFinder flight = (DistanceFinder) ApplicationUtil.getBean("distanceFinder");
		
		//Flights
		/*FlightService flight = (FlightService) ApplicationUtil.getBean("flightService");
		FlightRQ request = new FlightRQ();
		request.setCabinClass("E");
		request.setDepartureDate("2012-07-25T00:00:00");
		request.setDestination("BOM");
		request.setNoOfAdult("1");
		request.setOrigin("DEL");
		request.setTripType("O");
		flight.search(request);*/
		
		//Car
		
		/*CarService car = (CarService) ApplicationUtil.getBean("carService");
		CarRQ request = new CarRQ();
		request.setCapacity("4");
		request.setDate("06");
		request.setDestination("AGR");
		request.setMonth("08");
		request.setYear("2012");
		request.setOrigin("DEL");
		request.setCapacity("4");
		request.setServiceType(ServiceType.OUTSTATION_USAGE);
		car.search(request);*/
		
		//Bus
//		BusService busImpl = (BusService) ApplicationUtil.getBean("busService");
		//Citi request
		//		busImpl.getCities();
		
		//Bus Search
		/*BusRQ request = new BusRQ();
		request.setDepartureDate("22-07-2012");
		request.setDestination("343");
		request.setIsRoundTrip("N");
		request.setNoOfPax("1");
		request.setOrigin("1");
		request.setSeatType("1");
		busImpl.search(request);
		*/
		
		//GetBus CityMap
//		busImpl.getSeatMap("43461807");
//		busImpl.getSeatMap("40001515");
		
		
		// Hotel 
		/*IProductService service =  (IProductService) ApplicationUtil.getBean("hotelService");
		HotelRQ request = new HotelRQ();
		request.setCheckInDate("2012-07-25");
		request.setCheckOutDate("2012-07-26");
		request.setCityCode("BOM");
		RoomRQ roomRq = new RoomRQ();
		roomRq.setNoOfAdult("1");
		request.getNoOfRooms().add(roomRq);
		IResponse response = service.search(request);
		System.out.println("");*/
	}
}
