package com.mvc.footprints.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.footprints.dao.IShopImageDao;
import com.mvc.footprints.entity.TFileInfo;
import com.mvc.footprints.entity.TProvince;
import com.mvc.footprints.entity.TShopImage;
import com.mvc.footprints.param.PagerParam;
import com.mvc.footprints.service.IShopImageService;
@Transactional
@Service("shopImageService")
public class ShopImageServiceImpl implements IShopImageService {

	@Autowired
	private IShopImageDao shopImageDao;
	
	@Override
	public List<?> findAll(Class<?> clazz, PagerParam param) {
		return shopImageDao.findAll(clazz, param);
	}

	@Override
	public void save(Object object) {
		shopImageDao.save(object);
	}

	@Override
	public void delete(String id) {
		shopImageDao.delete(shopImageDao.findById(TProvince.class, id));
	}

	@Override
	public void update(Object object) {
		shopImageDao.update(object);
	}

	@Override
	public Object findById(String id) {
		return shopImageDao.findById(TShopImage.class, id);
	}

	@Override
	public int findAllCount(Class<?> clazz, PagerParam param) {
		return shopImageDao.findAllCount(clazz, param);
	}

	@Override
	public List<TFileInfo> findFileByShopId(Integer shopId) {
		return shopImageDao.findFileByShopId(shopId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TShopImage> findAll(Class<?> clazz) {
		return (List<TShopImage>) shopImageDao.findAll(clazz);
	}
}
