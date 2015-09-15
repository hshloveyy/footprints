package com.mvc.footprints.dao;

import java.util.List;
import java.util.Map;

import com.mvc.footprints.entity.TShopLike;
import com.mvc.footprints.param.ShopParam;


public interface IShopLikeDao extends BaseDao{

	TShopLike findShopLikeByUserIdAndShopId(Integer userId, Integer shopId);

	List<Map<String, Object>> ranking(ShopParam param);

	Long findLikeCountByShopId(Integer shopId);

}