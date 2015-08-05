package com.mvc.footprints.entity;

/**
 * TMessage entity. @author MyEclipse Persistence Tools
 */

public class TMessage implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2335325385854357974L;
	private Integer id;
	private Integer userId;
	private String email;
	private String theme;
	private String content;
	private String userName;

	// Constructors

	/** default constructor */
	public TMessage() {
	}

	/** full constructor */
	public TMessage(Integer userId, String email, String theme, String content) {
		this.userId = userId;
		this.email = email;
		this.theme = theme;
		this.content = content;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}