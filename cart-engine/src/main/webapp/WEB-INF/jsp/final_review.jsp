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



		<div data-role="content" align="center">


			<div class="ui-grid-d heading" data-role="header" data-theme="b">
				<div class="ui-block-a ">Product</div>
				<div class="ui-block-b">City</div>
				<div class="ui-block-c">Facilites/Time</div>
				<div class="ui-block-d">Fare</div>
			</div>
			</br>


			<c:if test="${cart.products['FLIGHTM'].type=='FLIGHT'}">
				<div class="ui-grid-d ${cart.products['FLIGHTM'].type }_product">
					<c:if test="${cart.products['FLIGHTM'].type=='FLIGHT'}">
						<div align="center" class="ui-block-a">
							<img width="30%" height="30%" alt="BUS"
								src="css/images/${cart.products['FLIGHTM'].carrierCode}.png"><br /> <strong>${cart.products['FLIGHTM'].airlineName}</strong><br />
							${cart.products['FLIGHTM'].carrierCode} - ${cart.products['FLIGHTM'].flightNumber}
						</div>
					</c:if>

					<div class="ui-block-b">
						<br /> <br /> ${cart.products['FLIGHTM'].fromCity} - ${cart.products['FLIGHTM'].toCity}
					</div>
					<div class="ui-block-c">
						<br /> <br /> ${cart.products['FLIGHTM'].departure} - ${cart.products['FLIGHTM'].arrival}
					</div>
					<div class="ui-block-d">
						<br /> <br /> <strong class="big"><span
							class="WebRupee">Rs</span> <fmt:formatNumber type="number"
								value="${cart.products['FLIGHTM'].fare}" /></strong>
								
								<c:set  var="totalFare" value="${cart.products['FLIGHTM'].fare}" scope="request"></c:set>


					</div>
				</div>
			</c:if>
<br/>
								<c:set var="busFare" scope="request" value="0"></c:set>

								<c:if test="${cart.products['BUSM'].type=='BUS'}">
			
								<c:if test="${not empty cart.products['BUSM'].seaterFare}">								
								 	<c:set var="busFare" value="${totalFare+cart.products['BUSM'].seaterFare}"></c:set>
								</c:if>
								<c:if test="${not empty cart.products['BUSM'].sleeperFare or not  cart.products['BUSM'].sleeperFare== null}">
								 	<c:set var="busFare" value="${totalFare+cart.products['BUSM'].sleeperFare}"></c:set>
								</c:if>
								
								
				<div class="ui-grid-d ${cart.products['BUSM'].type }_product">
					<c:if test="${cart.products['BUSM'].type=='BUS'}">
						<div align="center" class="ui-block-a">
							<img width="30%" height="30%" alt="BUS"
								src="css/images/bus(2).png"><br /> <strong>${cart.products['BUSM'].groupName}</strong><br />
							${cart.products['BUSM'].seatType}
						</div>
					</c:if>

					<div class="ui-block-b">
						<br /> <br /> ${cart.products['BUSM'].fromCity} - ${cart.products['BUSM'].toCity}
					</div>
					<div class="ui-block-c">
						<br /> <br /> ${cart.products['BUSM'].departureTime}
					</div>
					<div class="ui-block-d">
						<br /> <br /> <strong class="big "><span class="WebRupee">Rs</span> <fmt:formatNumber
								type="number" value="${busFare}" /></strong>
								<c:set  var="totalFare" value="${busFare+totalFare}" scope="request"></c:set>
								
								
					</div>
				</div>

			</c:if>

<br/>


			<c:if test="${cart.products['CARM'].type=='CAR'}">
				<div class="ui-grid-d ${cart.products['CARM'].type }_product">
					<c:if test="${cart.products['CARM'].type=='CAR'}">
						<div align="center" class="ui-block-a">
							 
							<img width="30%" height="30%" alt="car"
								src="${cart.products['CARM'].logo}">
								<br /> <br />
								<strong>${cart.products['CARM'].name}</strong>
						</div>
					</c:if>

					<div class="ui-block-b">
						<br /> <br /> ${cart.products['CARM'].fromCity} -
						${cart.products['CARM'].toCity}
					</div>

					<div class="ui-block-c">
						<br /> <br /> Morning
					</div>

					<div class="ui-block-d">
						<br /> <br /> <strong class="big "><span class="WebRupee">Rs</span> <fmt:formatNumber
								type="number" value="${cart.products['CARM'].fare}" /></strong>
								
						 	<c:set var="totalFare" value="${totalFare+cart.products['CARM'].fare}"></c:set>
								
					</div>
				</div>

			</c:if>

