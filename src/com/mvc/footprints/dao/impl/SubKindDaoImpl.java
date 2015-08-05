package com.mvc.footprints.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.ISubKindDao;
import com.mvc.footprints.entity.TSubKind;
			
@Repository("subKindDao")
public class SubKindDaoImpl extends BaseDaoImpl implements ISubKindDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TSubKind> findByKindId(Integer id) {
		return getHibernateTemplate().find("from TSubKind where parentId = " + id + " order by sort");
	}
}
