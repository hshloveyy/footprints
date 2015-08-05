package com.mvc.footprints.entity;

/**
 * TShopWorktime entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TShopWorktime implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer shopId;
	private Integer PWeek;
	private String startTime;
	private String endTime;

	// Constructors

	/** default constructor */
	public TShopWorktime() {
	}

	/** full constructor */
	public TShopWorktime(Integer shopId, Integer PWeek, String startTime,
			String endTime) {
		this.shopId = shopId;
		this.PWeek = PWeek;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getShopId() {
		return this.shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getPWeek() {
		return this.PWeek;
	}

	public void setPWeek(Integer PWeek) {
		this.PWeek = PWeek;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}