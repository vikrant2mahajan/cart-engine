package com.mmt.data.dao.api;

import java.util.List;

import com.mmt.data.models.CityMapper;

public interface CityMapperData {

	public List<CityMapper> getAllCities();

	public List<CityMapper> getAllCitiesByProduct(String id);
}
