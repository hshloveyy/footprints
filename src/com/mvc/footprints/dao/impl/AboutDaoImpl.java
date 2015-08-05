package com.mvc.footprints.dao.impl;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.IAboutDao;
import com.mvc.footprints.entity.TAbout;
			
@Repository("aboutDao")
public class AboutDaoImpl extends BaseDaoImpl implements IAboutDao {
	
	@Override
	public TAbout findAbout() {
		return getHibernateTemplate().execute(new HibernateCallback<TAbout>() {
			@Override
			public TAbout doInHibernate(Session arg0)
					throws HibernateException, SQLException {
				return (TAbout) arg0.createQuery("from TAbout where id = (select max(id) from TAbout)").uniqueResult();
			}
		});
	}
}
