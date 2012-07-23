package com.mmt.data.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.mmt.data.dao.api.CityMapperData;
import com.mmt.data.models.CityMapper;

@Repository("cityMapperData")
public class CityMapperDataImpl implements CityMapperData {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public List<CityMapper> getAllCities() {
		return hibernateTemplate.find("from CityMapper");
	}

	@Override
	public List<CityMapper> getAllCitiesByProduct(String id) {
		return hibernateTemplate.find("from CityMapper");
	}

}
