package com.mvc.footprints.entity;


/**
 * TNotice entity. @author MyEclipse Persistence Tools
 */

public class TNotice implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5031973112201930619L;
	private Integer id;
	private String noticeTheme;
	private String noticeContent;
	private String createTime;
	private String millSecond;

	// Constructors

	/** default constructor */
	public TNotice() {
	}

	/** full constructor */
	public TNotice(String noticeTheme, String noticeContent) {
		this.noticeTheme = noticeTheme;
		this.noticeContent = noticeContent;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNoticeTheme() {
		return this.noticeTheme;
	}

	public void setNoticeTheme(String noticeTheme) {
		this.noticeTheme = noticeTheme;
	}

	public String getNoticeContent() {
		return this.noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getMillSecond() {
		return millSecond;
	}

	public void setMillSecond(String millSecond) {
		this.millSecond = millSecond;
	}

}