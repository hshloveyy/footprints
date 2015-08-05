package com.mvc.footprints.service;

import java.util.List;

import com.mvc.footprints.entity.TAdvert;
import com.mvc.footprints.param.AdvertParam;





public interface IAdvertService extends BaseService{

	List<TAdvert> findByParam(AdvertParam param);

}
