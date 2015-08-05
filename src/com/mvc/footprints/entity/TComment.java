package com.mvc.footprints.entity;

import java.util.List;



/**
 * 足迹
 * TComment entity. @author MyEclipse Persistence Tools
 */

public class TComment  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 4239675590975350758L;
	private Integer id;
     private Integer userId;
     private String userName;
     private Integer shopId;
     private String shopName;
     private String commentContent;
     private Double consume;
     private Double grade;
     private String createTime;
     private String createMillsecond;
     private List<TCommentImage> images;
     private Integer likeCount;
     private String userSex;
     /**
      * 用户评论记录数
      */
     private Integer commentCount;
     private String userImageId;

    // Constructors

    /** default constructor */
    public TComment() {
    }

    
    /** full constructor */
    public TComment(Integer userId, Integer shopId, String commentContent, Double consume, Double grade) {
        this.userId = userId;
        this.shopId = shopId;
        this.commentContent = commentContent;
        this.consume = consume;
        this.grade = grade;
    }

   
    // Property accessors

    public String getUserImageId() {
		return userImageId;
	}


	public void setUserImageId(String userImageId) {
		this.userImageId = userImageId;
	}


	public Integer getId() {
        return this.id;
    }
    
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
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

    public Integer getShopId() {
        return this.shopId;
    }
    
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getCommentContent() {
        return this.commentContent;
    }
    
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Double getConsume() {
        return this.consume;
    }
    
    public void setConsume(Double consume) {
        this.consume = consume;
    }

    public Double getGrade() {
        return this.grade;
    }
    
    public void setGrade(Double grade) {
        this.grade = grade;
    }

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public List<TCommentImage> getImages() {
		return images;
	}

	public void setImages(List<TCommentImage> images) {
		this.images = images;
	}

	public String getCreateMillsecond() {
		return createMillsecond;
	}

	public void setCreateMillsecond(String createMillsecond) {
		this.createMillsecond = createMillsecond;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

}