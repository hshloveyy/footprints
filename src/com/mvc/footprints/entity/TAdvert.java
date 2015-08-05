package com.mvc.footprints.entity;

/**
 * TAdvert entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TAdvert implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer cityId;
	private String city;
	private Integer type;
	private Integer pageId;
	private String page;
	private Integer sort;
	private String imageId;

	// Constructors

	/** default constructor */
	public TAdvert() {
	}

	/** minimal constructor */
	public TAdvert(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TAdvert(Integer id, Integer cityId, Integer type, Integer pageId) {
		this.id = id;
		this.cityId = cityId;
		this.type = type;
		this.pageId = pageId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public String getCity() {
		return city;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPageId() {
		return this.pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TAdvert : {\n\t\"id\" : \"");
		builder.append(id);
		builder.append("\", \n\t\"cityId\" : \"");
		builder.append(cityId);
		builder.append("\", \n\t\"city\" : \"");
		builder.append(city);
		builder.append("\", \n\t\"type\" : \"");
		builder.append(type);
		builder.append("\", \n\t\"pageId\" : \"");
		builder.append(pageId);
		builder.append("\", \n\t\"page\" : \"");
		builder.append(page);
		builder.append("\", \n\t\"sort\" : \"");
		builder.append(sort);
		builder.append("\", \n\t\"imageId\" : \"");
		builder.append(imageId);
		builder.append("\"\n}");
		return builder.toString();
	}

	
}