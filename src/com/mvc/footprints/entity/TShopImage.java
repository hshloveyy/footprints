package com.mvc.footprints.entity;



/**
 * TShopImage entity. @author MyEclipse Persistence Tools
 */

public class TShopImage  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = -5988248143007356868L;
	private Integer id;
//     private TFileInfo fileInfo;
//     private TShopInfo shopInfo;
     private String type;
     private String imageId;
     private Integer shopId;


    // Constructors

    /** default constructor */
    public TShopImage() {
    }

    
    /** full constructor */
    public TShopImage(Integer id, String type, String imageId, Integer shopId) {
    	this.id = id;
        this.shopId = shopId;
        this.imageId = imageId;
//        this.fileInfo = fileInfo;
//        this.shopInfo = shopInfo;
        this.type = type;
    }
    
    public TShopImage(String type, String imageId, Integer shopId) {
        this.shopId = shopId;
        this.imageId = imageId;
        this.type = type;
    }
    
    public TShopImage(Integer shopId, String imageId) {
        this("1", imageId, shopId);
    }
   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

//    public TFileInfo getFileInfo() {
//        return this.fileInfo;
//    }
//    
//    public void setFileInfo(TFileInfo fileInfo) {
//        this.fileInfo = fileInfo;
//    }

//    public TShopInfo getShopInfo() {
//        return this.shopInfo;
//    }
//    
//    public void setShopInfo(TShopInfo shopInfo) {
//        this.shopInfo = shopInfo;
//    }

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }


	public String getImageId() {
		return imageId;
	}


	public void setImageId(String imageId) {
		this.imageId = imageId;
	}


	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getShopId() {
		return shopId;
	}
   
}