package com.mvc.footprints.dao.impl;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.IFileInfoDao;
import com.mvc.footprints.entity.TFileInfo;

@Repository("fileInfoDao")
public class FileInfoDaoImpl extends BaseDaoImpl implements IFileInfoDao {
	
	 public TFileInfo findByMd5(final String encryption){
		 return getHibernateTemplate().execute(new HibernateCallback<TFileInfo>() {
			 @Override
			public TFileInfo doInHibernate(Session arg0) throws HibernateException,
					SQLException {
				Query query = arg0.createQuery("from TFileInfo file where file.encryption = ?");
				
				query.setString(0, encryption);
				return (TFileInfo) query.uniqueResult();
			}
		});
	 }
}
