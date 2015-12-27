package com.mvc.footprints.param;


public class ShopParam extends PagerParam {

	private Integer id;
	private Integer shopId;
	private String name;
	private Integer userId;
	private Integer dist;
	private String mark;
	
	private String uploadPics;

    private Double latitude;
    private Double longitude;

    private String type;//是否优惠
    private String subClass;
    private Integer city;
    private Integer province;
    
    private String times;
    
    private String category;
    
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getSubClass() {
		return subClass;
	}

	public void setSubClass(String subClass) {
		this.subClass = subClass;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUploadPics() {
		return uploadPics;
	}

	public void setUploadPics(String uploadPics) {
		this.uploadPics = uploadPics;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getDist() {
		return dist;
	}

	public void setDist(Integer dist) {
		this.dist = dist;
	}
	
	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShopParam [id=");
		builder.append(id);
		builder.append(", shopId=");
		builder.append(shopId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", dist=");
		builder.append(dist);
		builder.append(", mark=");
		builder.append(mark);
		builder.append(", uploadPics=");
		builder.append(uploadPics);
		builder.append(", latitude=");
		builder.append(latitude);
		builder.append(", longitude=");
		builder.append(longitude);
		builder.append(", type=");
		builder.append(type);
		builder.append(", subClass=");
		builder.append(subClass);
		builder.append(", city=");
		builder.append(city);
		builder.append(", province=");
		builder.append(province);
		builder.append(", times=");
		builder.append(times);
		builder.append(", category=");
		builder.append(category);
		builder.append("]");
		return builder.toString();
	}
	
}
