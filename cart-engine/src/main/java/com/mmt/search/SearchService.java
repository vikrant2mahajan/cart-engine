package com.mmt.search;

import java.io.Serializable;

public interface SearchService extends Serializable{

	public ResponseHolder search(RequestHolder requestHolder);
}
