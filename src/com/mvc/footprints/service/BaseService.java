package com.mvc.footprints.service;

import java.util.List;

import com.mvc.footprints.param.PagerParam;

public interface BaseService {

	Object findById(String id);
	List<?> findAll(Class<?> clazz, PagerParam param);
	List<?> findAll(Class<?> clazz);
	int findAllCount(Class<?> clazz, PagerParam param);
	void save(Object object);
	void delete(String id);
	void update(Object object);
}
