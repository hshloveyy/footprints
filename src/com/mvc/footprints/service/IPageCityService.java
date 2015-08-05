package com.mvc.footprints.service;

import java.util.List;

import com.mvc.footprints.entity.TPageCity;


public interface IPageCityService extends BaseService{

	List<TPageCity> findByProvince(Integer id);

}
