package com.mvc.footprints.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.footprints.dao.IYellowPageInfoDao;
import com.mvc.footprints.entity.TYellowPageInfo;
import com.mvc.footprints.param.PagerParam;
import com.mvc.footprints.param.YellowPageParam;
import com.mvc.footprints.service.IYellowPageInfoService;
@Transactional
@Service("yellowPageInfoService")
public class YellowPageInfoServiceImpl implements IYellowPageInfoService {

	@Autowired
	private IYellowPageInfoDao yellowPageInfoDao;
	
	@Override
	public List<?> findAll(Class<?> clazz, PagerParam param) {
		return yellowPageInfoDao.findAll(clazz, param, true);
	}

	@Override
	public void save(Object object) {
		yellowPageInfoDao.save(object);
	}

	@Override
	public void delete(String id) {
		yellowPageInfoDao.delete(yellowPageInfoDao.findById(TYellowPageInfo.class, id));
	}

	@Override
	public void update(Object object) {
		yellowPageInfoDao.update(object);
	}

	@Override
	public Object findById(String id) {
		return yellowPageInfoDao.findById(TYellowPageInfo.class, id);
	}
	
	@Override
	public int findAllCount(Class<?> clazz, PagerParam param) {
		return yellowPageInfoDao.findAllCount(clazz, param);
	}
	
	@Override
	public List<TYellowPageInfo> findByParam(YellowPageParam param) {
		return yellowPageInfoDao.findByParam(param);
	}
	
	@Override
	public List<TYellowPageInfo> findByMark(String mark) {
		String[] marks = null;
		if(StringUtils.isNotBlank(mark)){
			marks = mark.split(",");
		}
		return yellowPageInfoDao.findByMark(marks);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TYellowPageInfo> findAll(Class<?> clazz) {
		return (List<TYellowPageInfo>) yellowPageInfoDao.findAll(clazz);
	}
	
	@Override
	public List<TYellowPageInfo> findAll(YellowPageParam param) {
		return yellowPageInfoDao.findAll(param);
	}
}
