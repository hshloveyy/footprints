package com.mvc.footprints.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.footprints.dao.IProvinceDao;
import com.mvc.footprints.entity.TProvince;
import com.mvc.footprints.param.PagerParam;
import com.mvc.footprints.service.IProvinceService;
@Transactional
@Service("provinceService")
public class ProvinceServiceImpl implements IProvinceService {

	@Autowired
	private IProvinceDao provinceDao;
	
	@Override
	public List<?> findAll(Class<?> clazz, PagerParam param) {
		return provinceDao.findAll(clazz, param);
	}

	@Override
	public void save(Object object) {
		provinceDao.save(object);
	}

	@Override
	public void delete(String id) {
		provinceDao.delete(provinceDao.findById(TProvince.class, id));
	}

	@Override
	public void update(Object object) {
		provinceDao.update(object);
	}

	@Override
	public Object findById(String id) {
		return provinceDao.findById(TProvince.class, id);
	}

	@Override
	public int findAllCount(Class<?> clazz, PagerParam param) {
		return provinceDao.findAllCount(clazz, param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TProvince> findAll(Class<?> clazz) {
		return (List<TProvince>) provinceDao.findAll(clazz);
	}
}
