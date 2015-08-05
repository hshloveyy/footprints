package com.mvc.footprints.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mvc.footprints.dao.BaseDaoImpl;
import com.mvc.footprints.dao.ISignDao;
import com.mvc.footprints.entity.TSign;
			
@Repository("signDao")
public class SignDaoImpl extends BaseDaoImpl implements ISignDao {

	@SuppressWarnings("unchecked")
	@Override
	public TSign findSignByUserIdAndShopId(Integer userId, Integer shopId) {
		List<TSign> list = hibernateTemplate.find("from TSign where userId = "
				+ userId + " and shopId = " + shopId);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}
