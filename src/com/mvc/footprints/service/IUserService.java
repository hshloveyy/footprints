package com.mvc.footprints.service;

import com.mvc.footprints.entity.PreUcenterMembers;
import com.mvc.footprints.resultmap.JsonResult;

public interface IUserService {

	/**
	 * 登录接口
	 * @param username
	 * @param password
	 * @return
	 */
	public JsonResult login(String username, String password);
	
	/**
	 * 用户注册接口
	 * @param member
	 * @return
	 */
	public JsonResult regist(PreUcenterMembers member);

	/**
	 * 用户修改信息接口
	 * @param member
	 * @return
	 */
	public JsonResult update(PreUcenterMembers member);

	/**
	 * 条件查询用户
	 * @param member
	 */
	public PreUcenterMembers findUserByParam(PreUcenterMembers member);

	public PreUcenterMembers findUserById(Integer userId);

	public void savePhoneCode(String phoneNumber, String code);

	public PreUcenterMembers findUserByMobilePhone(String phoneNumber);
}
