package com.mvc.footprints.service;

import java.util.List;

import com.mvc.footprints.entity.TShopWorktime;




public interface IShopWorktimeService extends BaseService{

	List<TShopWorktime> findWorktimeByShopId(Integer id);

}
