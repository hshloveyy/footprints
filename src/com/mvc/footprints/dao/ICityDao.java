package com.mvc.footprints.dao;

import java.util.List;

import com.mvc.footprints.entity.TCity;

public interface ICityDao extends BaseDao{

	List<TCity> findByProvinceId(Integer id);

}
