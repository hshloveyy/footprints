package com.mvc.footprints.resultmap;

public class JsonResult {

	private boolean result;
	private int code;
	private String msg;
	private Object obj;
	
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public JsonResult() {
		super();
	}
	public JsonResult(int code) {
		super();
		this.code = code;
	}
	public JsonResult(boolean result) {
		super();
		this.result = result;
	}
	public JsonResult(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	public JsonResult(boolean result, String msg) {
		super();
		this.result = result;
		this.msg = msg;
	}
	
}
