package com.mvc.footprints.entity;

import java.util.List;

/**
 * TCategory entity. @author MyEclipse Persistence Tools
 */

public class TCategory implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8021390236649570120L;
	private Integer id;
	private String categoryName;
	private Integer sort = 9999;
	private List<TSubCategory> subCategorys;

	// Constructors

	/** default constructor */
	public TCategory() {
	}

	/** full constructor */
	public TCategory(String categoryName, Integer sort) {
		this.categoryName = categoryName;
		this.sort = sort;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public List<TSubCategory> getSubCategorys() {
		return subCategorys;
	}

	public void setSubCategorys(List<TSubCategory> subCategorys) {
		this.subCategorys = subCategorys;
	}

}