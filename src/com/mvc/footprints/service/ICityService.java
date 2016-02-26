package com.mvc.footprints.service;

import java.util.List;

import com.mvc.footprints.entity.TCity;
import com.mvc.footprints.param.CityParam;


public interface ICityService extends BaseService{

	List<TCity> findByProvince(Integer id);

	/**
	 * 根据省份查询城市列表
	 * @Title findByProvinceId
	 * @Description findByProvinceId
	 * @param class1
	 * @param param
	 * @return
	 * @return List<TCity> 
	 * @author heshaohua
	 * @date 2016年2月26日 下午2:53:38
	 */
	List<TCity> findByProvinceIdPage(CityParam param);

}
