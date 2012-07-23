<!DOCTYPE html>
<%@ page isELIgnored="false"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.1.1/jquery.mobile-1.1.1.min.css" />
<link rel="stylesheet"
	href="http://dev.jtsage.com/cdn/datebox/latest/jquery.mobile.datebox.min.css" />
<link rel="stylesheet" href="css/chosen.css" />

<script src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
<script src="js/chosen.jquery.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.1.1/jquery.mobile-1.1.1.min.js"></script>
<script type="text/javascript"
	src="http://dev.jtsage.com/cdn/datebox/latest/jquery.mobile.datebox.min.js"></script>
<link rel="stylesheet" href="css/styles.css" />
<script type="text/javascript" src="js/application.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.21.custom.min.js"></script>

</head>
<script>
	$(document).ready(function() {
		$(".chosenTextFields").chosen();
	});
</script>
<body>

	<div data-role="page" id="menu" data-theme="b"
		data-content-theme="b">
			<div data-role="header" >
			     <h1>Travel Shop</h1>
		    </div>


		<div data-role="content">
			<div align="center" style="margin-top: 5%; padding-bottom: 10%;">
				<div class="ui-grid-a">
					<div class="ui-block-a" align="right">
						<a href="#flights" data-rel="dialog" data-transition="pop"
							data-role="button" data-theme="a" class="airplane"> </a>
					</div>
					<div class="ui-block-b" align="left">
						<a href="#hotels" data-rel="dialog" data-transition="pop"
							data-role="button" data-theme="b" class="hotels"></a>
					</div>
					<div class="ui-block-a" align="right">
						<a href="#car" data-rel="dialog" data-transition="pop"
							data-role="button" data-theme="b" class="car_logo1"></a>
					</div>
					<div class="ui-block-b" align="left">
						<a href="#bus" data-rel="dialog" data-transition="pop"
							data-role="button" data-theme="b" class="bus_logo"></a>
					</div>
				</div>
			</div>
		</div>

		<div data-role="footer" data-position="fixed" >
			     <h1></h1>
		</div>
    </div>



	<div data-role="page" id="flights" data-theme="b"
		data-content-theme="b">
		<div data-role="header" data-theme="b">
			<h1>Flights</h1>
			<a href="#one" data-rel="back" data-role="button" data-inline="true"
				data-icon="back">Back</a>
		</div>
		<!-- /header -->
		<div data-role="content">
			<form action="flightsReq.htm" method="get">
				<!-- <fieldset data-role="controlgroup" data-mini="true"
					data-type="horizontal">
					<input type="radio" name="tripType" id="oneWay" value="O"
						checked="checked" /> <label for="oneWay">One Way</label> <input
						type="radio" name="tripType" id="roundTrip" value="R" /> <label
						for="roundTrip">Round Trip</label> <input type="radio"
						name="tripType" id="multiCity" value="M" /> <label
						for="multiCity">Multi City</label>
				</fieldset> -->

				<div>
					<div>
						<label for="fDepCity">Leaving from</label>
					</div>
					<div>
						<select name="fDepCity" id="fDepCity" data-role="none"
							data-style="width:350px;" tabindex="2" class="chosenTextFields" style="width: 300px;">
							<option value="">Flying from</option>
							<c:forEach var="city" items="${cities}">
								<option value="${city.ctyFltcode}">${city.ctyName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div style="height: 4px;">
					<p>&nbsp;</p>
				</div>

				<div>
					<div>
						<label for="fRetCity">Going to</label>
					</div>
					<div>
						<select name="fRetCity" id="fRetCity" data-role="none"
							data-style="width:350px;" tabindex="2" class="chosenTextFields" style="width: 300px;">
							<option value="">Flying to</option>
							<c:forEach var="city" items="${cities}">
								<option value="${city.ctyFltcode}">${city.ctyName}</option>
							</c:forEach>
						</select>
					</div>
			   </div>
				<div style="height: 4px;">
					<p>&nbsp;</p>
				</div>
				<div>
						<label for="fDepDate">Departure Date</label> <input
							name="fDepDate" id="fDepDate" type="date" data-role="datebox"
							data-options='{"mode":"calbox"}'>
					</div>

					<!-- <div>
					<label for="fRetDate">Return Date</label> <input name="fRetDate"
						id="fRetDate" type="date" data-role="datebox"
						data-options='{"mode":"calbox"}'>
				</div> -->
				<div style="height: 4px;">
					<p>&nbsp;</p>
				</div>
				<div class="ui-grid-b">
						<div class="ui-block-a">
							<label for="fadultCount" class="ui-input-text">Adults:</label> <input
								type="number" name="fadultCount" id="fadultCount" value=""
								class="ui-input-text ui-body-c ui-corner-all ui-shadow-inset">
						</div>

						<div class="ui-block-b">
							<label for="fchildCount" class="ui-input-text">Children</label> <input
								type="number" name="fchildCount" id="fchildCount" value=""
								class="ui-input-text ui-body-c ui-corner-all ui-shadow-inset">
						</div>

						<div class="ui-block-c">
							<label for="finfantCount" class="ui-input-text">Infants</label> <input
								type="number" name="finfantCount" id="finfantCount" value=""
								class="ui-input-text ui-body-c ui-corner-all ui-shadow-inset">
						</div>
					</div>

					<div data-role="fieldcontain">
						<label for="fcabinClass">Cabin Class:</label> <select
							name="fcabinClass" id="fcabinClass" data-role="slider"
							data-theme="a">
							<option value="E">Eco</option>
							<option value="B">Biz</option>
						</select>
					</div>
					<div>
						<button type="submit" data-theme="b" name="submit"
							value="submit-value" data-inline="true" data-icon="search">Search
							For flights</button>
					</div>
			</form>

		</div>
		<div data-role="footer" data-theme="b" data-theme="c">
			<h4>Flights Footer</h4>
		</div>
		<!-- /footer -->
	</div>


	<div data-role="page" id="hotels" data-theme="b" data-content-theme="c">
		<div data-role="header" data-theme="b">
			<a href="#one" data-rel="back" data-role="button" data-inline="true"
				data-icon="back">Back</a>
			<h1>Hotels</h1>
		</div>
		<!-- /header -->
		<div data-role="content">
			<form action="hotelReq.htm" method="get">

				<div>
					<div>
						<label for="hHotCity">I want to go to</label>
					</div>
					<div>
						<select name="hHotCity" id="hHotCity" data-role="none"
							data-style="width:350px;" tabindex="2" class="chosenTextFields"
							style="width: 300px;">
							<option value="">Choose a City</option>
							<c:forEach var="city" items="${cities}">
								<option value="${city.ctyHtlcode}">${city.ctyName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
                <div style="height: 4px;">
					<p>&nbsp;</p>
				</div>

				<div>
					<label for="hCheckInDate">Check In Date</label> <input
						name="hCheckInDate" id="hCheckInDate" type="date"
						data-role="datebox" data-options='{"mode":"calbox"}'>
				</div>
				
				<div style="height: 4px;">
					<p>&nbsp;</p>
				</div>

				<div>
					<label for="hCheckOutDate">Check Out Date</label> <input
						name="hCheckOutDate" id="hCheckOutDate" type="date"
						data-role="datebox" data-options='{"mode":"calbox"}'>
				</div>
				
				 <div style="height: 4px;">
					<p>&nbsp;</p>
				</div>
				
				<div class="ui-grid-b">

					<div class="ui-block-a">
						<label for="rooms" class="ui-input-text">No Of rooms:</label> <input
							type="number" name="rooms" id="rooms" value=""
							class="ui-input-text ui-body-c ui-corner-all ui-shadow-inset">
					</div>

					<div class="ui-block-b">
						<label for="hadultcount" class="ui-input-text">Adults:</label> <input
							type="number" name="hadultcount" id="hadultcount" value=""
							class="ui-input-text ui-body-c ui-corner-all ui-shadow-inset">
					</div>

					<div class="ui-block-c">
						<label for="hchildCount" class="ui-input-text">Children:</label> <input
							type="number" name="hchildCount" id="childCount" value=""
							class="ui-input-text ui-body-c ui-corner-all ui-shadow-inset">
					</div>
				</div>
				<button type="submit" data-theme="b" name="submitHotel"
					value="submit-value-hotel" data-inline="true" data-icon="search">Search
					For hotels</button>
			</form>

		</div>
		<div data-role="footer" data-theme="b" data-position="fixed">
			<h4>Hotels Footer</h4>
		</div>
		<!-- /footer -->
	</div>


	<div data-role="page" id="bus" data-theme="b" data-content-theme="c">
		<div data-role="header" data-theme="b">
			<a href="#one" data-rel="back" data-role="button" data-icon="back">Back</a>
			<h1>Bus</h1>
		</div>
		<!-- /header -->

		<div data-role="content">
					<form action="busReq.htm" method="get">
						<div>
	                          <div>
							         <label for="bDepCity">Origin</label>
							   </div>
							   <div > 
							          <select name="bDepCity"
								       id="bDepCity" data-role="none" data-style="width:350px;"
								       tabindex="2" class="chosenTextFields" style="width:300px;">
								       <option value="">Choose a City</option>
								       <c:forEach var="city" items="${cities}">
									       <option value="${city.ctyBuscode}">${city.ctyName}</option>
								       </c:forEach>
							          </select>
							     </div>
						</div>
				<div style="height: 4px;">
					<p>&nbsp;</p>
				</div>
				<div>	
								<div>
							       <label for="bRetCity">Destination</label>
							       </div> 
							       <div>
							       <select name="bRetCity"
								id="bRetCity" data-role="none" data-style="width:350px;"
								tabindex="2" class="chosenTextFields" style="width:300px;">
								<option value="">Choose a City</option>
								<c:forEach var="city" items="${cities}">
									<option value="${city.ctyBuscode}">${city.ctyName}</option>
								</c:forEach>
							</select>
						</div>
						</div>
				<div style="height: 4px;">
					<p>&nbsp;</p>
				</div>

				<label for="bDepDate">Departure Date</label> <input
									name="bDepDate" id="bDepDate" type="date" data-role="datebox"
									data-options='{"mode":"calbox"}'>
							<div>
							<button type="submit" data-theme="b" name="submitBus"
								value="submit-value-bus" data-inline="true" data-icon="search">Search
								For Bus</button>
								</div>
					</form>
		</div>

		<div data-role="footer" data-theme="b"  data-position="fixed">
			<h4>Bus Footer</h4>
		</div>
		<!-- /footer -->

	</div>


	<div data-role="page" id="car" data-theme="b" data-content-theme="c">
		<div data-role="header" data-theme="b">
			<a href="#one" data-rel="back" data-role="button" data-icon="back">Back</a>
			<h1>Car</h1>
		</div>
		<!-- /header -->
		<div data-role="content">
			<form action="carReq.htm" method="get">
				<div>
					<div>
						<label for="cDepCity">Origin</label>
					</div>
					<div>
						<select name="cDepCity" id="cDepCity" data-role="none"
							data-style="width:350px;" tabindex="2" class="chosenTextFields" style="width:300px;">
							<option value="">Choose a City</option>
							<c:forEach var="city" items="${cities}">
								<option value="${city.ctyCarcode}">${city.ctyName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				 <div style="height: 4px;">
					<p>&nbsp;</p>
				</div>
				<div>
					<div>
						<label for="cRetCity">Destination</label>
					</div>
					<div>
						<select name="cRetCity" id="cRetCity" data-role="none"
							data-style="width:350px;" tabindex="2" class="chosenTextFields" style="width:300px;">
							<option value="">Choose a City</option>
							<c:forEach var="city" items="${cities}">
								<option value="${city.ctyCarcode}">${city.ctyName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				 <div style="height: 4px;">
					<p>&nbsp;</p>
				</div>
				<div>
					<label for="cDepDate">Departure Date</label> <input name="cDepDate"
						id="cDepDate" type="date" data-role="datebox"
						data-options='{"mode":"calbox"}' data-mini="true">
				</div>
				<button type="submit" data-theme="b" name="submitCar"
					value="submit-value-Car" data-inline="true" data-icon="search">Search
					For cars</button>
			</form>
		</div>
		<div data-role="footer" data-theme="b" data-position="fixed">
			<h4>Car Footer</h4>
		</div>
		<!-- /footer -->
	</div>
</body>
</html>