package com.mvc.footprints.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.footprints.dao.IMessageDao;
import com.mvc.footprints.entity.TCity;
import com.mvc.footprints.entity.TMessage;
import com.mvc.footprints.param.CityParam;
import com.mvc.footprints.param.PagerParam;
import com.mvc.footprints.service.IMessageService;
@Transactional
@Service("messageService")
public class MessageServiceImpl implements IMessageService {

	@Autowired
	private IMessageDao messageDao;
	
	@Override
	public List<?> findAll(Class<?> clazz, PagerParam param) {
		return messageDao.findAll(clazz, param);
	}

	@Override
	public void save(Object object) {
		messageDao.save(object);
	}

	@Override
	public void delete(String id) {
		messageDao.delete(messageDao.findById(TMessage.class, id));
	}

	@Override
	public void update(Object object) {
		messageDao.update(object);
	}

	@Override
	public Object findById(String id) {
		return messageDao.findById(TMessage.class, id);
	}
	
	@Override
	public int findAllCount(Class<?> clazz, PagerParam param) {
		return messageDao.findAllCount(clazz, param);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TMessage> findAll(Class<?> clazz) {
		return (List<TMessage>) messageDao.findAll(clazz);
	}

	@Override
	public List<TMessage> findAll(Class<TMessage> clazz, CityParam param,
			String sort) {
		return (List<TMessage>) messageDao.findAll(clazz, param, sort);
	}
}
