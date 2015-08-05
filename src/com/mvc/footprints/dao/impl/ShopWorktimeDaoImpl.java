package com.mvc.footprints.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.IShopWorktimeDao;
import com.mvc.footprints.entity.TShopWorktime;
			
@Repository("shopWorktimeDao")
public class ShopWorktimeDaoImpl extends BaseDaoImpl implements IShopWorktimeDao {

	@Override
	public void deleteByShopId(Integer shopId) {
		getHibernateTemplate().deleteAll(findWorktimeByShopId(shopId));
	}
	
	@Override
	public List<TShopWorktime> findWorktimeByShopId(Integer id) {
		return getHibernateTemplate().find("from TShopWorktime where shopId = " + id);
	}
}
