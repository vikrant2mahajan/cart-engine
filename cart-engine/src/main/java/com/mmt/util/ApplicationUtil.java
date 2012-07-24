package com.mmt.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.mmt.data.dao.api.CityMapperData;
import com.mmt.data.models.CityMapper;
import com.mmt.engine.core.utils.CFlightDiscountUtils;
import com.mmt.engine.core.utils.Flight;
import com.mmt.engine.core.utils.FlightCombination;
import com.mmt.engine.core.utils.Quote;

@Component("util")
public class ApplicationUtil implements ApplicationContextAware, ServletContextAware {

	@Autowired
	private CityMapperData cityMapperData;
	
	private static ApplicationContext context = null;
	
	private static Map<String,CityMapper> cityMapByName;
	
	private static Map<String,CityMapper> cityMapByCode;
	
	private static Map<String,CityMapper> cityMapByBusCode;
	
	private static List<CityMapper> cityList;

	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
	}
	
	public Map<String,CityMapper> getCityMapByName(){
		if(cityMapByName==null){
			List<CityMapper> cityList = cityMapperData.getAllCities();
			populateMapByName(cityList);
		}
		return cityMapByName;
	}
	
	public Map<String,CityMapper> getCityMapByCode(){
		if(cityMapByCode==null){
			List<CityMapper> cityList = cityMapperData.getAllCities();
			populateMapByCode(cityList);
		}
		return cityMapByCode;
	}
	
	public Map<String,CityMapper> getCityMapByBusCode(){
		if(cityMapByBusCode==null){
			List<CityMapper> cityList = cityMapperData.getAllCities();
			populateMapByBusCode(cityList);
		}
		return cityMapByBusCode;
	}
	
	private void populateMapByBusCode(List<CityMapper> cityList2) {
		cityMapByBusCode = new HashMap<String, CityMapper>();
		if(cityList2!=null && cityList2.size()>0){
			for(CityMapper city:cityList2){
				cityMapByBusCode.put(city.getCtyBuscode(), city);
			}
		}
	}

	public List<CityMapper> getCityList(){
		if(cityList==null){
			cityList = cityMapperData.getAllCities();
		}
		return cityList;
	}

	private void populateMapByName(List<CityMapper> cityList) {
		cityMapByName = new HashMap<String, CityMapper>();
		if(cityList!=null && cityList.size()>0){
			for(CityMapper city:cityList){
				cityMapByName.put(city.getCtyName(), city);
			}
		}
	}
	
	private void populateMapByCode(List<CityMapper> cityList) {
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

	@Override
	public void setServletContext(ServletContext servletContext) {
		servletContext.setAttribute("cityMapByCode", getCityMapByCode());
		servletContext.setAttribute("cityMapByName", getCityMapByName());
		servletContext.setAttribute("cityMapByBusCode", getCityMapByBusCode());
	}
}
