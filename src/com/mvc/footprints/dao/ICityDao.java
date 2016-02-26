package com.mvc.footprints.dao;

import java.util.List;

import com.mvc.footprints.entity.TCity;
import com.mvc.footprints.param.CityParam;

public interface ICityDao extends BaseDao{

	List<TCity> findByProvinceId(Integer id);

	List<TCity> findByProvinceIdPage(CityParam param);

}
