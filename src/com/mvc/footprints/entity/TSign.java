package com.mvc.footprints.entity;



/**
 * TSign entity. @author MyEclipse Persistence Tools
 */

public class TSign  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 257402413687346181L;
	private Integer id;
     private Integer userId;
     private Integer shopId;
     private Integer signCount;
     private String createTime;
     private String lastTime;


    // Constructors

    /** default constructor */
    public TSign() {
    }

    
    /** full constructor */
    public TSign(Integer userId, Integer shopId, Integer signCount) {
        this.userId = userId;
        this.shopId = shopId;
        this.signCount = signCount;
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

    public Integer getShopId() {
        return this.shopId;
    }
    
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public Integer getSignCount() {
        return this.signCount;
    }
    
    public void setSignCount(Integer signCount) {
        this.signCount = signCount;
    }

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
   
}