package com.mvc.footprints.entity;

import javax.persistence.Transient;

/**
 * TCommentLike entity. @author MyEclipse Persistence Tools
 */

public class TCommentLike implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3007879289837363102L;
	private Integer id;
	private Integer commentId;
	private Integer userId;
	private Integer likeCount;
	private String createTime;
	private String lastTime;
	private String millsecond;
	private Integer isClick;

	@Transient
	private Integer sumLike;
	// Constructors

	/** default constructor */
	public TCommentLike() {
	}

	/** full constructor */
	public TCommentLike(Integer commentId, Integer userId, Integer likeCount,
			String createTime, String lastTime, String millsecond) {
		this.commentId = commentId;
		this.userId = userId;
		this.likeCount = likeCount;
		this.createTime = createTime;
		this.lastTime = lastTime;
		this.millsecond = millsecond;
	}

	// Property accessors

	
	public Integer getId() {
		return this.id;
	}

	public Integer getSumLike() {
		return sumLike;
	}

	public void setSumLike(Integer sumLike) {
		this.sumLike = sumLike;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
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