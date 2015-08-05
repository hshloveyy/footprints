package com.mvc.footprints.entity;



/**
 * 评论
 * TCommentUser entity. @author MyEclipse Persistence Tools
 */

public class TCommentUser  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 5660449265379041385L;
	private Integer id;
	private Integer commentId;
     private String content;
     private String fromUser;
     private String fromUserName;
     private String createTime;
     private String millisecond;
     
     private String userImageId;

    // Constructors

    /** default constructor */
    public TCommentUser() {
    }

    
    /** full constructor */
    public TCommentUser(String content, String fromUser, String createTime) {
        this.content = content;
        this.fromUser = fromUser;
        this.createTime = createTime;
    }

    // Property accessors

    public String getMillisecond() {
		return millisecond;
	}


	public void setMillisecond(String millisecond) {
		this.millisecond = millisecond;
	}


	public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public String getFromUser() {
        return this.fromUser;
    }
    
    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getUserImageId() {
		return userImageId;
	}

	public void setUserImageId(String userImageId) {
		this.userImageId = userImageId;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

}