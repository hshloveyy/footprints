package com.mvc.footprints.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.footprints.constant.Constant;
import com.mvc.footprints.dao.IPhoneCodeDao;
import com.mvc.footprints.dao.IPreUcenterMemberDao;
import com.mvc.footprints.entity.PreUcenterMembers;
import com.mvc.footprints.entity.TPhoneCode;
import com.mvc.footprints.resultmap.JsonResult;
import com.mvc.footprints.service.IUserService;
import com.mvc.footprints.utils.MD5Utils;
@Transactional
@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IPreUcenterMemberDao preUcenterMemberDao;
	
	@Autowired
	private IPhoneCodeDao phoneCodeDao;
	
	@Override
	public JsonResult login(String username, String password) {
		PreUcenterMembers preUcenterMember = null;
		JsonResult jsonResult = new JsonResult();
		List<Object> ucList = preUcenterMemberDao
				.createQuery("from PreUcenterMembers pum where pum.username = ? or pum.mobilePhone = ?", new Object[]{username, username});
		if(ucList.size() > 0){
			preUcenterMember = (PreUcenterMembers)ucList.get(0);
			if(preUcenterMember != null){
				String md5Pwd = MD5Utils.generatePassword(MD5Utils.generatePassword(password) + preUcenterMember.getSalt());
				List<Object> list = preUcenterMemberDao
						.createQuery("from PreUcenterMembers pum where (pum.username = ? and pum.password = ?) or (pum.mobilePhone = ? and pum.password = ?)", new Object[]{username, md5Pwd, username, md5Pwd});
				if(list.size() > 0){
					preUcenterMember = (PreUcenterMembers)list.get(0);
					jsonResult.setObj(preUcenterMember);
					jsonResult.setCode(Constant.USER_LOGIN_SC);
				}else{
					jsonResult.setCode(Constant.USER_LOGIN_NOT_MATCH);
					jsonResult.setMsg("用户名密码不匹配");
				}
			}
		}else{
			jsonResult.setCode(Constant.USER_LOGIN_NOT_FOUND);
			jsonResult.setMsg("用户名不存在");
		}
		
		return jsonResult;
	}

	@Override
	public JsonResult regist(PreUcenterMembers member) {
		JsonResult jsonResult = new JsonResult();
		try {
			TPhoneCode phoneCode = phoneCodeDao.verfiyCode(member.getMobilePhone(), member.getPhcode());
			if(phoneCode != null){
				phoneCode.setStatus(0);
				phoneCodeDao.update(phoneCode);
				PreUcenterMembers preUcenterMembers = preUcenterMemberDao.findByUsername(member.getUsername());
				if(preUcenterMembers == null){
					String newPwd = MD5Utils.generatePassword(MD5Utils.generatePassword(member.getPassword()) + member.getSalt());
					member.setPassword(newPwd);
					preUcenterMemberDao.save(member);
					jsonResult.setCode(Constant.USER_REGIST_SC);
					jsonResult.setObj(member);
				}else{
					jsonResult.setCode(Constant.USER_REGIST_IS_EXIST);
					jsonResult.setMsg("用户名已存在");
				}
			}else{
				jsonResult.setCode(Constant.USER_REGIST_CODE_FIRULE);
				jsonResult.setMsg("验证码错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(Constant.USER_REGIST_FAIL);
		}
		return jsonResult;
	}

	@Override
	public JsonResult update(PreUcenterMembers member) {
		JsonResult jsonResult = new JsonResult();
		try {
			PreUcenterMembers ucenterMembers = (PreUcenterMembers) preUcenterMemberDao.findById(PreUcenterMembers.class, member.getUid().toString());
			//密码
			if(StringUtils.isNotBlank(member.getPassword())){
				String newPwd = MD5Utils.generatePassword(MD5Utils.generatePassword(member.getPassword()) + ucenterMembers.getSalt());
				ucenterMembers.setPassword(newPwd);
			}
			//手机号码
			if(StringUtils.isNotBlank(member.getMobilePhone())){
				ucenterMembers.setMobilePhone(member.getMobilePhone());
			}
			
			//修改地区
			if(StringUtils.isNotBlank(member.getAreaId())){
				String area = new String(member.getAreaId().getBytes("iso-8859-1"),"utf-8");
				ucenterMembers.setAreaId(area);
			}
			//修改年龄
			if(member.getAge() != null){
				ucenterMembers.setAge(member.getAge());
			}
			//修改星座
			if(StringUtils.isNotBlank(member.getStar())){
				String star = new String(member.getStar().getBytes("iso-8859-1"),"utf-8");
				ucenterMembers.setStar(star);
			}
			//修改性别
			if(StringUtils.isNotBlank(member.getSex())){
				ucenterMembers.setSex(member.getSex());
			}
			
			//修改头像
			if(StringUtils.isNotBlank(member.getPhotoImgId())){
				ucenterMembers.setPhotoImgId(member.getPhotoImgId());
			}
			//用户名
			if(StringUtils.isNotBlank(member.getUsername())){
				String username = new String(member.getUsername().getBytes("iso-8859-1"),"utf-8");
				PreUcenterMembers preUcenterMembers = preUcenterMemberDao.findByUsername(username);
				if(preUcenterMembers != null){
					ucenterMembers.setUsername(username);
				}else{
					jsonResult.setMsg("用户名已存在");
					jsonResult.setResult(false);
					jsonResult.setCode(2);
					return jsonResult;
				}
			}
			preUcenterMemberDao.update(ucenterMembers);
			jsonResult.setObj(ucenterMembers);
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMsg(e.getMessage());
			jsonResult.setResult(false);
			jsonResult.setCode(Constant.FAILURE);
		}
		return jsonResult;
	}

	@Override
	public PreUcenterMembers findUserByParam(PreUcenterMembers members){
		return preUcenterMemberDao.findUserByParam(members);
	}

	@Override
	public PreUcenterMembers findUserById(Integer userId) {
		return (PreUcenterMembers) preUcenterMemberDao.findById(PreUcenterMembers.class, userId+"");
	}
	
	@Override
	public void savePhoneCode(String phoneNumber, String code) {
		TPhoneCode phoneCode = new TPhoneCode();
		phoneCode.setCode(code);
		phoneCode.setPhone(phoneNumber);
		phoneCode.setTime(new Date());
		phoneCode.setStatus(1);
		phoneCodeDao.save(phoneCode);
	}
	
	@Override
	public PreUcenterMembers findUserByMobilePhone(String phoneNumber) {
		return preUcenterMemberDao.findUserByMobilePhone(phoneNumber);
	}

	@Override
	public PreUcenterMembers findUserByMobilePhoneAndUsername(
			String phoneNumber, String userName) {
		// TODO Auto-generated method stub
		return null;
	}
}
