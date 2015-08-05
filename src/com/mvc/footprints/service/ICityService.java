package com.mvc.footprints.service;

import java.util.List;

import com.mvc.footprints.entity.TCity;


public interface ICityService extends BaseService{

	List<TCity> findByProvince(Integer id);

}
