package com.mvc.footprints.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.IShopImageDao;
import com.mvc.footprints.entity.TFileInfo;

@Repository("shopImageDao")
public class ShopImageDaoImpl extends BaseDaoImpl implements IShopImageDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TFileInfo> findFileByShopId(Integer shopId) {
		return getHibernateTemplate().find("from TFileInfo where encryption in (select imageId from TShopImage where shopId = " + shopId + ")");
	}
	
}
