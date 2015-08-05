package com.mvc.footprints.dao;

import java.util.List;

import com.mvc.footprints.entity.TShopLike;
import com.mvc.footprints.param.ShopParam;


public interface IShopLikeDao extends BaseDao{

	TShopLike findShopLikeByUserIdAndShopId(Integer userId, Integer shopId);

	List<TShopLike> ranking(ShopParam param);

	Long findLikeCountByShopId(Integer shopId);

}