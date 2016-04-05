package com.mvc.footprints.service;

import java.util.List;

import com.mvc.footprints.entity.TYellowPageInfo;
import com.mvc.footprints.param.YellowPageParam;


public interface IYellowPageInfoService extends BaseService{

	List<TYellowPageInfo> findByParam(YellowPageParam param);

	List<TYellowPageInfo> findByMark(String mark);

	List<TYellowPageInfo> findAll(YellowPageParam param);

}
