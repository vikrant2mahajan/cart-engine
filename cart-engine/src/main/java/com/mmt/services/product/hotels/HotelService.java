package com.mmt.services.product.hotels;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mmt.hotel.entity.MMTHotelSearchRequest;
import com.mmt.hotel.entity.MMTHotelSearchRequest.SearchCriteria;
import com.mmt.hotel.entity.MMTHotelSearchRequest.SearchCriteria.Criterion;
import com.mmt.hotel.entity.MMTHotelSearchRequest.SearchCriteria.Criterion.Area;
import com.mmt.hotel.entity.MMTHotelSearchRequest.SearchCriteria.Criterion.RoomStayCandidates;
import com.mmt.hotel.entity.MMTHotelSearchRequest.SearchCriteria.Criterion.RoomStayCandidates.RoomStayCandidate;
import com.mmt.hotel.entity.MMTHotelSearchRequest.SearchCriteria.Criterion.RoomStayCandidates.RoomStayCandidate.GuestCounts;
import com.mmt.hotel.entity.MMTHotelSearchRequest.SearchCriteria.Criterion.RoomStayCandidates.RoomStayCandidate.GuestCounts.GuestCount;
import com.mmt.hotel.entity.MMTHotelSearchRequest.SearchCriteria.Criterion.StayDateRanges;
import com.mmt.hotel.entity.MMTHotelSearchRequest.SearchCriteria.Criterion.StayDateRanges.StayDateRange;
import com.mmt.hotel.entity.MMTHotelSearchResponse;
import com.mmt.search.ResponseHolder;
import com.mmt.services.product.IProductService;
import com.mmt.services.product.IRequest;
import com.mmt.services.product.IResponse;

@Component("hotelService")
public class HotelService implements IProductService {

	@Autowired
	RestTemplate restTemplate;

	@Value("${app.hotel.url}")
	private String htlUrl;

	public IResponse search(IRequest req) {
		MMTHotelSearchRequest hotelRequest = createHotelSearchRequest(req);
		ResponseEntity<MMTHotelSearchResponse> response = restTemplate
				.postForEntity(htlUrl, hotelRequest,
						MMTHotelSearchResponse.class);
		return populateHotelResponse(response.getBody(), req);
	}

	private IResponse populateHotelResponse(MMTHotelSearchResponse response,
			IRequest req) {
		HotelRSParser parser = new HotelRSParser(response, req);
		HotelRS hotelRS= parser.parse();
		prune(hotelRS);
		return hotelRS;
	}

	
	private void prune(HotelRS hotelRS) {
		if (hotelRS != null && hotelRS.getHotels().size() > 0) {
			Iterator<Hotel> iterator = hotelRS.getHotels().iterator();

			while (iterator.hasNext()) {
				Hotel hotel = iterator.next();
				if (hotel.getStarRating().equals("1")
						|| hotel.getStarRating().equals("2")) {
					iterator.remove();
				}

			}

		}
	}

	
	private MMTHotelSearchRequest createHotelSearchRequest(IRequest req) {
		HotelRQ hotelReq = (HotelRQ) req;
		MMTHotelSearchRequest request = new MMTHotelSearchRequest();
		request.getPOSOrPromotionReferenceCodesOrResultTransformer().add(
				HotelUtil.getPromotionReferenceCodes());
		request.getPOSOrPromotionReferenceCodesOrResultTransformer().add(
				HotelUtil.getPos());
		request.getPOSOrPromotionReferenceCodesOrResultTransformer().add(
				createCriteria(hotelReq));
		request.getPOSOrPromotionReferenceCodesOrResultTransformer().add(
				HotelUtil.getResultTransformer());
		return request;
	}

	private SearchCriteria createCriteria(HotelRQ req) {
		SearchCriteria criteria = new SearchCriteria();
		criteria.getCriterion().add(createCriterion(req));
		return criteria;
	}

	private Criterion createCriterion(HotelRQ req) {
		Criterion cr = new Criterion();
		cr.getArea().add(createArea(req));
		cr.getRoomStayCandidates().add(createRoomStayCandidates(req));
		cr.getStayDateRanges().add(createStayDateRange(req));
		return cr;
	}

	private StayDateRanges createStayDateRange(HotelRQ req) {
		StayDateRanges sdrs = new StayDateRanges();
		StayDateRange sdr = new StayDateRange();
		sdr.setStart(req.getCheckInDate());
		sdr.setEnd(req.getCheckOutDate());
		sdrs.getStayDateRange().add(sdr);
		return sdrs;
	}

	private RoomStayCandidates createRoomStayCandidates(HotelRQ req) {
		RoomStayCandidates rsc = new RoomStayCandidates();
		if (req.getNoOfRooms() != null && req.getNoOfRooms().size() > 0) {
			for (RoomRQ roomRq : req.getNoOfRooms()) {
				RoomStayCandidate rs = new RoomStayCandidate();
				rs.getGuestCounts().add(createGuestCounts(roomRq));
				rsc.getRoomStayCandidate().add(rs);
			}
		}
		return rsc;
	}

	private GuestCounts createGuestCounts(RoomRQ req) {
		GuestCounts gcs = new GuestCounts();
		GuestCount gc = new GuestCount();
		gc.setAgeQualifyingCode("10");
		gc.setCount(req.getNoOfAdult());
		gcs.getGuestCount().add(gc);
		return gcs;
	}

	private Area createArea(HotelRQ req) {
		Area area = new Area();
		area.setCityCode(req.getCityCode());
		area.setCountryCode("IN");
		return area;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
}
