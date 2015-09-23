package com.mvc.footprints.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.footprints.constant.Constant;
import com.mvc.footprints.dao.ICommentDao;
import com.mvc.footprints.dao.IShopDao;
import com.mvc.footprints.dao.IShopImageDao;
import com.mvc.footprints.dao.IShopLikeDao;
import com.mvc.footprints.dao.IShopWorktimeDao;
import com.mvc.footprints.entity.TFileInfo;
import com.mvc.footprints.entity.TShopInfo;
import com.mvc.footprints.entity.TShopLike;
import com.mvc.footprints.entity.TShopWorktime;
import com.mvc.footprints.param.PagerParam;
import com.mvc.footprints.param.ShopParam;
import com.mvc.footprints.service.IShopService;

@Service("shopService")
public class ShopServiceImpl implements IShopService {

	@Autowired
	private IShopDao shopDao;
	
	@Autowired
	private IShopImageDao shopImageDao;
	
	@Autowired
	private ICommentDao commentDao;
	
	@Autowired
	private IShopLikeDao shopLikeDao;
	
	@Autowired
	private IShopWorktimeDao shopWorktimeDao;
	
	@Override
	public List<?> findAll(Class<?> clazz, PagerParam param) {
		return shopDao.findAll(clazz, param);
	}

	@Override
	public void save(Object object) {
		shopDao.save(object);
	}

	@Override
	public void delete(String id) {
		shopDao.delete(shopDao.findById(TShopInfo.class, id));
	}

	@Override
	public void update(Object object) {
		TShopInfo shop = (TShopInfo) object;
		TShopInfo shopInfo = (TShopInfo)shopDao.findById(TShopInfo.class, shop.getId()+"");
		if(StringUtils.isNotBlank(shop.getName())){
			shopInfo.setName(shop.getName());
		}
		if(null != shop.getIsAds()){
			shopInfo.setIsAds(shop.getIsAds());
		}
		if(StringUtils.isNotBlank(shop.getUploadPics())){
			shopInfo.setUploadPics(shop.getUploadPics());
		}
		if(StringUtils.isNotBlank(shop.getLatitude())){
			shopInfo.setLatitude(shop.getLatitude());
		}
		if(StringUtils.isNotBlank(shop.getLongitude())){
			shopInfo.setLongitude(shop.getLongitude());
		}
		if(StringUtils.isNotBlank(shop.getIsbbsdiscounts())){
			shopInfo.setIsDiscount(1);
		}else{
			shopInfo.setIsDiscount(0);
		}
		shopInfo.setIsbbsdiscounts(shop.getIsbbsdiscounts());
		if(StringUtils.isNotBlank(shop.getAddress())){
			shopInfo.setAddress(shop.getAddress());
		}
		if(StringUtils.isNotBlank(shop.getPhoneNumber())){
			shopInfo.setPhoneNumber(shop.getPhoneNumber());
		}
		if(StringUtils.isNotBlank(shop.getWebsite())){
			shopInfo.setWebsite(shop.getWebsite());
		}
		if(StringUtils.isNotBlank(shop.getProvince())){
			shopInfo.setProvince(shop.getProvince());
		}
		if(StringUtils.isNotBlank(shop.getCity())){
			shopInfo.setCity(shop.getCity());
		}
		if(StringUtils.isNotBlank(shop.getClass_())){
			shopInfo.setClass_(shop.getClass_());
		}
		if(StringUtils.isNotBlank(shop.getSubclass())){
			shopInfo.setSubclass(shop.getSubclass());
		}
		if(StringUtils.isNotBlank(shop.getDescription())){
			shopInfo.setDescription(shop.getDescription());
		}
		if(StringUtils.isNotBlank(shop.getComments())){
			shopInfo.setComments(shop.getComments());
		}
		if(shop.getSort() != null){
			shopInfo.setSort(shop.getSort());
		}
		shopDao.update(shopInfo);
	}

	@Override
	public TShopInfo findById(String id) {
		TShopInfo shopInfo = (TShopInfo) shopDao.findById(TShopInfo.class, id);
		
		if(shopInfo != null){
			//填充图片信息
			List<TFileInfo> images = shopImageDao.findFileByShopId(shopInfo.getId());
			shopInfo.setImages(images);
		}
//		List<TComment> comments = commentDao.findByParam(param);
//		shopInfo.setFootprints(comments);
		return shopInfo;
	}

	@Override
	public int findAllCount(Class<?> clazz, PagerParam param) {
		return shopDao.findAllCount(clazz, param);
	}
	
	@Override
	public List<TShopInfo> findShopByParams(ShopParam param){
		return shopDao.findShopByParams(param);
	}

	@Override
	public TShopLike findLikeByParam(ShopParam param) {
		return shopLikeDao.findShopLikeByUserIdAndShopId(param.getUserId(), param.getShopId());
	}

	@Override
	public List<Map<String,Object>> ranking(ShopParam param) {
		return shopLikeDao.ranking(param);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TShopInfo> findAll(Class<?> clazz) {
		return (List<TShopInfo>) shopDao.findAll(clazz);
	}

	@Override
	public List<TShopInfo> isbbsShop(ShopParam param) {
		return shopDao.isbbsShop(param);
	}
	
	@Override
	public void saveWorktime(ShopParam param) {
		//删除之前的信息
		shopWorktimeDao.deleteByShopId(param.getShopId());
		String string = param.getTimes();
		String[] times = string.split(",");
		for (String str : times) {
			String[] time = str.split("-");
			TShopWorktime shopWorktime = new TShopWorktime(param.getShopId(), Integer.valueOf(time[0]), time[1], time[2]);
			shopWorktimeDao.save(shopWorktime);
		}
	}
	
	@Override
	public Long findLikeCountByShopId(Integer shopId) {
		return shopLikeDao.findLikeCountByShopId(shopId);
	}
}
