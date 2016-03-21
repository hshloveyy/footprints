package com.mvc.footprints.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;



/**
 * TShopInfo entity. @author MyEclipse Persistence Tools
 */

public class TShopInfo  implements java.io.Serializable {

    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -7442029634606937237L;
	private Integer id;
     private String name;
     private String shopName;
     private String latitude;
     private String longitude;
     private String dist;
     private String description;
     private String mark;
     private String isbbspoints;
     private String uploadPics;
     private String isbbsdiscounts;
     private String address;
     private String phoneNumber;
     private String website;
     private String mealStyle;
     private String deliveryRange;
     private String price;
     private String hours;
     private String comments;
     private String province;
     private String city;
     private String class_;
     private String subclass;
     private Integer isDiscount;
     private Integer discount;
     private Integer good;
     private Integer isAds;
     private String infoUrl;
     
     @Transient
     private Integer likeCount;
     
     private Integer sort;
//     private Set<TShopImage> images = new HashSet<TShopImage>();
     private List<TFileInfo> images = new ArrayList<TFileInfo>();
     
//     private List<TComment> footprints = new ArrayList<TComment>();
     
     private String category;
     private String subCategory;
     private String provinceName;
     private String cityName;
     
     private List<TShopWorktime> worktimes = new ArrayList<TShopWorktime>();
    // Constructors

    /** default constructor */
    public TShopInfo() {
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

    public String getLatitude() {
        return this.latitude;
    }
    
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return this.longitude;
    }
    
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDist() {
        return this.dist;
    }
    
    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getMark() {
        return this.mark;
    }
    
    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getIsbbspoints() {
        return this.isbbspoints;
    }
    
    public void setIsbbspoints(String isbbspoints) {
        this.isbbspoints = isbbspoints;
    }

    public String getUploadPics() {
        return this.uploadPics;
    }
    
    public void setUploadPics(String uploadPics) {
        this.uploadPics = uploadPics;
    }

    public String getIsbbsdiscounts() {
        return this.isbbsdiscounts;
    }
    
    public void setIsbbsdiscounts(String isbbsdiscounts) {
        this.isbbsdiscounts = isbbsdiscounts;
    }

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWebsite() {
        return this.website;
    }
    
    public void setWebsite(String website) {
        this.website = website;
    }

    public String getMealStyle() {
        return this.mealStyle;
    }
    
    public void setMealStyle(String mealStyle) {
        this.mealStyle = mealStyle;
    }

    public String getDeliveryRange() {
        return this.deliveryRange;
    }
    
    public void setDeliveryRange(String deliveryRange) {
        this.deliveryRange = deliveryRange;
    }

    public String getPrice() {
        return this.price;
    }
    
    public void setPrice(String price) {
        this.price = price;
    }

    public String getHours() {
        return this.hours;
    }
    
    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getComments() {
        return this.comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getProvince() {
        return this.province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }

    public String getClass_() {
        return this.class_;
    }
    
    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public String getSubclass() {
        return this.subclass;
    }
    
    public void setSubclass(String subclass) {
        this.subclass = subclass;
    }

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getIsDiscount() {
		return isDiscount;
	}

	public void setIsDiscount(Integer isDiscount) {
		this.isDiscount = isDiscount;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Integer getIsAds() {
		return isAds;
	}

	public void setIsAds(Integer isAds) {
		this.isAds = isAds;
	}




	public Integer getGood() {
		return good;
	}

	public void setGood(Integer good) {
		this.good = good;
	}

//	public Set<TShopImage> getImages() {
//		return images;
//	}
//
//	public void setImages(Set<TShopImage> images) {
//		this.images = images;
//	}

	public List<TFileInfo> getImages() {
		return images;
	}

	public void setImages(List<TFileInfo> images) {
		this.images = images;
	}

//	public List<TComment> getFootprints() {
//		return footprints;
//	}
//
//	public void setFootprints(List<TComment> footprints) {
//		this.footprints = footprints;
//	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public List<TShopWorktime> getWorktimes() {
		return worktimes;
	}

	public void setWorktimes(List<TShopWorktime> worktimes) {
		this.worktimes = worktimes;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
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

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getInfoUrl() {
		return infoUrl;
	}

	public void setInfoUrl(String infoUrl) {
		this.infoUrl = infoUrl;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TShopInfo : {\n\t\"id\" : \"");
		builder.append(id);
		builder.append("\", \n\t\"name\" : \"");
		builder.append(name);
		builder.append("\", \n\t\"shopName\" : \"");
		builder.append(shopName);
		builder.append("\", \n\t\"latitude\" : \"");
		builder.append(latitude);
		builder.append("\", \n\t\"longitude\" : \"");
		builder.append(longitude);
		builder.append("\", \n\t\"dist\" : \"");
		builder.append(dist);
		builder.append("\", \n\t\"description\" : \"");
		builder.append(description);
		builder.append("\", \n\t\"mark\" : \"");
		builder.append(mark);
		builder.append("\", \n\t\"isbbspoints\" : \"");
		builder.append(isbbspoints);
		builder.append("\", \n\t\"uploadPics\" : \"");
		builder.append(uploadPics);
		builder.append("\", \n\t\"isbbsdiscounts\" : \"");
		builder.append(isbbsdiscounts);
		builder.append("\", \n\t\"address\" : \"");
		builder.append(address);
		builder.append("\", \n\t\"phoneNumber\" : \"");
		builder.append(phoneNumber);
		builder.append("\", \n\t\"website\" : \"");
		builder.append(website);
		builder.append("\", \n\t\"mealStyle\" : \"");
		builder.append(mealStyle);
		builder.append("\", \n\t\"deliveryRange\" : \"");
		builder.append(deliveryRange);
		builder.append("\", \n\t\"price\" : \"");
		builder.append(price);
		builder.append("\", \n\t\"hours\" : \"");
		builder.append(hours);
		builder.append("\", \n\t\"comments\" : \"");
		builder.append(comments);
		builder.append("\", \n\t\"province\" : \"");
		builder.append(province);
		builder.append("\", \n\t\"city\" : \"");
		builder.append(city);
		builder.append("\", \n\t\"class_\" : \"");
		builder.append(class_);
		builder.append("\", \n\t\"subclass\" : \"");
		builder.append(subclass);
		builder.append("\", \n\t\"isDiscount\" : \"");
		builder.append(isDiscount);
		builder.append("\", \n\t\"discount\" : \"");
		builder.append(discount);
		builder.append("\", \n\t\"good\" : \"");
		builder.append(good);
		builder.append("\", \n\t\"isAds\" : \"");
		builder.append(isAds);
		builder.append("\", \n\t\"likeCount\" : \"");
		builder.append(likeCount);
		builder.append("\", \n\t\"sort\" : \"");
		builder.append(sort);
		builder.append("\", \n\t\"images\" : \"");
		builder.append(images);
		builder.append("\", \n\t\"category\" : \"");
		builder.append(category);
		builder.append("\", \n\t\"subCategory\" : \"");
		builder.append(subCategory);
		builder.append("\", \n\t\"provinceName\" : \"");
		builder.append(provinceName);
		builder.append("\", \n\t\"cityName\" : \"");
		builder.append(cityName);
		builder.append("\", \n\t\"worktimes\" : \"");
		builder.append(worktimes);
		builder.append("\"\n}");
		return builder.toString();
	}
	
	
}