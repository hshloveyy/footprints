package com.mvc.footprints.entity;

/**
 * TSubCategory entity. @author MyEclipse Persistence Tools
 */

public class TSubCategory implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1359679354109347783L;
	private Integer id;
	private String subCategoryName;
	private Integer categoryId;
	private Integer sort = 9999;
	private Integer status;

	// Constructors

	/** default constructor */
	public TSubCategory() {
	}

	/** full constructor */
	public TSubCategory(String subCategoryName, Integer categoryId, Integer sort) {
		this.subCategoryName = subCategoryName;
		this.categoryId = categoryId;
		this.sort = sort;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubCategoryName() {
		return this.subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TSubCategory : {\n\t\"id\" : \"");
		builder.append(id);
		builder.append("\", \n\t\"subCategoryName\" : \"");
		builder.append(subCategoryName);
		builder.append("\", \n\t\"categoryId\" : \"");
		builder.append(categoryId);
		builder.append("\", \n\t\"sort\" : \"");
		builder.append(sort);
		builder.append("\", \n\t\"status\" : \"");
		builder.append(status);
		builder.append("\"\n}");
		return builder.toString();
	}

}