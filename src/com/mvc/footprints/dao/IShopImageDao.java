package com.mvc.footprints.dao;

import java.util.List;

import com.mvc.footprints.entity.TFileInfo;
import com.mvc.footprints.entity.TShopImage;

public interface IShopImageDao extends BaseDao{

	List<TFileInfo> findFileByShopId(Integer shopId);

	TShopImage findByEncryption(String id);

}