<!-- 			<hr class="line"/> -->
			<br/>


			<c:if test="${cart.products['HOTELM'].type=='HOTEL'}">
				<div class="ui-grid-d ${cart.products['HOTELM'].type }_product">
					<c:if test="${cart.products['HOTELM'].type=='HOTEL'}">
						<div align="center" class="ui-block-a">
							<img width="30%" height="30%" alt="BUS" src="${cart.products['HOTELM'].imageUrl }"><br />
							<strong>${cart.products['HOTELM'].name}</strong><br />
							<div style="width: 90px;">
								<c:forEach var="rating" begin="1" end="${cart.products['HOTELM'].rating}"
									varStatus="status">
									<div class="star-rating star-rating-on"></div>
								</c:forEach>
								<c:forEach var="rating" begin="1" end="${5-cart.products['HOTELM'].rating}"
									varStatus="status">
									<div class="star-rating star-rating"></div>
								</c:forEach>
							</div>


						</div>
					</c:if>
					<div class="ui-block-b">
						<br /> <br /> ${cart.products['HOTELM'].fromCity}<br />
						<fmt:parseDate value="${cart.products['HOTELM'].checkinDate }" var="depDate"
							pattern="yyyy-MM-dd" />
						<fmt:formatDate pattern="EEEE ,MMMM dd yyyy" value="${depDate}" />


					</div>
					<div class="ui-block-c" align="center">
						<br /> <br />

						<div style="width: 150px;">
							<c:if test="${cart.products['HOTELM'].restaurantOrBar}">
								<div title="Restaurant/Bar" class="amenities am_active_1"></div>
							</c:if>
							<c:if test="${empty cart.products['HOTELM'].restaurantOrBar}">
								<div title="No Restaurant/Bar" class="amenities am_inactive_1"></div>
							</c:if>
							<c:if test="${cart.products['HOTELM'].internet}">
								<div title="Internet/Wi-Fi" class="amenities am_active_2"></div>
							</c:if>
							<c:if test="${empty cart.products['HOTELM'].internet}">
								<div title="No Internet/Wi-Fi" class="amenities am_inactive_2"></div>
							</c:if>
							<c:if test="${cart.products['HOTELM'].recreationAvail}">
								<div title="Recreation" class="amenities am_active_3"></div>
							</c:if>
							<c:if test="${empty cart.products['HOTELM'].recreationAvail}">
								<div title="No Recreation" class="amenities am_inactive_3"></div>
							</c:if>
							<c:if test="${cart.products['HOTELM'].swimmingPoolAvail}">
								<div title="Swimming Pool" class="amenities am_active_4"></div>
							</c:if>
							<c:if test="${empty cart.products['HOTELM'].swimmingPoolAvail}">
								<div title="No Swimming Pool" class="amenities am_inactive_4"></div>
							</c:if>
							<c:if test="${cart.products['HOTELM'].parkingAvail}">
								<div title="Parking Facility" class="amenities am_active_5"></div>
							</c:if>
							<c:if test="${empty cart.products['HOTELM'].parkingAvail}">
								<div title="No Parking Facility" class="amenities am_inactive_5"></div>
							</c:if>
						</div>

					</div>
					<div class="ui-block-d">
						<br /> <br /> <strong class="big"><span
							class="WebRupee">Rs</span> <fmt:formatNumber type="number"
								value="${cart.products['HOTELM'].fare}" /></strong>
								
								<c:set var="totalFare" value="${totalFare+cart.products['HOTELM'].fare}"></c:set>	
					</div>
				</div>

			</c:if>





			





			<div class="ui-grid-a" style="width: 90%;">
			<br />
			
				<div class="ui-block-b" align="right" style="width: 78%;">
				<hr />
					
						Grand Total<br /> <br /> <strong class="big"><span
							class="WebRupee">Rs</span> <fmt:formatNumber type="number"
								value="${totalFare}" /></strong>
					
					<br/>


					<a href="index.html" data-role="button" data-icon="arrow-r"
						data-theme="e" data-inline="true">PAY NOW </a>

				</div>

			</div>





		</div>


	</div>


















</body>
</html>