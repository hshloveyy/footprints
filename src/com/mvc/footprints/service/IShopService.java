package com.mvc.footprints.service;

import java.util.List;

import com.mvc.footprints.entity.TShopInfo;
import com.mvc.footprints.entity.TShopLike;
import com.mvc.footprints.param.ShopParam;


public interface IShopService extends BaseService{

	List<TShopInfo> findShopByParams(ShopParam param);

	TShopLike findLikeByParam(ShopParam param);

	List<TShopLike> ranking(ShopParam param);

	List<TShopInfo> isbbsShop(ShopParam param);

	void saveWorktime(ShopParam param);

	Long findLikeCountByShopId(Integer shopId);

}
