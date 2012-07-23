var cart = {};
(function() {
	$(function() {
		$("body").on(
				"click",
				"li[data-type='BUS'] a",
				function() {
					$("#requestData").val(
							JSON.stringify(results['BUS'][$(this).parents("li")
									.attr("id")]));
					$("#listingForm").submit();

				});

		$("body").on(
				"click",
				"li[data-type='FLIGHT'] a",
				function() {
					$("#requestData").val(
							JSON.stringify(results['Flight'][$(this).parents(
									"li").attr("id")]));
					$("#listingForm").submit();

				});

		$(".cart_panel[data-position='fixed']").fixedtoolbar('hide');

		$("body").on("click", "#planTravel", function() {
			if (currentProduct.type == 'BUS')
				addBusToCart(true);
			if (currentProduct.type == 'FLIGHT')
				addFlightToCart(true);

		});
		$("body").on("click", ".cart_delete", function() {
			var item = $(this).parents(".cart_item");
			$(item).hide("scale", 1000, function() {
				$(item).remove();
			});
		});

	});

}).call(this);
var addBusToCart = function(animate) {

	if (cart['BUS'] == undefined) {
		cart['BUS'] = currentProduct;
		if (cart['FARE'] == undefined) {
			if (currentProduct.sleeperFare != null)
				cart['FARE'] = currentProduct.sleeperFare;
			else
				cart['FARE'] = currentProduct.seaterFare;

		} else {
			var fare = 0;
			if (currentProduct.sleeperFare != null)
				fare = currentProduct.sleeperFare;
			else
				fare = currentProduct.seaterFare;
			cart['FARE'] += fare;
		}
		$(".cart_panel[data-position='fixed']").fixedtoolbar('show');

		if (animate)
			$("#planTravel").effect("transfer", {
				to : $(".cart")
			}, 1000);

		var content = '<div	class="ui-block-b ui-btn ui-shadow  ui-btn-inline ui-btn-up-c ui-btn-corner-all cart_item " style="display:none">	<span class="cart_delete" title="Remove"></span>	<div align="center"><img alt="BUS" src="css/images/bus(2).png" width="30px" height="30px;"></div><p class="heavy">'
				+ currentProduct.groupName
				+ '</p>	<p>'
				+ currentProduct.fromCity
				+ ' to '
				+ currentProduct.toCity
				+ '</p></div>';
		$(".cart_panel[data-position='fixed'] .ui-grid-e").first().after(
				content);

		if (animate)
			$(".cart_item:not(:visible)").show("drop", 1000);

		var params = JSON.stringify(currentProduct);
		$.ajax({
			url : 'addItemToCart.htm',
			type : "POST",
			data : {
				item : params,
				type : currentProduct.type,
				fare : cart['FARE']

			},
			success : function(data) {

			}
		});
	} else {
		$(".cart_panel[data-position='fixed']").fixedtoolbar('show');
		alert("You already have a bus in your cart !");

	}
};

var addFlightToCart = function(animate) {

	if (cart['FLIGHT'] == undefined) {
		cart['FLIGHT'] = currentProduct;
		if (cart['FARE'] == undefined) {
			cart['FARE'] = currentProduct.fare;

		} else {
			var fare = 0;
			fare = currentProduct.fare;
			cart['FARE'] += fare;
		}
		$(".cart_panel[data-position='fixed']").fixedtoolbar('show');

		if (animate)
			$("#planTravel").effect("transfer", {
				to : $(".cart")
			}, 1000);

		var content = '<div	class="ui-block-b ui-btn ui-shadow  ui-btn-inline ui-btn-up-c ui-btn-corner-all cart_item " style="display:none">	<span class="cart_delete" title="Remove"></span>	<div align="center"><img alt="BUS" src="css/images/'
				+ currentProduct.carrierCode
				+ '.png" width="50%" height="30px;"></div><p class="heavy">'
				+ currentProduct.airlineName
				+ '</p>	<p>'
				+ currentProduct.fromCity
				+ ' to '
				+ currentProduct.toCity
				+ '</p>	<p>'
				+ currentProduct.departure
				+ ' - '
				+ currentProduct.arrival + '</p></div>';
		$(".cart_panel[data-position='fixed'] .ui-grid-e").first().after(
				content);

		if (animate)
			$(".cart_item:not(:visible)").show("drop", 1000);

		var params = JSON.stringify(currentProduct);
		$.ajax({
			url : 'addItemToCart.htm',
			type : "POST",
			data : {
				item : params,
				type : currentProduct.type,
				fare : cart['FARE']

			},
			success : function(data) {
				$(".fare .big span:last").text(cart['FARE']);
			}
		});
	} else {
		$(".cart_panel[data-position='fixed']").fixedtoolbar('show');
		alert("You already have a flight in your cart !");

	}
};