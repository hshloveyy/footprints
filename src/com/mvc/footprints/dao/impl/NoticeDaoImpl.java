package com.mvc.footprints.dao.impl;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.INoticeDao;
import com.mvc.footprints.entity.TNotice;
			
@Repository("noticeDao")
public class NoticeDaoImpl extends BaseDaoImpl implements INoticeDao {
	
	@Override
	public TNotice findNotice() {
		return getHibernateTemplate().execute(new HibernateCallback<TNotice>() {
			@Override
			public TNotice doInHibernate(Session arg0)
					throws HibernateException, SQLException {
				return (TNotice) arg0.createQuery("from TNotice where id = (select max(id) from TNotice)").uniqueResult();
			}
		});
	}
}
