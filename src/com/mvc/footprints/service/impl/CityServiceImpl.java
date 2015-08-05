package com.mvc.footprints.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.footprints.dao.ICityDao;
import com.mvc.footprints.entity.TCity;
import com.mvc.footprints.param.PagerParam;
import com.mvc.footprints.service.ICityService;
@Transactional
@Service("cityService")
public class CityServiceImpl implements ICityService {

	@Autowired
	private ICityDao cityDao;
	
	@Override
	public List<?> findAll(Class<?> clazz, PagerParam param) {
		return cityDao.findAll(clazz, param);
	}

	@Override
	public void save(Object object) {
		cityDao.save(object);
	}

	@Override
	public void delete(String id) {
		cityDao.delete(cityDao.findById(TCity.class, id));
	}

	@Override
	public void update(Object object) {
		cityDao.update(object);
	}

	@Override
	public Object findById(String id) {
		return cityDao.findById(TCity.class, id);
	}
	
	@Override
	public int findAllCount(Class<?> clazz, PagerParam param) {
		return cityDao.findAllCount(clazz, param);
	}
	
	@Override
	public List<TCity> findByProvince(Integer id) {
		return cityDao.findByProvinceId(id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TCity> findAll(Class<?> clazz) {
		return (List<TCity>) cityDao.findAll(clazz);
	}
}
