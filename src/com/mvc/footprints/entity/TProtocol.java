package com.mvc.footprints.entity;



/**
 * TProtocol entity. @author MyEclipse Persistence Tools
 */

public class TProtocol implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4455183690411105495L;
	// Fields

	private Integer id;
	private String protocolContent;
	private String createTime;
	private String millSecond;

	// Constructors

	/** default constructor */
	public TProtocol() {
	}

	/** full constructor */
	public TProtocol(String protocolContent) {
		this.protocolContent = protocolContent;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProtocolContent() {
		return this.protocolContent;
	}

	public void setProtocolContent(String protocolContent) {
		this.protocolContent = protocolContent;
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