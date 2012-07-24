<%@ page isELIgnored="false"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.mobile-1.1.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.21.custom.min.js"></script>
<script type="text/javascript" src="js/application.js"></script>

<link rel="stylesheet" href="css/jquery.mobile-1.1.1.min.css" />
<link rel="stylesheet" href="css/jquery.mobile.structure-1.1.1.min.css" />
<link rel="stylesheet" href="css/jquery.mobile.theme-1.1.1.min.css" />
<link rel="stylesheet" href="css/jqm-docs.css" />
<link rel="stylesheet" href="css/styles.css" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Review</title>
</head>
<body>

	<div data-role="page" class="type-interior">
	
		<form id="suggestionForm" action="getSuggestions.htm" method="get">
			<input type="hidden" name="data" id="suggestionFormData"/>
			
		</form>

		<form id="finalReview" action="getFinalReview.htm" method="get">
			<input type="hidden" name="data" id="finalData"/>
			
		</form>
	
	
	
		<script type="text/javascript">
			var currentProduct = ${data};
		</script>
		<div data-role="header" data-position="fixed" class="cart_panel"
			data-fullscreen=true>
			<div class="ui-grid-e ">

				<div class="ui-block-a cart"></div>
				
				
				
				<c:if test="${not empty cart and not empty cart.products['BUS']}">
				<script>
				cart['BUS']=${cart.products['BUS']};
				cart['FARE']=${cart.fare};
				</script>
					<div style="" data-type="BUS"
						class="ui-block-b ui-btn ui-shadow  ui-btn-inline ui-btn-up-c ui-btn-corner-all cart_item ui-btn-up-undefined">
						<span title="Remove" class="cart_delete"></span>
						<div align="center">
							<img width="30px" height="30px;" src="css/images/bus(2).png"
								alt="BUS">
						</div>
						<p class="heavy">${cart.products['BUSM'].groupName}</p>
						<p>${cart.products['BUSM'].fromCity} to
							${cart.products['BUSM'].toCity}</p>
					</div>
				</c:if>



				<c:if test="${not empty cart and not empty cart.products['FLIGHT']}">
				<script>
				cart['FLIGHT']=${cart.products['FLIGHT']};				
				</script>
					
					<div data-type="FLIGHT" style="" class="ui-block-b ui-btn ui-shadow  ui-btn-inline ui-btn-up-c ui-btn-corner-all cart_item ui-btn-up-undefined">
						<span title="Remove" class="cart_delete"></span>	
						<div align="center"><img width="50%" height="30px;" src="css/images/${cart.products['FLIGHTM'].carrierCode}.png" alt="FLIGHT">
						</div><p class="heavy">${cart.products['FLIGHTM'].airlineName}</p>	<p>${cart.products['FLIGHTM'].fromCity} to ${cart.products['FLIGHTM'].toCity}</p>	<p>${cart.products['FLIGHTM'].departure} - ${cart.products['FLIGHTM'].arrival}</p></div>
				</c:if>


				<c:if test="${not empty cart and not empty cart.products['HOTEL']}">
				<script>
				cart['HOTEL']=${cart.products['HOTEL']};				
				</script>
			
			<div style="" data-type="HOTEL" class="ui-block-b ui-btn ui-shadow  ui-btn-inline ui-btn-up-c ui-btn-corner-all cart_item ui-btn-up-undefined">	
			<span title="Remove" class="cart_delete"></span>	
			<div align="center"><img width="50%" height="50px;" src=" ${cart.products['HOTELM'].imageUrl}" alt="BUS">
			</div><p class="heavy">${cart.products['HOTELM'].name} ( ${cart.products['HOTELM'].rating} &#9733; )</p></div>


				</c:if>
	
	
				<c:if test="${not empty cart and not empty cart.products['CAR']}">
				<script>
				cart['CAR']=${cart.products['CAR']};				
				</script>
					<div data-type="CAR" style="" class="ui-block-b ui-btn ui-shadow  ui-btn-inline ui-btn-up-c ui-btn-corner-all cart_item ui-btn-up-undefined">
						<span title="Remove" class="cart_delete"></span>	
						<div align="center"><img width="50%" height="30px;" src=${cart.products['CARM'].logo} alt="FLIGHT">
						</div><p class="heavy">${cart.products['CARM'].name}</p>	<p>${cart.products['CARM'].fromCity} to ${cart.products['CARM'].toCity}</p></div>
				</c:if>



				<div class="ui-block-a fare"
				
					<c:if test="${empty cart or(empty cart.products['CAR'] and empty cart.products['BUS'] and empty cart.products['FLIGHT'] and empty cart.products['HOTEL'])}">style="display: none;"</c:if>>
					<div class="ui-grid-a ">
						<div class="ui-block-a" align="right">
							<strong>Grand Total:</strong>
							<div class="big">
								<span class="WebRupee">Rs.</span><span> ${cart.fare }</span>
							</div>
						</div>
						<div class="ui-block-b" align="right">
							<a href="#cart Check Out" data-role="button" data-icon="arrow-r" class="completeCheckOut"
								data-theme="e" data-inline="true">Cart Check Out</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /footer -->



		<div data-role="content" align="center">


			<div class="ui-grid-d heading" data-role="header" data-theme="b">
				<div class="ui-block-a ">Product</div>
				<div class="ui-block-b">City</div>
				<c:if test="${mapdata.type=='HOTEL'}">
					<div class="ui-block-c">Facilites</div>
				</c:if>
				<c:if test="${ mapdata.type=='CAR' or not mapdata.type=='BUS' or not mapdata.type=='FLIGHT'}">
					<div class="ui-block-c">Time</div>
				</c:if>
				<div class="ui-block-d">Fare</div>
			</div>
			</br>


			<c:if test="${mapdata.type=='FLIGHT'}">
			<div class="ui-grid-d ${mapdata.type }_product">
					<c:if test="${mapdata.type=='FLIGHT'}">
						<div align="center" class="ui-block-a">
							<img width="30%" height="30%" alt="BUS"
								src="css/images/${mapdata.carrierCode}.png"><br /> <strong>${mapdata.airlineName}</strong><br /> ${mapdata.carrierCode} - ${mapdata.flightNumber}
						</div>
					</c:if>

					<div class="ui-block-b">
						<br /> <br /> ${mapdata.fromCity} - ${mapdata.toCity}
					</div>
					<div class="ui-block-c">
						<br /> <br /> ${mapdata.departure} - ${mapdata.arrival}
					</div>
					<div class="ui-block-d">
						<br /> <br /> <strong class="big"><span class="WebRupee">Rs</span> <fmt:formatNumber
								type="number" value="${mapdata.fare}" /></strong>
					</div>
				</div>
			</c:if>
			
			
			<c:if test="${mapdata.type=='BUS'}">
				<div class="ui-grid-d ${mapdata.type }_product">
					<c:if test="${mapdata.type=='BUS'}">
						<div align="center" class="ui-block-a">
							<img width="30%" height="30%" alt="BUS"
								src="css/images/bus(2).png"><br /> <strong>${mapdata.groupName}</strong><br /> ${mapdata.seatType}
						</div>
					</c:if>

					<div class="ui-block-b">
						<br /> <br /> ${mapdata.fromCity} - ${mapdata.toCity}
					</div>
					<div class="ui-block-c">
						<br /> <br /> ${mapdata.departureTime}
					</div>
					<div class="ui-block-d">
					
								<c:if test="${not empty mapdata.seaterFare}">								
								 	<c:set var="busFare" value="${mapdata.seaterFare}"></c:set>
								</c:if>
								<c:if test="${not empty mapdata.sleeperFare and empty mapdata.seaterFare }">
								 	<c:set var="busFare" value="${mapdata.sleeperFare}"></c:set>
								</c:if>
					
					
						<br /> <br /> <strong class="big"><span class="WebRupee">Rs</span> <fmt:formatNumber
								type="number" value="${busFare}" /></strong>
					</div>
				</div>

			</c:if>


				<c:if test="${mapdata.type=='CAR'}">
				<div class="ui-grid-d ${mapdata.type }_product">
					<c:if test="${mapdata.type=='CAR'}">
						<div align="center" class="ui-block-a">
							<strong>${mapdata.name}</strong><br /><br /><br />
							<img width="30%" height="30%" alt="car"
								src="${mapdata.logo}"> 
						</div>
					</c:if>

					<div class="ui-block-b">
						<br /> <br /> ${mapdata.fromCity} - ${mapdata.toCity}
					</div>
					
					<div class="ui-block-c">
						<br /> <br /> Morning
					</div>
					
					<div class="ui-block-d">
						<br /> <br /> <strong class="big"><span class="WebRupee">Rs</span> <fmt:formatNumber
								type="number" value="${mapdata.fare}" /></strong>
					</div>
				</div>

			</c:if>




			<c:if test="${mapdata.type=='HOTEL'}">
				<div class="ui-grid-d ${mapdata.type }_product">
					<c:if test="${mapdata.type=='HOTEL'}">
						<div align="center" class="ui-block-a">
							<img width="30%" height="30%" alt="BUS"
								src="${mapdata.imageUrl }"><br /> <strong>${mapdata.name}</strong><br />
								<div style="width: 90px;">
								<c:forEach var="rating" begin="1" end="${mapdata.rating}" varStatus="status">
											<div class="star-rating star-rating-on"></div>
										</c:forEach>
										<c:forEach var="rating" begin="1" end="${5-mapdata.rating}" varStatus="status">
											<div class="star-rating star-rating"></div>
								</c:forEach>
								</div>
								
								 
						</div>
					</c:if>
					<div class="ui-block-b">
						<br /> <br /> ${mapdata.fromCity}<br/>
						<fmt:parseDate value="${mapdata.checkinDate }"	var="depDate" pattern="yyyy-MM-dd" />
						<fmt:formatDate pattern="EEEE ,MMMM dd yyyy" value="${depDate}" />
						
						
					</div>
					<div class="ui-block-c" align="center">
						<br /> <br />
						
						<div style="width:150px;">
									<c:if test="${mapdata.restaurantOrBar}">
										<div title="Restaurant/Bar" class="amenities am_active_1"></div>
									</c:if>
									<c:if test="${empty mapdata.restaurantOrBar}">
										<div title="No Restaurant/Bar" class="amenities am_inactive_1"></div>
									</c:if>
									<c:if test="${mapdata.internet}">
										<div title="Internet/Wi-Fi" class="amenities am_active_2"></div>
									</c:if>
									<c:if test="${empty mapdata.internet}">
										<div title="No Internet/Wi-Fi" class="amenities am_inactive_2"></div>
									</c:if>
									<c:if test="${mapdata.recreationAvail}">
										<div title="Recreation" class="amenities am_active_3"></div>
									</c:if>
									<c:if test="${empty mapdata.recreationAvail}">
										<div title="No Recreation" class="amenities am_inactive_3"></div>
									</c:if>
									<c:if test="${mapdata.swimmingPoolAvail}">
										<div title="Swimming Pool" class="amenities am_active_4"></div>
									</c:if>
									<c:if test="${empty mapdata.swimmingPoolAvail}">
										<div title="No Swimming Pool" class="amenities am_inactive_4"></div>
									</c:if>
									<c:if test="${mapdata.parkingAvail}">
										<div title="Parking Facility" class="amenities am_active_5"></div>
									</c:if>
									<c:if test="${empty mapdata.parkingAvail}">
										<div title="No Parking Facility" class="amenities am_inactive_5"></div>
									</c:if>								
						</div>
						
					</div>
					<div class="ui-block-d">
						<br /> <br /> <strong class="big"><span class="WebRupee">Rs</span> <fmt:formatNumber
								type="number" value="${mapdata.fare}" /></strong>
					</div>
				</div>

			</c:if>











			<div class="ui-grid-a panel">
				<div class="ui-block-a" align="left">
					<a href="#plan" data-role="button" data-icon="trip" id="planTravel"   data-rel="dialog"
						data-inline="true">Plan My Trip</a>

				</div>

				<div class="ui-block-b" align="right">
					<a href="#checkout" data-role="button" data-icon="arrow-r" id="checkOut"
						data-theme="e" data-inline="true">Check Out</a>

				</div>

			</div>





		</div>


	</div>
	
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>