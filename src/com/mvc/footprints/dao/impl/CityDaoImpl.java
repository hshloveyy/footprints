package com.mvc.footprints.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.ICityDao;
import com.mvc.footprints.entity.TCity;
			
@Repository("cityDao")
public class CityDaoImpl extends BaseDaoImpl implements ICityDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TCity> findByProvinceId(Integer id) {
		return getHibernateTemplate().find("from TCity where provinceId = " + id);
	}
}
