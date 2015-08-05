package com.mvc.footprints.entity;

import javax.persistence.Transient;

/**
 * TCity entity. @author MyEclipse Persistence Tools
 */

public class TCity implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3831904869380531339L;
	private Integer id;
	private String cityName;
	private String enName;
	private Integer sort = 9999;
	private Integer provinceId;
	private Integer type;
	
	@Transient
	private String provinceName;
	
	

	// Constructors

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	/** default constructor */
	public TCity() {
	}

	/** full constructor */
	public TCity(String cityName, String enName, Integer sort,
			Integer provinceId) {
		this.cityName = cityName;
		this.enName = enName;
		this.sort = sort;
		this.provinceId = provinceId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
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

	public Integer getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}