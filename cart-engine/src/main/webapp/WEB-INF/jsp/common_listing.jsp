<%@page import="java.util.Date"%>
<%@page import="com.mmt.engine.core.utils.FlightCombination"%>
<%@page import="com.mmt.util.ApplicationUtil"%>
<%@ page isELIgnored="false"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>

<head>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.mobile-1.1.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.21.custom.min.js"></script>
<link rel="stylesheet" href="css/jquery.mobile-1.1.1.min.css" />
<link rel="stylesheet" href="css/jquery.mobile.structure-1.1.1.min.css" />
<link rel="stylesheet" href="css/styles.css" />
<!-- <link rel="stylesheet" href="css/jquery.mobile.theme-1.1.1.min.css" /> -->
<link rel="stylesheet" href="css/jqm-docs.css" />
<style type="text/css">
.ui-effects-transfer {
	border: 1px solid black;
	background: #FAFAFA;
	opacity: 0.5;
}
</style>
</head>

<body>
	<h2>Hello World!</h2>
	<div data-role="page" class="type-interior">

		<div data-role="content">
		</br>
				<ul data-role="listview" data-split-icon="arrow-r"
					data-split-theme="d" data-theme="d" data-divider-theme="d"
					data-filter="true"
					data-filter-placeholder="Filter by airline / refundable / stops"
					data-inset="true">


					<c:if test="${result.type=='FLIGHT'}">
						<c:forEach var="flight"
							items="${result.response.response.searchResult.results }"
							varStatus="status">

							<c:if test="${status.index==0 }">

								<fmt:parseDate value="${flight.flightSegmentList[0].departure}"
									var="depDate" pattern="yyyy-MM-dd'T'HH:mm:ss" />

								<li data-role="list-divider">${cityMapByCode[flight.flightSegmentList[0].origin].ctyName} to ${cityMapByCode[flight.flightSegmentList[0].destination].ctyName} - <fmt:formatDate
										pattern="EEEEE, MMMM d, yyyy" value="${depDate}" /><span
									class="ui-li-count">${fn:length(result.response.response.searchResult.results)}
										Flights</span>


								</li>
							</c:if>

							<c:set var="flight" value="${flight}" scope="request"></c:set>
							<%
								request.setAttribute("fare", ApplicationUtil
												.getCombinationPrice((FlightCombination) request
														.getAttribute("flight")));
							%>


							<li><a href="index.html"> <c:forEach var="leg"
										varStatus="statleg" items="${flight.flightSegmentList}">

										<fmt:parseDate value="${leg.departure}" var="dep"
											pattern="yyyy-MM-dd'T'HH:mm:ss" />
										<fmt:parseDate value="${leg.arrival}" var="arv"
											pattern="yyyy-MM-dd'T'HH:mm:ss" />
										<c:set var="depD" scope="request" value="${dep}"></c:set>
										<c:set var="arvD" scope="request" value="${arv}"></c:set>

										<div class="ui-grid-d">
											<div class="ui-block-a">


												<p class="logo L${leg.carrierCode}"></p>
												<p>
													<strong>${leg.airlineName }</strong>
												</p>
												<p>${leg.carrierCode}-${leg.flightNumber }</p>
											</div>
											<div class="ui-block-b">
												</br>
												<p>
													<strong><fmt:formatDate pattern="H:mm a"
															value="${dep}" /></strong>
												</p>
												<p>${cityMapByCode[leg.origin].ctyName}</p>
											</div>
											<div class="ui-block-c">
												</br>
												<p>
													<strong><fmt:formatDate pattern="H:mm a"
															value="${arv}" /></strong>
												</p>
												<p>${cityMapByCode[leg.destination].ctyName}</p>
											</div>
											<div class="ui-block-d">
												</br>
												<p>
													<strong><%=ApplicationUtil.getDuration(
								(Date) request.getAttribute("depD"),
								(Date) request.getAttribute("arvD"))%></strong>
												</p>
												<p>

													<c:if test="${leg.numOfStops==0}">Non Stop</c:if>
													<c:if test="${leg.numOfStops>0}">


														<span> <c:if test="${leg.numOfStops==1}">One Stop</c:if>
															<c:if test="${leg.numOfStops==2}">Two Stop</c:if>
														</span>
													</c:if>
												</p>
												<p>
													<c:if test="${leg.quotesList[0].refundable}">Refundable</c:if>
													<c:if test="${not leg.quotesList[0].refundable}">Non Refundable</c:if>
												</p>
											</div>

											<c:if test="${statleg.index==0}">
												<div class="ui-block-e">
													</br>
													<h1 class="big"
														title="<fmt:formatNumber type="number" value="${fare}" />">
														<span class="WebRupee">Rs</span>
														<fmt:formatNumber type="number" value="${fare}" />
													</h1>
												</div>
											</c:if>

										</div>

									</c:forEach>




							</a></li>
						</c:forEach>
					</c:if>

					<c:if test="${result.type=='BUS'}">


						<li data-role="list-divider">${result.response.wsCheckAvailabilityRS.trip[0].fromCity}
							- ${result.response.wsCheckAvailabilityRS.trip[0].toCity}<span
							class="ui-li-count">${fn:length(result.response.wsCheckAvailabilityRS.trip)}
								Buses</span>


						</li>

						<c:forEach var="busitem"
							items="${result.response.wsCheckAvailabilityRS.trip }">
							<li data-filtertext="IT,Kingfisher"><a href="index.html">
									<div class="ui-grid-d">
										<div class="ui-block-a">
											<br />
											<p class="bus"></p>

											<p>
												<strong>${busitem.groupName }</strong>
											</p>
											<p>${busitem.seatType }</p>
										</div>
										<div class="ui-block-b">
											</br>
											<p>

												<fmt:parseDate value="${busitem.tripTime }" var="dep"
													pattern="E MMM dd HH:mm:ss z yyyy" />
												<strong> <fmt:formatDate pattern="hh:mm a"
														value="${dep}" />
												</strong>
											</p>
											<p>${busitem.fromCity}</p>
										</div>
										<div class="ui-block-c">
											</br>
											<p>
												<c:if
													test="${not empty busitem.arrivalTime and (not busitem.arrivalTime=='null' )}">
													<fmt:parseDate value="${busitem.arrivalTime }" var="arv"
														pattern="E MMM dd HH:mm:ss z yyyy" />
													<strong> <fmt:formatDate pattern="hh:mm a"
															value="${arv}" />
													</strong>
												</c:if>

												<c:if
													test="${ empty busitem.arrivalTime or busitem.arrivalTime=='null'}">
												N/A
												</c:if>

											</p>
											<p>${busitem.toCity}</p>
											<div data-role="fieldcontain">
												<select name="select-choice-1" data-native-menu="false"
													data-inline="true" data-mini="true">
													<c:forEach items="${busitem.pickupPoints.pickupPoint }"
														var="point">
														<option value="${point.pickupPointid }">${point.name}
															- ${point.arivalTime }</option>
													</c:forEach>

												</select>
											</div>

										</div>
										<div class="ui-block-d">
											</br>
											<!-- 											<p>Non Stop</p> -->
											<p>
												<c:if test="${fn:contains(busitem.busType,'A/C')}">
													<span class="sprite_bus bus_ac_on"></span>
												</c:if>
												<c:if test="${not fn:contains(busitem.busType,'A/C')}">
													<span class="sprite_bus bus_ac"></span>
												</c:if>

												<c:if
													test="${fn:contains(busitem.busType,'VOLVO') or fn:contains(busitem.busType,'Volvo')}">
													<span class="sprite_bus bus_volvo_on"></span>
												</c:if>
												<c:if
													test="${not (fn:contains(busitem.busType,'VOLVO') or fn:contains(busitem.busType,'Volvo'))}">
													<span class="sprite_bus bus_volvo"></span>
												</c:if>

												<c:if
													test="${fn:contains(busitem.busType,'Seater') or (not empty busitem.seatFare) }">
													<span class="sprite_bus bus_seater_on"></span>
												</c:if>
												<c:if
													test="${not fn:contains(busitem.busType,'Seater') and ( empty busitem.seatFare) }">
													<span class="sprite_bus bus_seater"></span>
												</c:if>

												<c:if
													test="${fn:contains(busitem.busType,'Sleeper') or (not empty busitem.sleeperFare)}">
													<span class="sprite_bus bus_sl_on"></span>
												</c:if>
												<c:if
													test="${not fn:contains(busitem.busType,'Sleeper') and ( empty busitem.sleeperFare)}">
													<span class="sprite_bus bus_sl"></span>
												</c:if>

											</p>
										</div>

										<div class="ui-block-e">
											</br>

											<c:if test="${not empty  busitem.sleeperFare }">
												<h1 class="big"
													title="Rs <fmt:formatNumber type="number" value="${busitem.sleeperFare }" />">
													<span class="WebRupee">Rs</span>
													<fmt:formatNumber type="number"
														value="${busitem.sleeperFare }" />
													<c:if
														test="${not empty  busitem.sleeperFare and not empty busitem.seatFare}">
														<span class="sprite_bus bus_sl_on flr"></span>
													</c:if>
												</h1>
											</c:if>
											<c:if test="${not empty  busitem.seatFare }">
												<h1 class="big"
													title="Rs <fmt:formatNumber type="number" value="${busitem.seatFare }" />">
													<span class="WebRupee">Rs</span>
													<fmt:formatNumber type="number"
														value="${busitem.seatFare }" />
													<c:if
														test="${not empty  busitem.sleeperFare and not empty busitem.seatFare}">
														<span class="sprite_bus bus_seater_on flr"></span>
													</c:if>

												</h1>
											</c:if>

										</div>



									</div>

							</a></li>
						</c:forEach>

					</c:if>
					<c:if test="${result.type=='CAR'}">
						<c:forEach var="car"
							items="${result.response.response}"
							varStatus="status">
							<li><a href="index.html">
							<div class="ui-grid-c">
							<div class="ui-block-a">
								<div>${car.carMake} ${car.carModel}</div>
								<div  class="car_logo">
									<img src="${car.carLogo}" >
								</div>
							</div>
								<div class="ui-block-b">
								<div title="${car.starRating} Star">
									<c:forEach var="rating" begin="1" end="${car.starRating}" varStatus="status">
										<div class="star-rating star-rating-on"></div>
									</c:forEach>
									<c:forEach var="rating" begin="1" end="${5-car.starRating}" varStatus="status">
										<div class="star-rating star-rating"></div>
									</c:forEach>
								</div>
								</br>
								<div title="Air Condition" class="aircon"></div>
								<div title="Stereo" class="audio"></div>
								</div>
								<div class="ui-block-c">
								<c:if test="${result.response.serviceType == 'Local Usage'}">
									<div>${car.usageType}</div>
									<div>(4 Hr/${car.kmLimitToDisplay} Km)</div>
								</c:if>
								<c:if test="${result.response.serviceType == 'Outstation'}">
									<div class="listing-font">Max. ${car.kmLimitToDisplay} Km/Day</div>
								</c:if>
								</div>
								<div class="ui-block-d">
									<h1 class="big"><span class="WebRupee">Rs</span> <fmt:formatNumber type="number" value="${car.bookingPriceToDisplay}"/>
									 </h1>
								</div>
							</div>
					</a> 
					</li>
					</c:forEach>
					</c:if>
					
					<c:if test="${result.type=='HOTEL'}">
						<c:forEach var="hotel"
							items="${result.response.hotels}"
							varStatus="status">
							<li><a href="index.html">
							<div class="ui-grid-c">
								<div class="ui-block-a">
									<img width="45%" height="45%"
										src="${hotel.urlPic}" class="thumbnail">
								</div>
								<div class="ui-block-b">
									<h3 class="nowrap_hotel">${hotel.hotelName}</h3>
