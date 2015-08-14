package com.mvc.footprints.dao;

import com.mvc.footprints.entity.TDict;


public interface IDictDao extends BaseDao{

	TDict findByKey(String key);

}
