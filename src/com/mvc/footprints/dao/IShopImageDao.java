package com.mvc.footprints.dao;

import java.util.List;

import com.mvc.footprints.entity.TFileInfo;

public interface IShopImageDao extends BaseDao{

	List<TFileInfo> findFileByShopId(Integer shopId);

}