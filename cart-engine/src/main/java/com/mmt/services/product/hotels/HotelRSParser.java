package com.mmt.services.product.hotels;

import java.util.ArrayList;
import java.util.List;

import com.mmt.hotel.entity.MMTHotelSearchResponse;
import com.mmt.hotel.entity.MMTHotelSearchResponse.HotelSearchResults;
import com.mmt.hotel.entity.MMTHotelSearchResponse.HotelSearchResults.Hotels;
import com.mmt.hotel.entity.MMTHotelSearchResponse.HotelSearchResults.Hotels.Hotel;
import com.mmt.hotel.entity.MMTHotelSearchResponse.HotelSearchResults.Hotels.Hotel.PropertyInfo;
import com.mmt.hotel.entity.MMTHotelSearchResponse.HotelSearchResults.Hotels.Hotel.PropertyInfo.Facets;
import com.mmt.hotel.entity.MMTHotelSearchResponse.HotelSearchResults.Hotels.Hotel.PropertyInfo.Facets.Facet;
import com.mmt.hotel.entity.MMTHotelSearchResponse.HotelSearchResults.Hotels.Hotel.PropertyInfo.Facets.Facet.FacetValues;
import com.mmt.hotel.entity.MMTHotelSearchResponse.HotelSearchResults.Hotels.Hotel.PropertyInfo.Facets.Facet.FacetValues.FacetValue;
import com.mmt.hotel.entity.MMTHotelSearchResponse.HotelSearchResults.Hotels.Hotel.PropertyInfo.MediaList;

public class HotelRSParser {

	private MMTHotelSearchResponse body;

	public HotelRSParser(MMTHotelSearchResponse body) {
		super();
		this.body = body;
	}

	public HotelRS parse() {
		HotelRS response = new HotelRS();
		List<com.mmt.services.product.hotels.Hotel> respHotelList = new ArrayList<com.mmt.services.product.hotels.Hotel>();
		List<HotelSearchResults> results = body.getHotelSearchResults();
		if (results != null && results.size() > 0) {
			List<Hotels> hotelsList = results.get(0).getHotels();
			if (hotelsList != null && hotelsList.size() > 0) {
				Hotels hotels = hotelsList.get(0);
				List<Hotel> hotelList = hotels.getHotel();
				if (hotelList != null && hotelList.size() > 0) {
					for (Hotel hotel : hotelList) {
						com.mmt.services.product.hotels.Hotel myHotel = createHotel(hotel);
						if (myHotel != null) {
							respHotelList.add(myHotel);
						}
					}
				}
			}
		}
		response.setHotels(respHotelList);
		return response;
	}

	private com.mmt.services.product.hotels.Hotel createHotel(Hotel hotel) {
		com.mmt.services.product.hotels.Hotel ourHotel = new com.mmt.services.product.hotels.Hotel();
		ourHotel.setHotelId(hotel.getId());
		ourHotel.setHotelName(hotel.getName());
		if(hotel.getLowestRate()!=null 
				&& hotel.getLowestRate().size()>0
				&& hotel.getLowestRate().get(0)!=null 
				&& hotel.getLowestRate().get(0).getLowestRate()!=null
				&& hotel.getLowestRate().get(0).getLowestRate().size()>0
				&& hotel.getLowestRate().get(0).getLowestRate().get(0)!=null){
			ourHotel.setLowestRate(hotel.getLowestRate().get(0).getLowestRate().get(0).getValue());
		}else{
			return null;
		}
		PropertyInfo prop = hotel.getPropertyInfo().get(0);
		if(prop!=null){
			List<Facets> facets = prop.getFacets();
			if(facets!=null && facets.size()>0){
				Facets face = facets.get(0);
				if(face!=null){
					for(Facet facet: face.getFacet()){
						String groupName = facet.getGroup();
						if(groupName.equalsIgnoreCase("starRating")){
							ourHotel.setStarRating(facet.getFacetValue());
						}else if(groupName.equalsIgnoreCase("amenities")){
							List<FacetValues> facetValues = facet.getFacetValues();
							if(facetValues!=null && facetValues.size()>0){
								
								for(FacetValues fvalues:facetValues){
									List<FacetValue> fvList = fvalues.getFacetValue();
									if(fvList!=null && fvList.size()>0){
										for(FacetValue fv:fvList){
											if(fv.getValue().contains("Restaurant") || fv.getValue().contains("Bar")||fv.getValue().contains("Coffee Shop")){
												ourHotel.setRestaurantOrBar(true);
											}
											if(fv.getValue().contains("Internet") || fv.getValue().contains("Internet Services")
													||fv.getValue().contains("Internet Facility")||fv.getValue().contains("Wifi Enabled")){
												ourHotel.setInternet(true);
											}
											if(fv.getValue().contains("Gymnasium") || fv.getValue().contains("Spa")|| fv.getValue().contains("Lounge")
													|| fv.getValue().contains("Massage Centre")|| fv.getValue().contains("Yoga Classes")|| fv.getValue().contains("Yoga Meditation Centre")){
												ourHotel.setRecreationAvail(true);
											}
											if(fv.getValue().contains("Swimming Pool") || fv.getValue().contains("Kids Pool")||fv.getValue().contains("Sun beds (pool)")){
												ourHotel.setSwimmingPoolAvail(true);
											}
											if(fv.getValue().contains("Parking Facility") || fv.getValue().contains("Parking")){
												ourHotel.setParkingAvail(true);
											}
										}
									}
									
								}
							}
						}
					}
				}
			}
			
		List<MediaList> mediaList =prop.getMediaList();
		if(mediaList!=null && mediaList.size()>0){
			for(MediaList list:mediaList){
				ourHotel.setUrlPic(list.getMedia().get(0).getSrc());
			}
			
		}
		}
		return ourHotel;
	}
}
