package com.mvc.footprints.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.IKindDao;
import com.mvc.footprints.entity.TKind;
			
@Repository("kindDao")
public class KindDaoImpl extends BaseDaoImpl implements IKindDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TKind> findAll() {
		return getHibernateTemplate().find("from TKind");
	}
}
