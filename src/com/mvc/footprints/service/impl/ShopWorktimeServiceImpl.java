package com.mvc.footprints.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.footprints.dao.IShopWorktimeDao;
import com.mvc.footprints.entity.TShopWorktime;
import com.mvc.footprints.param.PagerParam;
import com.mvc.footprints.service.IShopWorktimeService;

@Transactional
@Service("shopWorktimeService")
public class ShopWorktimeServiceImpl implements IShopWorktimeService {

	@Autowired
	private IShopWorktimeDao shopWorktimeDao;
	
	@Override
	public void save(Object object) {
		shopWorktimeDao.save(object);
	}

	@Override
	public void delete(String id) {
		shopWorktimeDao.delete(shopWorktimeDao.findById(TShopWorktime.class, id));
	}

	@Override
	public void update(Object object) {
		shopWorktimeDao.update(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TShopWorktime> findAll(Class<?> clazz, PagerParam param) {
		return (List<TShopWorktime>) shopWorktimeDao.findAll(clazz, param);
	}

	@Override
	public Object findById(String id) {
		return shopWorktimeDao.findById(TShopWorktime.class, id);
	}

	@Override
	public int findAllCount(Class<?> clazz, PagerParam param) {
		return shopWorktimeDao.findAllCount(clazz, param);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TShopWorktime> findAll(Class<?> clazz) {
		return (List<TShopWorktime>) shopWorktimeDao.findAll(clazz);
	}

	@Override
	public List<TShopWorktime> findWorktimeByShopId(Integer id) {
		return shopWorktimeDao.findWorktimeByShopId(id);
	}
}
