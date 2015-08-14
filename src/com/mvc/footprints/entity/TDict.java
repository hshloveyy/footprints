package com.mvc.footprints.entity;


/**
 * TAbout entity. @author MyEclipse Persistence Tools
 */

public class TDict implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6351498289538130868L;
	private Integer id;
	private String key;
	private String value;
	private String description;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TDict : {\n\t\"id\" : \"");
		builder.append(id);
		builder.append("\", \n\t\"key\" : \"");
		builder.append(key);
		builder.append("\", \n\t\"value\" : \"");
		builder.append(value);
		builder.append("\", \n\t\"desc\" : \"");
		builder.append(description);
		builder.append("\"\n}");
		return builder.toString();
	}
	
	
}