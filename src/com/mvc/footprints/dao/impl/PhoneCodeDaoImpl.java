package com.mvc.footprints.dao.impl;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.IPhoneCodeDao;
import com.mvc.footprints.entity.TPhoneCode;

@Repository("phoneCodeDao")
public class PhoneCodeDaoImpl extends BaseDaoImpl implements IPhoneCodeDao {

	@Override
	public TPhoneCode verfiyCode(final String mobilePhone, final String phcode) {
		return getHibernateTemplate().execute(
				new HibernateCallback<TPhoneCode>() {
					@Override
					public TPhoneCode doInHibernate(Session arg0)
							throws HibernateException, SQLException {
						return (TPhoneCode) arg0.createQuery(
								"from TPhoneCode where status = 1 and phone = " + mobilePhone
										+ " and code = " + phcode)
								.uniqueResult();
					}
				});
	}
}
