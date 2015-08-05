package com.mvc.footprints.dao;

import java.util.List;

import com.mvc.footprints.entity.TAdvert;
import com.mvc.footprints.param.AdvertParam;


public interface IAdvertDao extends BaseDao{

	List<TAdvert> findByParam(AdvertParam param);

}
