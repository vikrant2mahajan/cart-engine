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
<title>Suggestions Review</title>
</head>
<body>
	<div data-role="page" class="type-interior">


		<div data-role="content" align="center"></div>

		<ul data-role="listview" data-divider-theme="b">
			<li data-role="list-divider" data-theme="b"><h1>Travel
					Suggestions</h1> <span class="ui-li-count">${fn:length(suggestions)}
					Suggestions available</span></li>

			<c:forEach items="${suggestions }" var="suggestion">






				<c:if test="${suggestion.request.type=='HOTEL' }">
					<li data-theme="e"><a
						href="hotelReq.htm?hHotCity=${suggestion.request.request.cityCode }&hCheckInDate=${suggestion.request.request.checkInDate}&hCheckOutDate=${suggestion.request.request.checkOutDate}&hadultcount=1&hchildCount=1&submitHotel=submit-value-hotel">

							<div class="ui-grid-a">
								<div class="ui-block-a" style="width: 10%;">
									<img class="bg" alt="image" src="${suggestion.imgUrl }"
										width="60px" height="50px"> <br /> <strong>${suggestion.name}</strong>
								</div>
								<div class="ui-block-b">
									<span class="add"></span>
									<h3 class="big2">
										Add <span class="WebRupee">Rs</span> ${suggestion.price } and
										get ${suggestion.description }
									</h3>
									<br />
									<p>
										<fmt:parseDate
											value="${suggestion.request.request.checkInDate}"
											var="ciDate" pattern="yyyy-MM-dd" />

										<fmt:parseDate
											value="${suggestion.request.request.checkOutDate}"
											var="coDate" pattern="yyyy-MM-dd" />

										<strong class="f16">Stay from <fmt:formatDate
												pattern="EEEE, MMMM dd yyyy" value="${ciDate}" /> @
											${cityMapByCode[suggestion.request.request.cityCode].ctyName}
										</strong>
									</p>

								</div>

							</div>
					</a></li>
				</c:if>





				<c:if test="${suggestion.request.type=='FLIGHT' }">
					<li data-theme="e"><a
						href="flightsReq.htm?fDepCity=${suggestion.request.request.origin }&fRetCity=${suggestion.request.request.destination }&fDepDate=${suggestion.request.request.departureDate }&fadultCount=1&fchildCount=1&finfantCount=1&fcabinClass=E&submit=submit-value">


							<div class="ui-grid-a">
								<div class="ui-block-a" style="width: 10%;">
									<img class="bg" alt="image"
										src="css/images/${suggestion.imgUrl }.png" width="60px"
										height="50px"> <br /> <strong>${suggestion.name}</strong>
								</div>
								<div class="ui-block-b">
									<span class="add"></span>
									<h3 class="big2">
										Add <span class="WebRupee">Rs</span>
										<fmt:formatNumber type="number" value="${suggestion.price }" />
										and get ${suggestion.description }
									</h3>
									<br />
									<p>
										<strong class="f16"> <fmt:parseDate
												value="${suggestion.request.request.departureDate}"
												var="fdDate" pattern="yyyy-MM-dd" /> From
											${cityMapByCode[suggestion.request.request.origin].ctyName}
											to
											${cityMapByCode[suggestion.request.request.destination].ctyName}
											on <fmt:formatDate pattern="EEEE, MMMM dd yyyy"
												value="${fdDate}" />
										</strong>

									</p>

								</div>

							</div>
					</a></li>
				</c:if>



				<c:if test="${suggestion.request.type=='BUS' }">
					<fmt:parseDate value="${suggestion.request.request.departureDate}"
						var="fdDate" pattern="dd-MM-yyyy" />
					<li data-theme="e"><a
						href="busReq.htm?bDepCity=${suggestion.request.request.origin}&bRetCity=${suggestion.request.request.destination}&bDepDate=<fmt:formatDate pattern="yyyy-MM-dd" value="${fdDate}" />&submitBus=submit-value-bus">


							<div class="ui-grid-a">
								<div class="ui-block-a" style="width: 10%;">
									<img class="bg" alt="image" src="css/images/bus.png"
										width="60px" height="50px"> <br /> <strong>${suggestion.name}</strong>
								</div>
								<div class="ui-block-b">
									<span class="add"></span>
									<h3 class="big2">
										Add <span class="WebRupee">Rs</span>
										<fmt:formatNumber type="number" value="${suggestion.price }" />
										and get ${suggestion.description }
									</h3>
									<br />
									<p>
										<strong class="f16"> From
											${cityMapByBusCode[suggestion.request.request.origin].ctyName}
											to
											${cityMapByBusCode[suggestion.request.request.destination].ctyName}
											on <fmt:formatDate pattern="EEEE, MMMM dd yyyy"
												value="${fdDate}" />
										</strong>

									</p>

								</div>

							</div>
					</a></li>
				</c:if>










				<c:if test="${suggestion.request.type=='CAR' }">




					<li data-theme="e"><a
						href="carReq.htm?cDepCity=<c:if test="${empty suggestion.request.request.origin}">${suggestion.request.request.destination}</c:if><c:if test="${not empty suggestion.request.request.origin}">${suggestion.request.request.origin}</c:if>&cRetCity=${suggestion.request.request.destination}&cDepDate=${suggestion.request.request.year}-${suggestion.request.request.month}-${suggestion.request.request.date}&submitCar=submit-value-Car">

							<div class="ui-grid-a">
								<div class="ui-block-a" style="width: 10%;">
									<img class="bg" alt="image" src="${suggestion.imgUrl }"
										width="60px" height="50px"> <br /> <strong>${suggestion.name}</strong>
								</div>
								<div class="ui-block-b">
									<span class="add"></span>
									<h3 class="big2">
										Add <span class="WebRupee">Rs</span> ${suggestion.price } and
										get ${suggestion.description }
									</h3>
									<br />
									<p>
										<strong class="f16">${suggestion.request.request.serviceType} @
											${cityMapByCode[suggestion.request.request.destination].ctyName}</strong>

									</p>

								</div>

							</div>
					</a></li>
				</c:if>





			</c:forEach>

		</ul>
<form id="finalReview" action="getFinalReview.htm" method="get">
			<input type="hidden" name="data" id="finalData"/>
			
		</form>


			<a href="#checkout" class="completeCheckOut" data-role="button" data-icon="arrow-r" style="position: absolute;bottom: 10px;right: 10px;"
				id="checkOut" data-theme="e" data-inline="true">Continue Check Out</a>

</div>












</body>
</html>