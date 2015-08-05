package com.mvc.footprints.service;

import java.util.List;

import com.mvc.footprints.entity.TFileInfo;


public interface IShopImageService extends BaseService {

	List<TFileInfo> findFileByShopId(Integer shopId);
}
