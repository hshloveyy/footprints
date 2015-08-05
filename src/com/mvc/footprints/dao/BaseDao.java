package com.mvc.footprints.dao;

import java.util.List;

import com.mvc.footprints.param.PagerParam;

public interface BaseDao {
	public List<Object> createQuery(final String queryString, Object[] objects);
	public List<?> findAll(Class<?> clazz, PagerParam param, boolean isSort);
	public List<?> findAll(Class<?> clazz, PagerParam param, String isSort);
	public List<?> findAll(Class<?> clazz, PagerParam param);
	public List<?> findAll(Class<?> clazz);
	public int findAllCount(Class<?> clazz, PagerParam param);
	public Object findById(Class<?> clazz, String id);
	public Object save(final Object model);
	public void update(final Object model);
	public void delete(final Object model);
}
