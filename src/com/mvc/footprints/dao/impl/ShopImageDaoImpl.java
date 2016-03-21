package com.mvc.footprints.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.IShopImageDao;
import com.mvc.footprints.entity.TFileInfo;
import com.mvc.footprints.entity.TShopImage;

@Repository("shopImageDao")
public class ShopImageDaoImpl extends BaseDaoImpl implements IShopImageDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TFileInfo> findFileByShopId(Integer shopId) {
		return getHibernateTemplate().find("from TFileInfo where encryption in (select imageId from TShopImage where shopId = " + shopId + ")  order by SAVE_PATH");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TShopImage findByEncryption(String id) {
		List<TShopImage> list = getHibernateTemplate().find("from TShopImage where imageId = '" + id + "'");
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}
