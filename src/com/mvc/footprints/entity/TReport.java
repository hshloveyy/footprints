package com.mvc.footprints.entity;

/**
 * TReport entity. @author MyEclipse Persistence Tools
 */

public class TReport implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4013312141606027089L;
	private Integer id;
	private Integer reportId;
	private Integer userId;
	private String reportReason;
	private Integer reportType;

	// Constructors

	/** default constructor */
	public TReport() {
	}

	/** full constructor */
	public TReport(Integer userId, String reportReason, Integer reportType) {
		this.userId = userId;
		this.reportReason = reportReason;
		this.reportType = reportType;
	}

	// Property accessors

	public Integer getReportId() {
		return this.reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getReportReason() {
		return this.reportReason;
	}

	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}

	public Integer getReportType() {
		return this.reportType;
	}

	public void setReportType(Integer reportType) {
		this.reportType = reportType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}