<!-- 									<p class="wrap">Paharganj , New Delhi Railway Station</p> -->
									<div title="${hotel.starRating} Star">
										<c:forEach var="rating" begin="1" end="${hotel.starRating}" varStatus="status">
											<div class="star-rating star-rating-on"></div>
										</c:forEach>
										<c:forEach var="rating" begin="1" end="${5-hotel.starRating}" varStatus="status">
											<div class="star-rating star-rating"></div>
										</c:forEach>
									</div>
									<br />
									<c:if test="${hotel.restaurantOrBar}">
										<div title="Restaurant/Bar" class="amenities am_active_1"></div>
									</c:if>
									<c:if test="${not hotel.restaurantOrBar}">
										<div title="No Restaurant/Bar" class="amenities am_inactive_1"></div>
									</c:if>
									<c:if test="${hotel.internet}">
										<div title="Internet/Wi-Fi" class="amenities am_active_2"></div>
									</c:if>
									<c:if test="${not hotel.internet}">
										<div title="No Internet/Wi-Fi" class="amenities am_inactive_2"></div>
									</c:if>
									<c:if test="${hotel.recreationAvail}">
										<div title="Recreation" class="amenities am_active_3"></div>
									</c:if>
									<c:if test="${not hotel.recreationAvail}">
										<div title="No Recreation" class="amenities am_inactive_3"></div>
									</c:if>
									<c:if test="${hotel.swimmingPoolAvail}">
										<div title="Swimming Pool" class="amenities am_active_4"></div>
									</c:if>
									<c:if test="${not hotel.swimmingPoolAvail}">
										<div title="No Swimming Pool" class="amenities am_inactive_4"></div>
									</c:if>
									<c:if test="${hotel.parkingAvail}">
										<div title="Parking Facility" class="amenities am_active_5"></div>
									</c:if>
									<c:if test="${not hotel.parkingAvail}">
										<div title="No Parking Facility" class="amenities am_inactive_5"></div>
									</c:if>																																			
								</div>
								<div class="ui-block-c"></div>
								<div class="ui-block-d">
									</br>
									<h1 class="big"><span class="WebRupee">Rs</span> <fmt:formatNumber type="number" value="${hotel.lowestRate}"/>
									 </h1>
								</div>
							</div>
					</a> 
					</li>
					</c:forEach>
					</c:if>




				</ul>


		</div>

	</div>
	<!-- /page -->

</body>
</html>
