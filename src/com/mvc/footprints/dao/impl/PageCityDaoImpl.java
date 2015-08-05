package com.mvc.footprints.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.IPageCityDao;
import com.mvc.footprints.entity.TPageCity;
			
@Repository("pageCityDao")
public class PageCityDaoImpl extends BaseDaoImpl implements IPageCityDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TPageCity> findByProvinceId(Integer id) {
		return getHibernateTemplate().find("from TPageCity where provinceId = " + id);
	}
}
