package com.mvc.footprints.dao.impl;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.IDictDao;
import com.mvc.footprints.entity.TDict;
			
@Repository("dictDao")
public class DictDaoImpl extends BaseDaoImpl implements IDictDao {
	
	@Override
	public TDict findByKey(final String key) {
		return getHibernateTemplate().execute(new HibernateCallback<TDict>() {
			@Override
			public TDict doInHibernate(Session arg0) throws HibernateException,
					SQLException {
				return (TDict) arg0.createQuery("from TDict t where t.key = ?").setString(0, key).uniqueResult();
			}
		});
	}
}
