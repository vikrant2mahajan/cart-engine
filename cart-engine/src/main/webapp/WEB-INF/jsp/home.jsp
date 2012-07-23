<!DOCTYPE html>
<%@ page isELIgnored="false"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Dominoes</title>
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

</head>
<script>
	$(document).ready(function() {
		$(".chosenTextFields").chosen();
	});
</script>

<style>
.chosenTextFields {
	style ="width: 40%";
}
</style>
<body>

	<div data-role="page" id="flights" data-theme="b"
		data-content-theme="b">
		<div data-role="header">
			<h1>Flights</h1>
			<a href="#one" data-rel="back" data-role="button" data-inline="true"
				data-icon="back">Back</a>
			<div data-role="navbar">
				<ul>
					<li><a href="#flights" data-transition="slide"
						data-role="button" class="ui-btn-active ui-state-persist">Flights</a></li>
					<li><a href="#car" data-transition="slide" data-role="button">Cars</a></li>
					<li><a href="#hotels" data-transition="slide"
						data-role="button">Hotels</a></li>
					<li><a href="#bus" data-transition="slide" data-role="button">Bus</a></li>
				</ul>
			</div>
		</div>
		<!-- /header -->
		<div data-role="content">
			<form action="flightsReq.htm" method="post">
				<fieldset data-role="controlgroup" data-mini="true"
					data-type="horizontal">
					<input type="radio" name="tripType" id="oneWay" value="O"
						checked="checked" /> <label for="oneWay">One Way</label> <input
						type="radio" name="tripType" id="roundTrip" value="R" /> <label
						for="roundTrip">Round Trip</label> <input type="radio"
						name="tripType" id="multiCity" value="M" /> <label
						for="multiCity">Multi City</label>
				</fieldset>
				<label for="fDepCity">Departure</label> <select name="fDepCity"
					id="fDepCity" data-role="none" data-style="width:350px;"
					tabindex="2" class="chosenTextFields">
					<c:forEach var="city" items="${cities}">
						<option value="${city.ctyFltcode}">${city.ctyName}</option>
					</c:forEach>
				</select> <label for="fRetCity">Return</label> <select name="fRetCity"
					id="fRetCity" data-role="none" data-style="width:350px;"
					tabindex="2" class="chosenTextFields">
					<c:forEach var="city" items="${cities}">
						<option value="${city.ctyFltcode}">${city.ctyName}</option>
					</c:forEach>
				</select>


				<div>
					<label for="fDepDate">Departure Date</label> <input name="fDepDate"
						id="fDepDate" type="date" data-role="datebox"
						data-options='{"mode":"calbox"}'>
				</div>

				<div>
					<label for="fRetDate">Return Date</label> <input name="fRetDate"
						id="fRetDate" type="date" data-role="datebox"
						data-options='{"mode":"calbox"}'>
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
		<div data-role="footer" data-theme="c">
			<h4>Flights Footer</h4>
		</div>
		<!-- /footer -->
	</div>


	<div data-role="page" id="hotels" data-theme="b" data-content-theme="c">
		<div data-role="header">
			<a href="#one" data-rel="back" data-role="button" data-inline="true"
				data-icon="back">Back</a>
			<h1>Hotels</h1>
			<div data-role="navbar">
				<ul>
					<li><a href="#flights" data-transition="slide"
						data-role="button">Flights</a></li>
					<li><a href="#car" data-transition="slide" data-role="button">Cars</a></li>
					<li><a href="#hotels" data-transition="slide"
						data-role="button" class="ui-btn-active ui-state-persist">Hotels</a></li>
					<li><a href="#bus" data-transition="slide" data-role="button">Bus</a></li>
				</ul>
			</div>
		</div>
		<!-- /header -->
		<div data-role="content">
			<form action="hotelReq.htm" method="post">

				<label for="hHotCity">Destination</label> <select name="hHotCity"
					id="hHotCity" data-role="none" data-style="width:350px;"
					tabindex="2" class="chosenTextFields">
					<c:forEach var="city" items="${cities}">
						<option value="${city.ctyHtlcode}">${city.ctyName}</option>
					</c:forEach>
				</select>
				<div>
					<label for="hCheckInDate">Check In Date</label> <input
						name="hCheckInDate" id="hCheckInDate" type="date"
						data-role="datebox" data-options='{"mode":"calbox"}'>
				</div>

				<div>
					<label for="hCheckOutDate">Check Out Date</label> <input
						name="hCheckOutDate" id="hCheckOutDate" type="date"
						data-role="datebox" data-options='{"mode":"calbox"}'>
				</div>
				<div class="ui-grid-b">

					<div class="ui-block-a">
						<label for="rooms" class="ui-input-text">No Of rooms:</label> <input
							type="number" name="rooms" id="rooms" value=""
							class="ui-input-text ui-body-c ui-corner-all ui-shadow-inset">
					</div>

					<div class="ui-block-b">
						<label for="hadultCount" class="ui-input-text">Adults:</label> <input
							type="number" name="hadultCount" id="adultCount" value=""
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
		<div data-role="footer" data-theme="c">
			<h4>Hotels Footer</h4>
		</div>
		<!-- /footer -->
	</div>


	<div data-role="page" id="bus" data-theme="b" data-content-theme="c">
		<div data-role="header">
			<a href="#one" data-rel="back" data-role="button" data-icon="back">Back</a>
			<h1>Bus</h1>
			<div data-role="navbar">
				<ul>
					<li><a href="#flights" data-transition="slide"
						data-role="button">Flights</a></li>
					<li><a href="#car" data-transition="slide" data-role="button">Cars</a></li>
					<li><a href="#hotels" data-transition="slide"
						data-role="button">Hotels</a></li>
					<li><a href="#bus" data-transition="slide" data-role="button"
						class="ui-btn-active ui-state-persist">Bus</a></li>
				</ul>
			</div>
		</div>
		<!-- /header -->

		<div data-role="content" style="height: 480px;">
					<form action="busReq.htm" method="post">
						<div>
							<label for="bDepCity">Departure</label> <select name="bDepCity"
								id="bDepCity" data-role="none" data-style="width:350px;"
								tabindex="2" class="chosenTextFields" style="width:300px;">
								<option value="">Choose a City</option>
								<c:forEach var="city" items="${cities}">
									<option value="${city.ctyBuscode}">${city.ctyName}</option>
								</c:forEach>
							</select>
							</div>
							<div>
							<div style="height: 8px;">
							<p>&nbsp;</p>
							</div>
							<label for="bRetCity">Return</label> <select name="bRetCity"
								id="bRetCity" data-role="none" data-style="width:350px;"
								tabindex="2" class="chosenTextFields" style="width:300px;">
								<option value="">Choose a City</option>
								<c:forEach var="city" items="${cities}">
									<option value="${city.ctyBuscode}">${city.ctyName}</option>
								</c:forEach>
							</select>
							
							
							
							</div>
							
							<div style="height: 8px;">
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

		<div data-role="footer" data-theme="c">
			<h4>Bus Footer</h4>
		</div>
		<!-- /footer -->

	</div>


	<div data-role="page" id="car" data-theme="b" data-content-theme="c">
		<div data-role="header">
			<a href="#one" data-rel="back" data-role="button" data-icon="back">Back</a>
			<h1>Car</h1>
			<div data-role="navbar">
				<ul>
					<li><a href="#flights" data-transition="slide"
						data-role="button">Flights</a></li>
					<li><a href="#car" data-transition="slide" data-role="button"
						class="ui-btn-active ui-state-persist">Cars</a></li>
					<li><a href="#hotels" data-transition="slide"
						data-role="button">Hotels</a></li>
					<li><a href="#bus" data-transition="slide" data-role="button">Bus</a></li>
				</ul>
			</div>
		</div>
		<!-- /header -->
		<div data-role="content">
			<form action="carReq.htm" method="post">
				<label for="cDepCity">Departure</label> <select name="cDepCity"
					id="cDepCity" data-role="none" data-style="width:350px;"
					tabindex="2" class="chosenTextFields">
					<option value="">Choose a City</option>
					<c:forEach var="city" items="${cities}">
						<option value="${city.ctyCarcode}">${city.ctyName}</option>
					</c:forEach>
				</select> <label for="cRetCity">Return</label> <select name="cRetCity"
					id="cRetCity" data-role="none" data-style="width:350px;"
					tabindex="2" class="chosenTextFields">
					<option value="">Choose a City</option>
					<c:forEach var="city" items="${cities}">
						<option value="${city.ctyCarcode}">${city.ctyName}</option>
					</c:forEach>
				</select>

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
		<div data-role="footer" data-theme="c">
			<h4>Car Footer</h4>
		</div>
		<!-- /footer -->
	</div>


</body>
</html>