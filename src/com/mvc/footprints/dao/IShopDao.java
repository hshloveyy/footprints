package com.mvc.footprints.dao;

import java.util.List;

import com.mvc.footprints.entity.TShopInfo;
import com.mvc.footprints.param.ShopParam;

public interface IShopDao extends BaseDao{

	List<TShopInfo> findShopByParams(ShopParam param);

	List<TShopInfo> isbbsShop(ShopParam param);

	List<TShopInfo> findAll(ShopParam param);

	int findAllCount(ShopParam param);

}
