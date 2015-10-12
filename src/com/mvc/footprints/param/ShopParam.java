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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShopParam : {\n\t\"id\" : \"");
		builder.append(id);
		builder.append("\", \n\t\"shopId\" : \"");
		builder.append(shopId);
		builder.append("\", \n\t\"name\" : \"");
		builder.append(name);
		builder.append("\", \n\t\"userId\" : \"");
		builder.append(userId);
		builder.append("\", \n\t\"dist\" : \"");
		builder.append(dist);
		builder.append("\", \n\t\"mark\" : \"");
		builder.append(mark);
		builder.append("\", \n\t\"uploadPics\" : \"");
		builder.append(uploadPics);
		builder.append("\", \n\t\"latitude\" : \"");
		builder.append(latitude);
		builder.append("\", \n\t\"longitude\" : \"");
		builder.append(longitude);
		builder.append("\", \n\t\"type\" : \"");
		builder.append(type);
		builder.append("\", \n\t\"subClass\" : \"");
		builder.append(subClass);
		builder.append("\", \n\t\"city\" : \"");
		builder.append(city);
		builder.append("\", \n\t\"times\" : \"");
		builder.append(times);
		builder.append("\"\n}");
		return builder.toString();
	}
	
}
