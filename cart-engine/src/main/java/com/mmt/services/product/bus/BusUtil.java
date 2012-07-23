package com.mmt.services.product.bus;

import com.travis.webservices.WsAuthenticate;

public class BusUtil {

	public static WsAuthenticate getWsAuthenticate(){
		WsAuthenticate au = new WsAuthenticate();
		au.setGroupId("1");
		au.setUserId("transportation");
		au.setPassword("tr@nsp0rt@t10n@mmt");
		return au;
	}
}
