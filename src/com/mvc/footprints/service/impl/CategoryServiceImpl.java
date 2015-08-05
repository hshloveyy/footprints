package com.mvc.footprints.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.footprints.dao.ICategoryDao;
import com.mvc.footprints.entity.TCategory;
import com.mvc.footprints.param.PagerParam;
import com.mvc.footprints.service.ICategoryService;

@Transactional
@Service("categoryService")
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ICategoryDao categoryDao;
	
	@Override
	public void save(Object object) {
		categoryDao.save(object);
	}

	@Override
	public void delete(String id) {
		categoryDao.delete(categoryDao.findById(TCategory.class, id));
	}

	@Override
	public void update(Object object) {
		categoryDao.update(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TCategory> findAll(Class<?> clazz, PagerParam param) {
		return (List<TCategory>) categoryDao.findAll(clazz, param);
	}

	@Override
	public Object findById(String id) {
		return categoryDao.findById(TCategory.class, id);
	}

	@Override
	public int findAllCount(Class<?> clazz, PagerParam param) {
		return categoryDao.findAllCount(clazz, param);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TCategory> findAll(Class<?> clazz) {
		return (List<TCategory>) categoryDao.findAll(clazz);
	}
}
