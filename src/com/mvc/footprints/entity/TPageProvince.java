package com.mvc.footprints.entity;

import java.util.List;

/**
 * TProvice entity. @author MyEclipse Persistence Tools
 */

public class TPageProvince implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -68710645240532762L;
	private Integer id;
	private String name;
	private String enName;
	private Integer sort = 9999;
	private Integer type;
	private List<TPageCity> citys;

	// Constructors

	/** default constructor */
	public TPageProvince() {
	}

	/** minimal constructor */
	public TPageProvince(Integer sort) {
		this.sort = sort;
	}

	/** full constructor */
	public TPageProvince(String name, String enName, Integer sort) {
		this.name = name;
		this.enName = enName;
		this.sort = sort;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnName() {
		return this.enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public List<TPageCity> getCitys() {
		return citys;
	}

	public void setCitys(List<TPageCity> citys) {
		this.citys = citys;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}