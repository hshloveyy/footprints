package com.mvc.footprints.dao;

import java.util.List;

import com.mvc.footprints.entity.TSubKind;

public interface ISubKindDao extends BaseDao{

	List<TSubKind> findByKindId(Integer id);

}
