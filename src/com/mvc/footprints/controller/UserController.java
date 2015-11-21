package com.mvc.footprints.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.footprints.constant.Constant;
import com.mvc.footprints.entity.PreUcenterMembers;
import com.mvc.footprints.entity.TCity;
import com.mvc.footprints.entity.TPhoneCode;
import com.mvc.footprints.resultmap.JsonResult;
import com.mvc.footprints.service.ICityService;
import com.mvc.footprints.service.IPhoneCodeService;
import com.mvc.footprints.service.IUserService;
import com.mvc.footprints.utils.SMSUtils;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICityService cityService;
	
	@Autowired
	private IPhoneCodeService phoneCodeService;

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
	@RequestMapping(value="/verifyusername")
	public String verifyUsername(String username) {
		JsonResult jsonResult = new JsonResult();
		try {
			PreUcenterMembers param = new PreUcenterMembers();
			param.setUsername(new String(username.getBytes("iso-8859-1"), "UTF-8"));
			PreUcenterMembers member = userService.findUserByParam(param);
			if(member == null){
				jsonResult.setCode(Constant.SUCCESS);
				jsonResult.setMsg("此用户名可以使用");
				jsonResult.setResult(true);
			}else{
				jsonResult.setCode(Constant.USER_REGIST_IS_EXIST);
				jsonResult.setMsg("用户名已存在");
				jsonResult.setResult(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setResult(false);
		}
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
	@RequestMapping(value="/sendcode")
	public String sendCode(String phoneNumber) {
		JsonResult jsonResult = new JsonResult();
		try {
			//通过手机号找用户
			PreUcenterMembers member = userService.findUserByMobilePhone(phoneNumber);
			if(member == null){
				jsonResult.setCode(Constant.FAILURE);
				jsonResult.setMsg("请使用绑定账号的手机号获取验证码");
				jsonResult.setResult(false);
			}else{
				String code = RandomStringUtils.random(6, "0123456789");
				String result = SMSUtils.sendSMSGetRequest(phoneNumber, String.format(Constant.SMS_MSG, code));
				userService.savePhoneCode(phoneNumber, code);
				jsonResult.setCode(Constant.SUCCESS);
				jsonResult.setMsg("验证码已发送");
				jsonResult.setResult(true);
			}
		} catch (IOException e) {
			e.printStackTrace();
			jsonResult.setMsg(e.getMessage());
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@ResponseBody
	@RequestMapping("verifycode")
	public String verifyCode(PreUcenterMembers member){
		JsonResult jsonResult = new JsonResult();
		try {
			TPhoneCode phoneCode = phoneCodeService.verifyCode(member.getMobilePhone(), member.getPhcode());
			if(phoneCode != null){
				phoneCode.setStatus(0);
				phoneCodeService.update(phoneCode);
				//校验时间
				Date phoneCodeTime = phoneCode.getTime();
				Date nowTime = new Date();
				
				long subTime = nowTime.getTime() - phoneCodeTime.getTime();
				
				if(subTime > (1000 * 60 * 15)){
					jsonResult.setCode(Constant.MOBILE_PHONE_INVALID);
					jsonResult.setMsg("验证码已失效");
					jsonResult.setResult(false);
				}else{
					member = userService.findUserByMobilePhone(member.getMobilePhone());
					jsonResult.setCode(Constant.SUCCESS);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("uid", member.getUid());
					jsonResult.setObj(map);
					jsonResult.setMsg("验证通过");
					jsonResult.setResult(true);
				}
			}else{
				jsonResult.setCode(Constant.USER_REGIST_CODE_FIRULE);
				jsonResult.setMsg("验证码错误");
				jsonResult.setResult(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setMsg(e.getMessage());
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
	
	@ResponseBody
	@RequestMapping("resetpassword")
	public String resetPassword(PreUcenterMembers member){
		return update(member);
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
