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
	
	private static Map<String,CityMapper> cityMapByName;
	
	private static Map<String,CityMapper> cityMapByCode;
	
	private static List<CityMapper> cityList;

	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
	}
	
	public static Map<String,CityMapper> getCityMapByName(){
		if(cityMapByName==null){
			List<CityMapper> cityList = cityMapperData.getAllCities();
			populateMapByName(cityList);
		}
		return cityMapByName;
	}
	
	public static Map<String,CityMapper> getCityMapByCode(){
		if(cityMapByCode==null){
			List<CityMapper> cityList = cityMapperData.getAllCities();
			populateMapByCode(cityList);
		}
		return cityMapByCode;
	}
	
	public static List<CityMapper> getCityList(){
		if(cityList==null){
			cityList = cityMapperData.getAllCities();
		}
		return cityList;
	}

	private static void populateMapByName(List<CityMapper> cityList) {
		cityMapByName = new HashMap<String, CityMapper>();
		if(cityList!=null && cityList.size()>0){
			for(CityMapper city:cityList){
				cityMapByName.put(city.getCtyName(), city);
			}
		}
	}
	
	private static void populateMapByCode(List<CityMapper> cityList) {
		cityMapByCode = new HashMap<String, CityMapper>();
		if(cityList!=null && cityList.size()>0){
			for(CityMapper city:cityList){
				cityMapByCode.put(city.getCtyFltcode(), city);
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
