package com.mvc.footprints.dao;

import java.util.List;

import com.mvc.footprints.entity.TYellowPageInfo;
import com.mvc.footprints.param.YellowPageParam;

public interface IYellowPageInfoDao extends BaseDao{

	List<TYellowPageInfo> findByParam(YellowPageParam param);

	List<TYellowPageInfo> findByMark(String[] marks);

}
