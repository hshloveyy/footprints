/*
 * 文件名：PhoneCodeServiceImpl.java
 * 版权：Copyright 2011-2018 Kerrunt Tech. Co. Ltd. All Rights Reserved. 
 * 描述：KURRENT系统系列
 */
package com.mvc.footprints.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.footprints.dao.IPhoneCodeDao;
import com.mvc.footprints.entity.TPhoneCode;
import com.mvc.footprints.param.PagerParam;
import com.mvc.footprints.service.IPhoneCodeService;

/**
 * 修改人： Heshaohua
 * 修改时间：2015年10月22日 上午9:32:02 
 * 修改内容：新增 
 * 类说明：
 */
@Service("phoneCodeService")
public class PhoneCodeServiceImpl implements IPhoneCodeService {

	@Autowired
	private IPhoneCodeDao phoneCodeDao;
	
	@Override
	public Object findById(String id) {
		return phoneCodeDao.findById(TPhoneCode.class, id);
	}

	@Override
	public List<?> findAll(Class<?> clazz, PagerParam param) {
		return phoneCodeDao.findAll(clazz, param);
	}

	@Override
	public List<?> findAll(Class<?> clazz) {
		return phoneCodeDao.findAll(clazz);
	}

	@Override
	public int findAllCount(Class<?> clazz, PagerParam param) {
		return phoneCodeDao.findAllCount(clazz, param);
	}

	@Override
	public void save(Object object) {
		phoneCodeDao.save(object);
	}

	@Override
	public void delete(String id) {
		phoneCodeDao.delete(phoneCodeDao.findById(TPhoneCode.class, id));
	}

	@Override
	public void update(Object object) {
		phoneCodeDao.update(object);
	}

	@Override
	public TPhoneCode verifyCode(String mobilePhone, String phcode) {
		return phoneCodeDao.verfiyCode(mobilePhone, phcode);
	}

}
