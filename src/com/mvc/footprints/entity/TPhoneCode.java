package com.mvc.footprints.entity;

import java.util.Date;

/**
 * TPhoneCode entity. @author MyEclipse Persistence Tools
 */

public class TPhoneCode implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7633719133806597514L;
	private Integer id;
	private String phone;
	private String code;
	private Date time;
	private Integer userId;
	private Integer status;

	// Constructors

	/** default constructor */
	public TPhoneCode() {
	}

	/** full constructor */
	public TPhoneCode(String phone, String code, Date time) {
		this.phone = phone;
		this.code = code;
		this.time = time;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}