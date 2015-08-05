package com.mvc.footprints.service;

import java.util.List;

import com.mvc.footprints.entity.TSubCategory;



public interface ISubCategoryService extends BaseService{

	List<TSubCategory> findByCategoryId(Integer id);
	
	
}
