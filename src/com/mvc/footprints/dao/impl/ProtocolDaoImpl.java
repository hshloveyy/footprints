package com.mvc.footprints.dao.impl;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.IProtocolDao;
import com.mvc.footprints.entity.TProtocol;
			
@Repository("protocolDao")
public class ProtocolDaoImpl extends BaseDaoImpl implements IProtocolDao {
	
	@Override
	public TProtocol findProtocol() {
		return getHibernateTemplate().execute(new HibernateCallback<TProtocol>() {
			@Override
			public TProtocol doInHibernate(Session arg0)
					throws HibernateException, SQLException {
				return (TProtocol) arg0.createQuery("from TProtocol where id = (select max(id) from TProtocol)").uniqueResult();
			}
		});
	}
}
