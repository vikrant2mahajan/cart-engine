<html>
<head>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.mobile-1.1.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.21.custom.min.js"></script>
<link rel="stylesheet" href="css/jquery.mobile-1.1.1.min.css" />
<link rel="stylesheet" href="css/jquery.mobile.structure-1.1.1.min.css" />
<!-- <link rel="stylesheet" href="css/jquery.mobile.theme-1.1.1.min.css" /> -->
<link rel="stylesheet" href="css/styles.css" />
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

		<div data-role="header" data-position="fixed">
			<div class="ui-grid-e">
				<div
					class="ui-block-a ui-btn ui-shadow  ui-btn-inline ui-btn-up-c ui-btn-corner-all cart_item">
					<span class="cart_delete" title="Remove"></span>
					<div align="center">
						<img alt="IT" src="css/images/IT.png" width="28px" height="30px;">
					</div>
					<p class="heavy">Kingfisher</p>
					<p>IT - 312</p>
				</div>
				<div class="ui-block-b">
					<span class="cart_plus" title="Remove"></span>
				</div>
				<div
					class="ui-block-c ui-btn ui-shadow  ui-btn-inline ui-btn-up-c ui-btn-corner-all cart_item">

					<span class="cart_delete" title="Remove"></span>


					<div align="center">
						<img alt="IT" src="css/images/SG.png" width="28px" height="30px;">
					</div>
					<p class="heavy">SpiceJet</p>
					<p>SG - 312</p>
				</div>

			</div>
		</div>
		<!-- /footer -->


		<div data-role="content">
			<div class="content-primary">
				<ul data-role="listview" data-split-icon="arrow-r"
					data-split-theme="d" data-theme="d" data-divider-theme="d"
					data-filter="true"
					data-filter-placeholder="Filter by airline / refundable / stops"
					data-inset="true">
					<li data-role="list-divider">Delhi to Mumbai - Friday, October
						8, 2010 <span class="ui-li-count">75 Flights</span>
					</li>
					<li
						data-filtertext="IT,Kingfisher,Refundable,refundable,nonstop,Non stop,0"><a
						href="index.html">
							<div class="ui-grid-d">
								<div class="ui-block-a">
									<p class="IT"></p>
									<p>
										<strong>Kingfisher</strong>
									</p>
									<p>IT - 312</p>
								</div>
								<div class="ui-block-b">
									</br>
									<p>
										<strong>8:00 AM</strong>
									</p>
									<p>New Delhi</p>
								</div>
								<div class="ui-block-c">
									</br>
									<p>
										<strong>10:40 AM</strong>
									</p>
									<p>Mumbai</p>
								</div>
								<div class="ui-block-d">
									</br>
									<p>
										<strong>2h 40m</strong>
									</p>
									<p>Non Stop</p>
									<p>Refundable</p>
								</div>

								<div class="ui-block-e">
									</br>
									<h1 class="big" title="Rs 4304">Rs 4304</h1>
								</div>



							</div>

							<div data-role="collapsible" data-mini="true">
								<h3>Flight Details</h3>
								<p>I'm the collapsible content. By default I'm closed, but
									you can click the header to open me.</p>
							</div>
					</a></li>
					<li data-filtertext="IT,Kingfisher"><a href="index.html">
							<div class="ui-grid-d">
								<div class="ui-block-a">
									<p class="IT"></p>
									<p>
										<strong>Kingfisher</strong>
									</p>
									<p>IT - 312</p>
								</div>
								<div class="ui-block-b">
									</br>
									<p>
										<strong>8:00 AM</strong>
									</p>
									<p>New Delhi</p>
								</div>
								<div class="ui-block-c">
									</br>
									<p>
										<strong>10:40 AM</strong>
									</p>
									<p>Mumbai</p>
								</div>
								<div class="ui-block-d">
									</br>
									<p>
										<strong>2h 40m</strong>
									</p>
									<p>Non Stop</p>
									<p>Refundable</p>
								</div>


								<p class="ui-li-aside big">
									<strong>Rs 4300</strong>
								</p>


							</div>

							<div data-role="collapsible" data-mini="true">
								<h3>Flight Details</h3>
								<p>I'm the collapsible content. By default I'm closed, but
									you can click the header to open me.</p>
							</div>
					</a> <a href="lists-split-purchase.html" data-rel="dialog"
						data-transition="slideup">Purchase album </a></li>



					<li><a href="index.html">
							<div class="ui-grid-c">
								<div class="ui-block-a">
									<img width="80%" height="100%"
										src="css/images/Exterior_View.jpg" class="thumbnail">
								</div>
								<div class="ui-block-b">
									<h3>Hotel Woodland Deluxe</h3>
									<p class="wrap">Paharganj , New Delhi Railway Station</p>
									<div class="star-rating star-rating-on"></div>
									<div class="star-rating star-rating-on"></div>
									<div class="star-rating star-rating-on"></div>
									<div class="star-rating star-rating"></div>
									<br />
								</div>
								<div class="ui-block-c"></div>
								<div class="ui-block-d">
									</br>
									<h1 class="big" title="Rs 4304">Rs 4304</h1>
								</div>

							</div>

							<div data-role="collapsible" data-mini="true">
								<h3>Hotel Details</h3>
								<p>I'm the collapsible content. By default I'm closed, but
									you can click the header to open me.</p>
							</div>
					</a> <a href="lists-split-purchase.html" data-rel="dialog"
						data-transition="slideup">Purchase album </a></li>
					<li><a href="index.html">
							<div class="ui-grid-c">
								<div class="ui-block-a">
									<img width="80%" height="100%"
										src="css/images/Exterior_View.jpg" class="thumbnail">
								</div>
								<div class="ui-block-b">
									<h3>Hotel Woodland Deluxe</h3>
									<p class="wrap">Paharganj , New Delhi Railway Station</p>
									<div class="star-rating star-rating-on"></div>
									<div class="star-rating star-rating-on"></div>
									<div class="star-rating star-rating-on"></div>
									<div class="star-rating star-rating"></div>
									<br />
								</div>
								<div class="ui-block-c"></div>
								<div class="ui-block-d">
									</br>
									<h1 class="big" title="Rs 4304">Rs 4304</h1>
								</div>
							</div>
							<div data-role="collapsible" data-mini="true">
								<h3>Hotel Details</h3>
								<p>I'm the collapsible content. By default I'm closed, but
									you can click the header to open me.</p>
							</div>
					</a> <a href="lists-split-purchase.html" data-rel="dialog"
						data-transition="slideup">Purchase album </a></li>



					<li data-filtertext="IT,Kingfisher"><a href="index.html">
							<div class="ui-grid-d">
								<div class="ui-block-a">
									<br />
									<p>
										<strong>SVR Travels</strong>
									</p>
									<p>More Info</p>
								</div>
								<div class="ui-block-b">
									</br>
									<p>
										<strong>8:00 AM</strong>
									</p>
									<p>New Delhi</p>
								</div>
								<div class="ui-block-c">
									</br>
									<p>
										<strong>10:40 AM</strong>
									</p>
									<p>Mumbai</p>
								</div>
								<div class="ui-block-d">
									</br>
									<p>
										<strong>2h 40m</strong>
									</p>
									<p>Non Stop</p>
									<p>Refundable</p>
								</div>

								<div class="ui-block-e">
									</br>
									<h1 class="big" title="Rs 4304">Rs 4304</h1>
								</div>



							</div>

							<div data-role="collapsible" data-mini="true">
								<h3>Bus Details</h3>
								<p>I'm the collapsible content. By default I'm closed, but
									you can click the header to open me.</p>
							</div>
					</a> <a href="lists-split-purchase.html" data-rel="dialog"
						data-transition="slideup">Purchase album </a></li>
					<li><a href="index.html">

							<h3>jQuery Team</h3>
							<p>
								<strong>Boston Conference Planning</strong>
							</p>
							<p>In preparation for the upcoming conference in Boston, we
								need to start gathering a list of sponsors and speakers.</p>
							<p class="ui-li-aside">
								<strong>9:18</strong>AM
							</p>

					</a></li>
					<li><a href="index.html">

							<h3>jQuery Team</h3>
							<p>
								<strong>Boston Conference Planning</strong>
							</p>
							<p>In preparation for the upcoming conference in Boston, we
								need to start gathering a list of sponsors and speakers.</p>
							<p class="ui-li-aside">
								<strong>9:18</strong>AM
							</p>

					</a></li>
				</ul>

			</div>




		</div>



	</div>
	<!-- /page -->

</body>
</html>
