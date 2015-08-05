package com.mvc.footprints.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.footprints.dao.IShopLikeDao;
import com.mvc.footprints.entity.TProvince;
import com.mvc.footprints.entity.TShopImage;
import com.mvc.footprints.entity.TShopLike;
import com.mvc.footprints.param.PagerParam;
import com.mvc.footprints.service.IShopLikeService;
@Transactional
@Service("shopLikeService")
public class ShopLikeServiceImpl implements IShopLikeService {

	@Autowired
	private IShopLikeDao shopLikeDao;
	
	@Override
	public List<?> findAll(Class<?> clazz, PagerParam param) {
		return shopLikeDao.findAll(clazz, param);
	}

	@Override
	public void save(Object object) {
		shopLikeDao.save(object);
	}

	@Override
	public void delete(String id) {
		shopLikeDao.delete(shopLikeDao.findById(TProvince.class, id));
	}

	@Override
	public void update(Object object) {
		shopLikeDao.update(object);
	}

	@Override
	public Object findById(String id) {
		return shopLikeDao.findById(TShopImage.class, id);
	}

	@Override
	public int findAllCount(Class<?> clazz, PagerParam param) {
		return shopLikeDao.findAllCount(clazz, param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TShopLike> findAll(Class<?> clazz) {
		return (List<TShopLike>) shopLikeDao.findAll(clazz);
	}
}
