package com.mvc.footprints.param;


public class YellowPageParam extends PagerParam {

	private String id;
	private String subKindId;
	private String mark;
	private String city;
	private String province;
	private String name;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getSubKindId() {
		return subKindId;
	}

	public void setSubKindId(String subKindId) {
		this.subKindId = subKindId;
	}
	
	
}
