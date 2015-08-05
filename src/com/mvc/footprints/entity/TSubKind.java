package com.mvc.footprints.entity;

/**
 * TSubKind entity. @author MyEclipse Persistence Tools
 */

public class TSubKind implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5136253343827449019L;
	private Integer id;
	private String subKindName;
	private Integer parentId;
	private Integer sort;
	private Integer status;

	// Constructors

	/** default constructor */
	public TSubKind() {
	}

	/** full constructor */
	public TSubKind(String subKindName, Integer parentId, Integer sort) {
		this.subKindName = subKindName;
		this.parentId = parentId;
		this.sort = sort;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubKindName() {
		return this.subKindName;
	}

	public void setSubKindName(String subKindName) {
		this.subKindName = subKindName;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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

}