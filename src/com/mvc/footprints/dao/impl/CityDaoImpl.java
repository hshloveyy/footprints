package com.mvc.footprints.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.ICityDao;
import com.mvc.footprints.entity.TCity;
import com.mvc.footprints.param.CityParam;
			
@Repository("cityDao")
public class CityDaoImpl extends BaseDaoImpl implements ICityDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TCity> findByProvinceId(Integer id) {
		return getHibernateTemplate().find("from TCity where provinceId = " + id);
	}

	@Override
	public List<TCity> findByProvinceIdPage(final CityParam param) {
		return getHibernateTemplate().execute(new HibernateCallback<List<TCity>>() {
			@SuppressWarnings("unchecked")
			@Override
			public List<TCity> doInHibernate(Session arg0) throws HibernateException,
					SQLException {
				int first = (param.getPage() - 1) * param.getRows();
				int rows = param.getRows();
				if(rows == 0){
					rows = 10;
				}
				return arg0.createQuery("from TCity where provinceId = " + param.getProvinceId()).setFirstResult(first).setMaxResults(rows).list();
			}
		});
	}
	
	
}
