package com.mvc.footprints.entity;

import javax.persistence.Transient;


/**
 * TFileInfo entity. @author MyEclipse Persistence Tools
 */

public class TFileInfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 12052886261266000L;
	private String poid;
	private String encryption;
	private String fileName;
	private String fileExt;
	private Integer status;
	private String remark;
	private String descr;
	private String ext1;
	private String ext2;
	private String ext3;
	private long ext4;
	private String savePath;
	private String saveType;

	// Constructors

	/** default constructor */
	public TFileInfo() {
	}

	/** minimal constructor */
	public TFileInfo(String poid) {
		this.poid = poid;
	}

	/** full constructor */
	public TFileInfo(String poid, String encryption, String fileName,
			String fileExt, Integer status, String remark, String descr,
			String ext1, String ext2, String ext3, long ext4,
			String savePath, String saveType) {
		this.poid = poid;
		this.encryption = encryption;
		this.fileName = fileName;
		this.fileExt = fileExt;
		this.status = status;
		this.remark = remark;
		this.descr = descr;
		this.ext1 = ext1;
		this.ext2 = ext2;
		this.ext3 = ext3;
		this.ext4 = ext4;
		this.savePath = savePath;
		this.saveType = saveType;
	}

	// Property accessors

	public String getPoid() {
		return this.poid;
	}

	public void setPoid(String poid) {
		this.poid = poid;
	}

	public String getEncryption() {
		return this.encryption;
	}

	public void setEncryption(String encryption) {
		this.encryption = encryption;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileExt() {
		return this.fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	@Transient
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	@Transient
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Transient
	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}
	@Transient
	public String getExt1() {
		return this.ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	@Transient
	public String getExt2() {
		return this.ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	@Transient
	public String getExt3() {
		return this.ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	@Transient
	public long getExt4() {
		return this.ext4;
	}

	public void setExt4(long size) {
		this.ext4 = size;
	}
	@Transient
	public String getSavePath() {
		return this.savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	@Transient
	public String getSaveType() {
		return this.saveType;
	}

	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}

}