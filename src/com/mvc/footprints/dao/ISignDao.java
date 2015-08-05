package com.mvc.footprints.dao;

import com.mvc.footprints.entity.TSign;


public interface ISignDao extends BaseDao{

	TSign findSignByUserIdAndShopId(Integer userId, Integer shopId);

}
