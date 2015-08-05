package com.mvc.footprints.dao;

import java.util.List;

import com.mvc.footprints.entity.TKind;

public interface IKindDao extends BaseDao{

	List<TKind> findAll();

}
