package com.mvc.footprints.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.footprints.dao.IAdvertDao;
import com.mvc.footprints.entity.TAdvert;
import com.mvc.footprints.param.AdvertParam;
import com.mvc.footprints.param.PagerParam;
import com.mvc.footprints.service.IAdvertService;

@Transactional
@Service("advertService")
public class AdvertServiceImpl implements IAdvertService {

	@Autowired
	private IAdvertDao advertDao;
	
	@Override
	public void save(Object object) {
		advertDao.save(object);
	}

	@Override
	public void delete(String id) {
		advertDao.delete(advertDao.findById(TAdvert.class, id));
	}

	@Override
	public void update(Object object) {
		advertDao.update(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TAdvert> findAll(Class<?> clazz, PagerParam param) {
		return (List<TAdvert>) advertDao.findAll(clazz, param);
	}

	@Override
	public Object findById(String id) {
		return advertDao.findById(TAdvert.class, id);
	}

	@Override
	public int findAllCount(Class<?> clazz, PagerParam param) {
		return advertDao.findAllCount(clazz, param);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TAdvert> findAll(Class<?> clazz) {
		return (List<TAdvert>) advertDao.findAll(clazz);
	}
	
	@Override
	public List<TAdvert> findByParam(AdvertParam param) {
		return advertDao.findByParam(param);
	}
}
