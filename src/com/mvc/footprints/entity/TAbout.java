package com.mvc.footprints.entity;


/**
 * TAbout entity. @author MyEclipse Persistence Tools
 */

public class TAbout implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6351498289538130868L;
	private Integer id;
	private String aboutContent;
	private String createTime;
	private String millSecond;

	// Constructors

	/** default constructor */
	public TAbout() {
	}

	/** full constructor */
	public TAbout(String aboutContent) {
		this.aboutContent = aboutContent;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAboutContent() {
		return this.aboutContent;
	}

	public void setAboutContent(String aboutContent) {
		this.aboutContent = aboutContent;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TAbout : {\n\t\"id\" : \"");
		builder.append(id);
		builder.append("\", \n\t\"aboutContent\" : \"");
		builder.append(aboutContent);
		builder.append("\", \n\t\"createTime\" : \"");
		builder.append(createTime);
		builder.append("\", \n\t\"millSecond\" : \"");
		builder.append(millSecond);
		builder.append("\"\n}");
		return builder.toString();
	}

}