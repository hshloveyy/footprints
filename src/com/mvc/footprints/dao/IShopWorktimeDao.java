package com.mvc.footprints.dao;

import java.util.List;

import com.mvc.footprints.entity.TShopWorktime;



public interface IShopWorktimeDao extends BaseDao{

	void deleteByShopId(Integer shopId);

	List<TShopWorktime> findWorktimeByShopId(Integer id);

}
