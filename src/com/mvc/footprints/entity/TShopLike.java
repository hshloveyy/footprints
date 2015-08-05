package com.mvc.footprints.entity;

import javax.persistence.Transient;

/**
 * TShopLike entity. @author MyEclipse Persistence Tools
 */

public class TShopLike implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2739472307479430468L;
	private Integer id;
	private Integer shopId;
	private String shopName;
	private Integer userId;
	private Integer likeCount;
	private String createTime;
	private String lastTime;
	private String millsecond;
	private Integer isClick;
	
	@Transient
	private Long sumLike;

	// Constructors

	/** default constructor */
	public TShopLike() {
	}

	/** full constructor */
	public TShopLike(Integer shopId, Integer userId, Integer likeCount,
			String createTime, String lastTime, String millsecond) {
		this.shopId = shopId;
		this.userId = userId;
		this.likeCount = likeCount;
		this.createTime = createTime;
		this.lastTime = lastTime;
		this.millsecond = millsecond;
	}

	// Property accessors
	public Long getSumLike() {
		return sumLike;
	}
	
	public void setSumLike(Long sumLike) {
		this.sumLike = sumLike;
	}

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

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getLikeCount() {
		return this.likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public String getMillsecond() {
		return this.millsecond;
	}

	public void setMillsecond(String millsecond) {
		this.millsecond = millsecond;
	}

	public Integer getIsClick() {
		return isClick;
	}

	public void setIsClick(Integer isClick) {
		this.isClick = isClick;
	}

}