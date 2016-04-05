package com.mvc.footprints.entity;

/**
 * TYellowPageInfo entity. @author MyEclipse Persistence Tools
 */

public class TYellowPageInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5877323275154431196L;
	private Integer id;
	private String name;
	private String address;
	private Integer kindId;
	private Integer subKindId;
	private String mark;
	private String lang;
	private String mobile;
	private String website;
	private String email;
	private Integer sort;
	private String moreMsg;
	private Integer province;
	private Integer city;
    private String latitude;
    private String longitude;

	private String provinceName;
	private String cityName;
	private String kindName;
	private String subKindName;

	// Constructors

	/** default constructor */
	public TYellowPageInfo() {
	}

	/** full constructor */
	public TYellowPageInfo(String name, String address, Integer subKindId,
			String mark, String lang, String mobile, String website,
			String email) {
		this.name = name;
		this.address = address;
		this.subKindId = subKindId;
		this.mark = mark;
		this.lang = lang;
		this.mobile = mobile;
		this.website = website;
		this.email = email;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getSubKindId() {
		return this.subKindId;
	}

	public void setSubKindId(Integer subKindId) {
		this.subKindId = subKindId;
	}

	public String getMark() {
		return this.mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getLang() {
		return this.lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getMoreMsg() {
		return moreMsg;
	}

	public void setMoreMsg(String moreMsg) {
		this.moreMsg = moreMsg;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Integer getKindId() {
		return kindId;
	}

	public void setKindId(Integer kindId) {
		this.kindId = kindId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getKindName() {
		return kindName;
	}

	public void setKindName(String kindName) {
		this.kindName = kindName;
	}

	public String getSubKindName() {
		return subKindName;
	}

	public void setSubKindName(String subKindName) {
		this.subKindName = subKindName;
	}

}