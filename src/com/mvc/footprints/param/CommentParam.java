package com.mvc.footprints.param;


public class CommentParam extends PagerParam {

	private String id;
	private Integer commentId;
	private Integer userId;
	private Integer shopId;
	private String commentContent;

	public CommentParam() {
		// TODO Auto-generated constructor stub
	}
	
	public CommentParam(Integer commentId) {
		this.commentId = commentId;
	}
	
	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	
	
}
