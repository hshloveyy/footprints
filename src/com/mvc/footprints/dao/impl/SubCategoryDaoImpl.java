package com.mvc.footprints.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.ISubCategoryDao;
import com.mvc.footprints.entity.TSubCategory;
			
@Repository("subCategoryDao")
public class SubCategoryDaoImpl extends BaseDaoImpl implements ISubCategoryDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TSubCategory> findByCategoryId(Integer id) {
		return getHibernateTemplate().find("from TSubCategory where categoryId = " + id + " order by sort");
	}
}
