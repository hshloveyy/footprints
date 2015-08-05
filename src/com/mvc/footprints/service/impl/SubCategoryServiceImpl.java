package com.mvc.footprints.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.footprints.dao.ISubCategoryDao;
import com.mvc.footprints.entity.TSubCategory;
import com.mvc.footprints.param.PagerParam;
import com.mvc.footprints.service.ISubCategoryService;
@Transactional
@Service("subCategoryService")
public class SubCategoryServiceImpl implements ISubCategoryService {

	@Autowired
	private ISubCategoryDao subCategoryDao;
	
	@Override
	public void save(Object object) {
		subCategoryDao.save(object);
	}

	@Override
	public void delete(String id) {
		subCategoryDao.delete(subCategoryDao.findById(TSubCategory.class, id));
	}

	@Override
	public void update(Object object) {
		subCategoryDao.update(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TSubCategory> findAll(Class<?> clazz, PagerParam param) {
		return (List<TSubCategory>) subCategoryDao.findAll(clazz, param);
	}

	@Override
	public Object findById(String id) {
		return subCategoryDao.findById(TSubCategory.class, id);
	}

	@Override
	public int findAllCount(Class<?> clazz, PagerParam param) {
		return subCategoryDao.findAllCount(clazz, param);
	}
	
	@Override
	public List<TSubCategory> findByCategoryId(Integer id) {
		return subCategoryDao.findByCategoryId(id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TSubCategory> findAll(Class<?> clazz) {
		return (List<TSubCategory>) subCategoryDao.findAll(clazz);
	}
}
