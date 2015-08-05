package com.mvc.footprints.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.footprints.dao.IPageProvinceDao;
import com.mvc.footprints.entity.TPageProvince;
import com.mvc.footprints.entity.TProvince;
import com.mvc.footprints.param.PagerParam;
import com.mvc.footprints.service.IPageProvinceService;
@Transactional
@Service("pageProvinceService")
public class PageProvinceServiceImpl implements IPageProvinceService {

	@Autowired
	private IPageProvinceDao pageProvinceDao;
	
	@Override
	public List<?> findAll(Class<?> clazz, PagerParam param) {
		return pageProvinceDao.findAll(clazz, param);
	}

	@Override
	public void save(Object object) {
		pageProvinceDao.save(object);
	}

	@Override
	public void delete(String id) {
		pageProvinceDao.delete(pageProvinceDao.findById(TProvince.class, id));
	}

	@Override
	public void update(Object object) {
		pageProvinceDao.update(object);
	}

	@Override
	public Object findById(String id) {
		return pageProvinceDao.findById(TPageProvince.class, id);
	}

	@Override
	public int findAllCount(Class<?> clazz, PagerParam param) {
		return pageProvinceDao.findAllCount(clazz, param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TPageProvince> findAll(Class<?> clazz) {
		return (List<TPageProvince>) pageProvinceDao.findAll(clazz);
	}
}
