package com.mmt.services.product.cars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.mmt.car.ws.GetLocalCarListRequest;
import com.mmt.car.ws.GetLocalCarListResponse;
import com.mmt.car.ws.GetOutstationCarListRequest;
import com.mmt.car.ws.GetOutstationCarListResponse;
import com.mmt.car.ws.SeatingCapacityList;
import com.mmt.services.product.IProductService;
import com.mmt.services.product.IRequest;
import com.mmt.services.product.IResponse;

@Component("carService")
public class CarService implements IProductService {

	@Autowired
	private WebServiceTemplate carWebServiceTemplate;

	@Autowired
	private Jaxb2Marshaller carMarshaller;

	public IResponse search(IRequest req) {
		CarRQ request = (CarRQ) req;
		IRequest carRequest = null;
		CarRS carResponse = new CarRS();
		carResponse.setServiceType(request.getServiceType());
		if(request.getServiceType().equalsIgnoreCase(ServiceType.LOCAL_USAGE)){
			carRequest = getLocalCarRequest(request);
		}else{
			carRequest = getOutStationRequest(request);
		}
		if(request.getServiceType().equalsIgnoreCase(ServiceType.LOCAL_USAGE)){
			carResponse.setResponse(((GetLocalCarListResponse)carWebServiceTemplate.marshalSendAndReceive(carRequest)).getResultsList().getResult());
		}else{
			carResponse.setResponse(((GetOutstationCarListResponse)carWebServiceTemplate.marshalSendAndReceive(carRequest)).getResultsList().getResult());
		}
		return carResponse;
	}

	private IRequest getOutStationRequest(CarRQ request) {
		GetOutstationCarListRequest carRequest = new GetOutstationCarListRequest();
		carRequest.setDate(request.getDate());
		carRequest.setFromCityCode(request.getOrigin());
		carRequest.setMonth(request.getMonth());
		SeatingCapacityList scl = new SeatingCapacityList();
		scl.getCapacity().add(4);
		carRequest.setSeatingCapacity(scl);
		carRequest.setServiceType(request.getServiceType());
		carRequest.setSortKey("price");
		carRequest.setSortOrder("asc");
		carRequest.setToCityCode(request.getDestination());
		carRequest.setYear(request.getYear());
		carRequest.setHour("23");
		carRequest.setMinute("10");
		carRequest.setDuration("01");
		carRequest.setNoOfRecords(0);
		return carRequest;
	}

	private GetLocalCarListRequest getLocalCarRequest(CarRQ request) {
		GetLocalCarListRequest carRequest = new GetLocalCarListRequest();
		carRequest.setCityCode(request.getDestination());
		carRequest.setDate(request.getDate());
		SeatingCapacityList seatingList = new SeatingCapacityList();
		seatingList.getCapacity().add(Integer.parseInt(request.getCapacity()));
		carRequest.setSeatingCapacity(seatingList);
		carRequest.setServiceType(request.getServiceType());
		carRequest.setMonth(request.getMonth());
		carRequest.setYear(request.getYear());
		carRequest.setHour("10");
		carRequest.setMinute("00");
		carRequest.setSortKey("price");
		carRequest.setSortOrder("asc");
		carRequest.setMinAmount("");
		carRequest.setMaxAmount("");
		carRequest.setNoOfRecords(0);
		return carRequest;
	}

	public WebServiceTemplate getCarWebServiceTemplate() {
		return carWebServiceTemplate;
	}

	public void setCarWebServiceTemplate(
			WebServiceTemplate carWebServiceTemplate) {
		this.carWebServiceTemplate = carWebServiceTemplate;
	}

	public Jaxb2Marshaller getCarMarshaller() {
		return carMarshaller;
	}

	public void setCarMarshaller(Jaxb2Marshaller carMarshaller) {
		this.carMarshaller = carMarshaller;
	}
}
