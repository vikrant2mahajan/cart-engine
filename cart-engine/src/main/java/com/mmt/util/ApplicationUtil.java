package com.mmt.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.ibm.wsdl.extensions.PopulatedExtensionRegistry;
import com.mmt.data.dao.api.CityMapperData;
import com.mmt.data.models.CityMapper;
import com.mmt.engine.core.utils.CFlightDiscountUtils;
import com.mmt.engine.core.utils.Flight;
import com.mmt.engine.core.utils.FlightCombination;
import com.mmt.engine.core.utils.Quote;

public class ApplicationUtil implements ApplicationContextAware {

	@Autowired
	private static CityMapperData cityMapperData;
	
	private static ApplicationContext context = null;
	
	private static Map<String,CityMapper> cityMap = null;

	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
	}
	
	public static Map<String,CityMapper> getCityMap(){
		if(cityMap==null){
			List<CityMapper> cityList = cityMapperData.getAllCities();
			populateMap(cityList);
		}
		return cityMap;
	}

	private static void populateMap(List<CityMapper> cityList) {
		cityMap = new HashMap<String, CityMapper>();
		if(cityList!=null && cityList.size()>0){
			for(CityMapper city:cityList){
				cityMap.put(city.getCtyName(), city);
			}
		}
	}

	public static double getCombinationPrice(FlightCombination combination) {
		double price = 0.0;
		for (Flight flight : combination.getFlightSegmentList()) {
			Quote qt = flight.getQuotesList().get(0);
			price += CFlightDiscountUtils.calculateFinalAdultFare(qt);
		}
		return price;
	}

	public static double getFlightPrice(List<Flight> flights) {
		double fare = 0;
		for (Flight flight : flights) {
			Quote qt = flight.getQuotesList().get(0);
			fare += CFlightDiscountUtils.calculateFinalAdultFare(qt);
		}
		return fare;
	}

	public static String getDuration(Date d1, Date d2) {
		long diff = (d2.getTime() - d1.getTime());
		String hour = (int) (diff / (60 * 60 * 1000)) + "h";
		String min = (int) ((diff % (60 * 60 * 1000))/60000) + "m";
		return hour + " " + min;

	}

	public CityMapperData getCityMapperData() {
		return cityMapperData;
	}

	public void setCityMapperData(CityMapperData cityMapperData) {
		this.cityMapperData = cityMapperData;
	}

}
