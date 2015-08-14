package com.mvc.footprints.controller;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.hsqldb.lib.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.footprints.constant.Constant;
import com.mvc.footprints.entity.TAbout;
import com.mvc.footprints.entity.TDict;
import com.mvc.footprints.entity.TNotice;
import com.mvc.footprints.entity.TProtocol;
import com.mvc.footprints.resultmap.JsonResult;
import com.mvc.footprints.resultmap.PagerResult;
import com.mvc.footprints.service.ICommonService;

@Controller
@RequestMapping("common")
public class CommonController {
	
	@Autowired
	private ICommonService commonService;
	
	@ResponseBody
	@RequestMapping("about")
	public String about(){
		JsonResult jsonResult = new JsonResult();
		try {
			TAbout about = commonService.findAbout();
			jsonResult.setObj(about);
			
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@ResponseBody
	@RequestMapping("getabout")
	public String getAbout(){
		TAbout about = commonService.findAbout();
		return JSONObject.fromObject(about).toString();
	}
	
	@ResponseBody
	@RequestMapping("notice")
	public String notice(){
		JsonResult jsonResult = new JsonResult();
		try {
			List<TNotice> notice = commonService.findAllNotice();
			jsonResult.setObj(notice);
			
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@ResponseBody
	@RequestMapping("noticelist")
	public String noticeList(){
		 PagerResult pagerResult = new PagerResult();
		try {
			List<TNotice> notice = commonService.findAllNotice();
			pagerResult.setRows(notice);
			pagerResult.setTotal(notice.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.fromObject(pagerResult).toString();
	}
	
	@ResponseBody
	@RequestMapping("protocol")
	public String protocol(){
		JsonResult jsonResult = new JsonResult();
		try {
			TProtocol protocol = commonService.findProtocol();
			
			jsonResult.setObj(protocol);
			
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMsg(e.getMessage());
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@ResponseBody
	@RequestMapping("getprotocol")
	public String getProtocol(){
		TProtocol protocol = commonService.findProtocol();
		return JSONObject.fromObject(protocol).toString();
	}
	
	@ResponseBody
	@RequestMapping("addprotocol")
	public String addProtocol(TProtocol protocol){
		JsonResult jsonResult = new JsonResult();
		try {
			Date now = new Date();
			protocol.setCreateTime(DateFormatUtils.format(now, Constant.DATETIME));
			protocol.setMillSecond(now.getTime()+"");
			
			commonService.saveProtocol(protocol);
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMsg(e.getMessage());
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@ResponseBody
	@RequestMapping("addabout")
	public String addAbout(TAbout about){
		JsonResult jsonResult = new JsonResult();
		try {
			Date now = new Date();
			about.setCreateTime(DateFormatUtils.format(now, Constant.DATETIME));
			about.setMillSecond(now.getTime()+"");
			
			commonService.saveAbout(about);
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMsg(e.getMessage());
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@RequestMapping(value="/toAddNotice")
	public String toAddNotice(){

		return "/notice/noticeAdd";
	}
	
	@ResponseBody
	@RequestMapping("addnotice")
	public String addNotice(TNotice notice){
		JsonResult jsonResult = new JsonResult();
		try {
			Date now = new Date();
			notice.setCreateTime(DateFormatUtils.format(now, Constant.DATETIME));
			notice.setMillSecond(now.getTime()+"");
			
			commonService.saveNotice(notice);
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMsg(e.getMessage());
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@RequestMapping(value="/toNoticeForm")
	public String toNoticeForm(){
		return "/notice/noticeEdit";
	}
	
	@ResponseBody
	@RequestMapping(value="/toNoticeEdit")
	public String toNoticeEdit(String id){
		TNotice report = (TNotice) commonService.findNoticeById(id);
		return JSONObject.fromObject(report).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/updateNotice")
	public String updateNotice(TNotice report){
		JsonResult jsonResult = new JsonResult();
		try {
			commonService.updateNotice(report);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/dict")
	public String dict(String key){
		JsonResult jsonResult = new JsonResult();
		try {
			if(StringUtils.isBlank(key)){
				key = "url";
			}
			TDict dict = commonService.findByKey(key);
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setObj(dict);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setMsg(e.getMessage());
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
}
