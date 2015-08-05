package com.mvc.footprints.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.footprints.dao.IPageCityDao;
import com.mvc.footprints.entity.TPageCity;
import com.mvc.footprints.param.PagerParam;
import com.mvc.footprints.service.IPageCityService;
@Transactional
@Service("pageCityService")
public class PageCityServiceImpl implements IPageCityService {

	@Autowired
	private IPageCityDao pageCityDao;
	
	@Override
	public List<?> findAll(Class<?> clazz, PagerParam param) {
		return pageCityDao.findAll(clazz, param);
	}

	@Override
	public void save(Object object) {
		pageCityDao.save(object);
	}

	@Override
	public void delete(String id) {
		pageCityDao.delete(pageCityDao.findById(TPageCity.class, id));
	}

	@Override
	public void update(Object object) {
		pageCityDao.update(object);
	}

	@Override
	public Object findById(String id) {
		return pageCityDao.findById(TPageCity.class, id);
	}
	
	@Override
	public int findAllCount(Class<?> clazz, PagerParam param) {
		return pageCityDao.findAllCount(clazz, param);
	}
	
	@Override
	public List<TPageCity> findByProvince(Integer id) {
		return pageCityDao.findByProvinceId(id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TPageCity> findAll(Class<?> clazz) {
		return (List<TPageCity>) pageCityDao.findAll(clazz);
	}
}
