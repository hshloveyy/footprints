package com.mvc.footprints.service;

import java.util.List;

import com.mvc.footprints.entity.TKind;


public interface IKindService extends BaseService{

	List<TKind> findAll();

}
