package com.mvc.footprints.service;

import java.util.List;

import com.mvc.footprints.entity.TMessage;
import com.mvc.footprints.param.CityParam;


public interface IMessageService extends BaseService{

	List<TMessage> findAll(Class<TMessage> clazz, CityParam param, String sort);

}
