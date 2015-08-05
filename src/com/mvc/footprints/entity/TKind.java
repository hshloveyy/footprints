package com.mvc.footprints.entity;

import java.util.List;

/**
 * TKind entity. @author MyEclipse Persistence Tools
 */

public class TKind implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7548689783671666091L;
	private Integer id;
	private String kindName;
	private Integer sort;
	private List<TSubKind> subKinds;

	// Constructors

	/** default constructor */
	public TKind() {
	}

	/** full constructor */
	public TKind(String kindName, Integer sort) {
		this.kindName = kindName;
		this.sort = sort;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKindName() {
		return this.kindName;
	}

	public void setKindName(String kindName) {
		this.kindName = kindName;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public List<TSubKind> getSubKinds() {
		return subKinds;
	}

	public void setSubKinds(List<TSubKind> subKinds) {
		this.subKinds = subKinds;
	}

}