package com.mvc.footprints.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.footprints.constant.Constant;
import com.mvc.footprints.entity.PreUcenterMembers;
import com.mvc.footprints.entity.TCity;
import com.mvc.footprints.resultmap.JsonResult;
import com.mvc.footprints.service.ICityService;
import com.mvc.footprints.service.IUserService;
import com.mvc.footprints.utils.SMSUtils;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICityService cityService;

	@ResponseBody
	@RequestMapping(value="/test")
	public JsonResult test(@RequestParam(value="title") String title) {
		JsonResult jsonResult = new JsonResult(Constant.SUCCESS);
		return jsonResult;
	}
	
	/**
	 * 登陆接口
	 * @param member
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/login")
	public String login(PreUcenterMembers member) {
		JsonResult jsonResult = null;
		try {
			member.setUsername(new String(member.getUsername().getBytes("iso-8859-1"),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		jsonResult = userService.login(member.getUsername(), member.getPassword());
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	/**
	 * 注册接口
	 * @param member
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/regist")
	public String regist(PreUcenterMembers member) {
		JsonResult jsonResult = userService.regist(member);
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/verifyPhone")
	public String verifyPhone(String phoneNumber) {
		JsonResult jsonResult = new JsonResult();
		try {
			PreUcenterMembers member = userService.findUserByMobilePhone(phoneNumber);
			if(member == null){
				String code = RandomStringUtils.random(6, "0123456789");
				SMSUtils.sendSMSGetRequest(phoneNumber, String.format(Constant.SMS_MSG, code));
				userService.savePhoneCode(phoneNumber, code);
				jsonResult.setCode(Constant.SUCCESS);
				jsonResult.setResult(true);
			}else{
				jsonResult.setCode(Constant.MOBILE_PHONE_IS_EXISTS);
				jsonResult.setMsg("phone number is already exists");
				jsonResult.setResult(true);
			}
		} catch (IOException e) {
			e.printStackTrace();
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@ResponseBody
	@RequestMapping("update")
	public String update(PreUcenterMembers member){
		JsonResult jsonResult = userService.update(member);
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	/**
	 * 获取个人信息
	 * @param member
	 * @return
	 */
	@ResponseBody
	@RequestMapping("info")
	public String info(PreUcenterMembers member){
		JsonResult jsonResult = new JsonResult();
		try {
			PreUcenterMembers m = userService.findUserById(member.getUid());
			if(m != null && StringUtils.isNotBlank(m.getAreaId())){
				if(!isNumber(m.getAreaId())){
					m.setAreaName(m.getAreaId());
				}else{
					TCity city = (TCity) cityService.findById(m.getAreaId());
					if(city != null){
						m.setAreaName(city.getCityName());
					}
				}
			}
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setObj(m);
			jsonResult.setResult(true);
		} catch (Exception e) {
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setMsg(e.getMessage());
			jsonResult.setResult(false);
			e.printStackTrace();
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	private static boolean isNumber(String cha){
		try {
			Integer.parseInt(cha);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}