package com.mvc.footprints.entity;

/**
 * TCommentImage entity. @author MyEclipse Persistence Tools
 */

public class TCommentImage implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8015346085965215179L;
	private Integer id;
	private Integer commentId;
	private String imageId;
	private String type;

	// Constructors

	/** default constructor */
	public TCommentImage() {
	}

	/** full constructor */
	public TCommentImage(Integer commentId, String imageId, String type) {
		this.commentId = commentId;
		this.imageId = imageId;
		this.type = type;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getImageId() {
		return this.imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}