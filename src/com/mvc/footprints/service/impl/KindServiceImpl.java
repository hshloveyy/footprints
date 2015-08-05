package com.mvc.footprints.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.footprints.dao.IKindDao;
import com.mvc.footprints.entity.TCity;
import com.mvc.footprints.entity.TKind;
import com.mvc.footprints.param.PagerParam;
import com.mvc.footprints.service.IKindService;
@Transactional
@Service("kindService")
public class KindServiceImpl implements IKindService {

	@Autowired
	private IKindDao kindDao;
	
	@Override
	public List<?> findAll(Class<?> clazz, PagerParam param) {
		return kindDao.findAll(clazz, param);
	}

	@Override
	public void save(Object object) {
		kindDao.save(object);
	}

	@Override
	public void delete(String id) {
		kindDao.delete(kindDao.findById(TCity.class, id));
	}

	@Override
	public void update(Object object) {
		kindDao.update(object);
	}

	@Override
	public Object findById(String id) {
		return kindDao.findById(TKind.class, id);
	}
	
	@Override
	public int findAllCount(Class<?> clazz, PagerParam param) {
		return kindDao.findAllCount(clazz, param);
	}
	
	@Override
	public List<TKind> findAll() {
		return kindDao.findAll();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TKind> findAll(Class<?> clazz) {
		return (List<TKind>) kindDao.findAll(clazz);
	}
}
