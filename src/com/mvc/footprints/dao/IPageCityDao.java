package com.mvc.footprints.dao;

import java.util.List;

import com.mvc.footprints.entity.TPageCity;

public interface IPageCityDao extends BaseDao{

	List<TPageCity> findByProvinceId(Integer id);

}
