package com.mvc.footprints.service;

import java.util.List;

import com.mvc.footprints.entity.TSubKind;


public interface ISubKindService extends BaseService{

	List<TSubKind> findByKindId(Integer id);

}
