package com.mvc.footprints.dao;

import java.util.List;

import com.mvc.footprints.entity.TSubCategory;

public interface ISubCategoryDao extends BaseDao{

	List<TSubCategory> findByCategoryId(Integer id);

}
