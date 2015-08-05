package com.mvc.footprints.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.IPreUcenterMemberDao;
import com.mvc.footprints.entity.PreUcenterMembers;

@SuppressWarnings("unchecked")
@Repository("preUcenterMemberDao")
public class PreUcenterMemberDaoImpl extends BaseDaoImpl implements IPreUcenterMemberDao {

	@Override
	public PreUcenterMembers findByUsername(String username) {
		PreUcenterMembers p = new PreUcenterMembers();
		p.setUsername(username);
		List<PreUcenterMembers> list = getHibernateTemplate().findByExample(p);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public PreUcenterMembers findUserByParam(PreUcenterMembers members) {
		return null;
	}

	@Override
	public PreUcenterMembers findByUserId(final Integer userId) {
		return getHibernateTemplate().execute(new HibernateCallback<PreUcenterMembers>() {
			@Override
			public PreUcenterMembers doInHibernate(Session arg0)
					throws HibernateException, SQLException {
				return (PreUcenterMembers) arg0.createQuery("from PreUcenterMembers where uid = " + userId).uniqueResult();
			}
		});
	}
	
	@Override
	public PreUcenterMembers findUserByMobilePhone(final String phoneNumber) {
		return getHibernateTemplate().execute(new HibernateCallback<PreUcenterMembers>() {
			@Override
			public PreUcenterMembers doInHibernate(Session arg0)
					throws HibernateException, SQLException {
				return (PreUcenterMembers) arg0.createQuery("from PreUcenterMembers where mobilePhone = " + phoneNumber).uniqueResult();
			}
		});
	}
}
