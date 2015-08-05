package com.mvc.footprints.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.footprints.dao.ISubKindDao;
import com.mvc.footprints.entity.TCity;
import com.mvc.footprints.entity.TSubKind;
import com.mvc.footprints.param.PagerParam;
import com.mvc.footprints.service.ISubKindService;
@Transactional
@Service("subKindService")
public class SubKindServiceImpl implements ISubKindService {

	@Autowired
	private ISubKindDao subKindDao;
	
	@Override
	public List<?> findAll(Class<?> clazz, PagerParam param) {
		return subKindDao.findAll(clazz, param);
	}

	@Override
	public void save(Object object) {
		subKindDao.save(object);
	}

	@Override
	public void delete(String id) {
		subKindDao.delete(subKindDao.findById(TCity.class, id));
	}

	@Override
	public void update(Object object) {
		subKindDao.update(object);
	}

	@Override
	public Object findById(String id) {
		return subKindDao.findById(TCity.class, id);
	}
	
	@Override
	public int findAllCount(Class<?> clazz, PagerParam param) {
		return subKindDao.findAllCount(clazz, param);
	}
	
	@Override
	public List<TSubKind> findByKindId(Integer id) {
		return subKindDao.findByKindId(id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TSubKind> findAll(Class<?> clazz) {
		return (List<TSubKind>) subKindDao.findAll(clazz);
	}
}
