package com.mvc.footprints.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.footprints.constant.Constant;
import com.mvc.footprints.entity.TCategory;
import com.mvc.footprints.entity.TCity;
import com.mvc.footprints.entity.TFileInfo;
import com.mvc.footprints.entity.TProvince;
import com.mvc.footprints.entity.TShopImage;
import com.mvc.footprints.entity.TShopInfo;
import com.mvc.footprints.entity.TShopLike;
import com.mvc.footprints.entity.TShopWorktime;
import com.mvc.footprints.entity.TSubCategory;
import com.mvc.footprints.param.ShopParam;
import com.mvc.footprints.resultmap.JsonResult;
import com.mvc.footprints.resultmap.PagerResult;
import com.mvc.footprints.service.ICategoryService;
import com.mvc.footprints.service.ICityService;
import com.mvc.footprints.service.ICommentService;
import com.mvc.footprints.service.IProvinceService;
import com.mvc.footprints.service.IShopImageService;
import com.mvc.footprints.service.IShopLikeService;
import com.mvc.footprints.service.IShopService;
import com.mvc.footprints.service.IShopWorktimeService;
import com.mvc.footprints.service.ISubCategoryService;
import com.mvc.footprints.utils.DateUtils;
import com.mvc.footprints.utils.DistanceUtils;

@Controller
@RequestMapping("shop")
public class ShopController {

	private static final int CLICKED = 1;
	private static final int UNCLICK = 0;
	
	@Autowired
	private IShopService shopService;
	
	@Autowired
	private IShopImageService shopImageService;
	
	@Autowired
	private ICommentService commentService;
	
	@Autowired
	private IShopLikeService shopLikeService;
	
	@Autowired
	private IShopWorktimeService shopWorktimeService;
	
	@Autowired
	private IProvinceService provinceService;
	
	@Autowired
	private ICityService cityService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private ISubCategoryService subCategoryService;

	@ResponseBody
	@RequestMapping(value="/list")
	public String index(ShopParam param){
		PagerResult result = new PagerResult();
		List<TShopInfo> list = (List<TShopInfo>) shopService.findAll(param);
		
		for (TShopInfo tShopInfo : list) {
			TProvince province = (TProvince) provinceService.findById(tShopInfo.getProvince());
			if(province != null){
				tShopInfo.setProvinceName(province.getName());
			}
			TCity city = (TCity) cityService.findById(tShopInfo.getCity());
			if(city != null){
				tShopInfo.setCityName(city.getCityName());
			}
			TCategory category = (TCategory) categoryService.findById(tShopInfo.getClass_());
			if(category != null){
				tShopInfo.setCategory(category.getCategoryName());
			}
			TSubCategory subCategory = (TSubCategory) subCategoryService.findById(tShopInfo.getSubclass());
			if(subCategory != null){
				tShopInfo.setSubCategory(subCategory.getSubCategoryName());
			}
		}
		result.setRows(list);
		
		int count = shopService.findAllCount(TShopInfo.class, param);
		result.setTotal(count);
		return JSONObject.fromObject(result).toString();
	}
	
	@RequestMapping(value="/toAdd")
	public String toAdd(){

		return "/shop/shopAdd";
	}
	
	@ResponseBody
	@RequestMapping(value="/save")
	public String save(TShopInfo shop){
		JsonResult jsonResult = new JsonResult();
		try {
			if(StringUtils.isNotBlank(shop.getIsbbsdiscounts())){
				shop.setIsDiscount(1);
			}else{
				shop.setIsDiscount(0);
			}
			if(shop.getSort() == null){
				shop.setSort(9999);
			}
			shopService.save(shop);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@RequestMapping(value="/toForm")
	public String toForm(){
		return "/shop/shopEdit";
	}
	
	@ResponseBody
	@RequestMapping(value="/toEdit")
	public String toEdit(String id){
		TShopInfo shop = (TShopInfo) shopService.findById(id);
		return JSONObject.fromObject(shop).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/update")
	public String update(TShopInfo shop){
		JsonResult jsonResult = new JsonResult();
		try {
			shopService.update(shop);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/delete")
	public String delete(String id){
		JsonResult jsonResult = new JsonResult();
		try {
			shopService.delete(id);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="get")
	public String findOne(ShopParam param){
		JsonResult jsonResult = new JsonResult();
		TShopInfo shopInfo = (TShopInfo) shopService.findById(""+param.getShopId());
		
		if(shopInfo != null){
			shopInfo.setLikeCount(shopInfo.getGood());
			List<TShopWorktime> worktimes = shopWorktimeService.findWorktimeByShopId(shopInfo.getId());
			shopInfo.setWorktimes(worktimes);
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setObj(shopInfo);
		}else{
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setMsg("Not found Shop.");
		}
//		JsonConfig config = new JsonConfig();
//		config.setExcludes(new String[] {"images"});
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	/**
	 * 商铺列表查询
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/shopList")
	public String shopList(ShopParam param){
		JsonResult jsonResult = new JsonResult();
		try {
			param.setRows(Constant.SHOP_LIST_ROWS);
//			TShopInfo shopInfo = new TShopInfo();
//			shopInfo.setId(param.getId());
			if(param.getPage() < 1){
				param.setPage(1);
			}
			if(StringUtils.isNotBlank(param.getName())){
//				shopInfo.setName(new String(param.getName().getBytes("iso-8859-1"),"utf-8"));
				param.setName(new String(param.getName().getBytes("iso-8859-1"),"utf-8"));
			}
			List<TShopInfo> list = (List<TShopInfo>) shopService.findShopByParams(param);
//			List<TShopInfo> list = (List<TShopInfo>) shopService.findAll(TShopInfo.class, param);
			//判断是否在给定距离内
//			if(param.getLatitude()  0 && param.getLongitude() > 0){
//				for (TShopInfo tShopInfo : list) {
//					
//				}
//			}
			
			//加载商铺图片和足迹
			for (TShopInfo tShopInfo : list) {
				List<TFileInfo> images = shopImageService.findFileByShopId(tShopInfo.getId());
				tShopInfo.setImages(images);
				
//				List<TComment> footprints = commentService.findCommentByShopId(tShopInfo.getId());
//				tShopInfo.setFootprints(footprints);
			}
			
			
			jsonResult.setObj(list);
			jsonResult.setCode(Constant.SUCCESS);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	/**
	 * 附近商铺查询接口
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("nearby")
	public String nearby(ShopParam param){
		JsonResult jsonResult = new JsonResult();
		try {
			if(param.getPage() < 1){
				param.setPage(1);
			}
			if(param.getRows() < 1){
				param.setRows(Constant.SHOP_LIST_ROWS);
			}
			if(StringUtils.isNotBlank(param.getName())){
				param.setName(new String(param.getName().getBytes("iso-8859-1"),"utf-8"));
			}
//			List<TShopInfo> list = (List<TShopInfo>) shopService.isbbsShop(param);
			List<TShopInfo> list = (List<TShopInfo>) shopService.findAll(TShopInfo.class);
			
			List<TShopInfo> source = new ArrayList<TShopInfo>();
			//判断是否在给定距离内
			if(Math.abs(param.getLatitude()) > 0 && Math.abs(param.getLongitude()) > 0){
				Integer sourceDist = 20000;
				if(param.getDist() != null){
					sourceDist = param.getDist();
				}
				for (TShopInfo tShopInfo : list) {
					if (StringUtils.isNotEmpty(tShopInfo.getLatitude())
							&& StringUtils.isNotEmpty(tShopInfo.getLongitude())
							&& Math.abs(Double.valueOf(tShopInfo.getLatitude())) > 0
							&& Math.abs(Double.valueOf(tShopInfo.getLongitude())) > 0) {
						Double dist = DistanceUtils.getDistance(Math.abs(param.getLongitude()),
								param.getLatitude(),
								Math.abs(Double.valueOf(tShopInfo.getLongitude())),
								Double.valueOf(tShopInfo.getLatitude()));
						if(dist <= sourceDist){
							tShopInfo.setDist(dist.longValue() + "");
							source.add(tShopInfo);
						}
					}
				}
				
				shopSortByDist(param, source);
				
				source = pageShop(param, source);
			}
			
			//加载商铺图片和足迹
			for (TShopInfo tShopInfo : source) {
				List<TFileInfo> images = shopImageService.findFileByShopId(tShopInfo.getId());
				tShopInfo.setImages(images);
				
//				List<TComment> footprints = commentService.findCommentByShopId(tShopInfo.getId());
//				tShopInfo.setFootprints(footprints);
				
				List<TShopWorktime> worktimes = shopWorktimeService.findWorktimeByShopId(tShopInfo.getId());
				tShopInfo.setWorktimes(worktimes);
				
				Long likeCount = shopService.findLikeCountByShopId(tShopInfo.getId());
				Integer count = likeCount != null ? likeCount.intValue() : 0;
				tShopInfo.setLikeCount(count);
				tShopInfo.setGood(count);
			}
			
			jsonResult.setObj(source);
			jsonResult.setCode(Constant.SUCCESS);
		} catch (UnsupportedEncodingException e) {
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	/**
	 * 根据用户id跟商铺id获取点赞情况信息
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("shoplike")
	public String shopLike(ShopParam param){
		JsonResult jsonResult = new JsonResult();
		try {
			TShopLike shopLike = shopService.findLikeByParam(param);
			if(shopLike != null){
				Long sumLike = shopService.findLikeCountByShopId(param.getShopId());
				shopLike.setSumLike(sumLike);
				shopLike.setLikeCount(sumLike.intValue());
				jsonResult.setObj(shopLike);
			}
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setMsg(e.getMessage());
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	/**
	 * 用户对商铺点赞
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("like")
	public String likeShop(ShopParam param, int like){
		JsonResult jsonResult = new JsonResult();
		try {
			//查询用户之前有没有对该店铺点赞的记录
			TShopLike shopLike = shopService.findLikeByParam(param);
			Date now = new Date();
			
			if(shopLike == null){//用户第一次对该店铺点赞
				shopLike = new TShopLike();
				
				TShopInfo shopInfo = (TShopInfo) shopService.findById(param.getShopId().toString());
				shopLike.setUserId(param.getUserId());
				shopLike.setShopId(param.getShopId());
				shopLike.setShopName(shopInfo.getName());
				shopLike.setCreateTime(DateFormatUtils.format(now, Constant.DATETIME));
				shopLike.setLastTime(DateFormatUtils.format(now, Constant.DATETIME));
				shopLike.setMillsecond(now.getTime()+"");
				shopLike.setIsClick(UNCLICK);
			}else{//用户有对该店铺点过赞
				if(like == -1 || !DateUtils.isSameMonth(now, new Date(Long.valueOf(shopLike.getMillsecond()))) || shopLike.getIsClick() == 1){
					shopLike.setLastTime(DateFormatUtils.format(now, Constant.DATETIME));
					shopLike.setMillsecond(now.getTime() + "");
					if(like == -1){
						shopLike.setIsClick(CLICKED);
					}else{
						shopLike.setIsClick(UNCLICK);
					}
				}else{
					shopLike.setIsClick(UNCLICK);
					jsonResult.setCode(Constant.LIKED);
					jsonResult.setMsg("当月已经点赞了，本月不能点赞");
					jsonResult.setResult(false);
					return JSONObject.fromObject(jsonResult).toString();
				}
			}
			Integer sourceLike = shopLike.getLikeCount();
			if(sourceLike == null){
				sourceLike = 0;
			}
			if(sourceLike + like < 0){
				shopLike.setLikeCount(0);
			}else{
				shopLike.setLikeCount(sourceLike + like);
			}
			
			shopLikeService.save(shopLike);
			jsonResult.setObj(shopLike);
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	/**
	 * 获取排行榜信息
	 */
	@ResponseBody
	@RequestMapping("ranking")
	public String ranking(ShopParam param){
		JsonResult jsonResult = new JsonResult();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<TShopInfo> allShops = new ArrayList<TShopInfo>();
			param.setType(Constant.RANKING_TYPE_YEAR);
			List<Map<String, Object>> maps = shopService.ranking(param);
			for (Map<String, Object> map : maps) {
				String shopId = map.get("shopId").toString();
				String likeCount = map.get("likeCount").toString();
				TShopInfo shopInfo = (TShopInfo) shopService.findById(shopId);
				shopInfo.setLikeCount(Integer.valueOf(likeCount));
				
				allShops.add(shopInfo);
			}
			resultMap.put("all", allShops);
			

			List<TShopInfo> monthShops = new ArrayList<TShopInfo>();
			param.setType(Constant.RANKING_TYPE_MONTH);
			List<Map<String, Object>> mapsMonth = shopService.ranking(param);
			for (Map<String, Object> map : mapsMonth) {
				String shopId = map.get("shopId").toString();
				String likeCount = map.get("likeCount").toString();
				TShopInfo shopInfo = (TShopInfo) shopService.findById(shopId);
				shopInfo.setLikeCount(Integer.valueOf(likeCount));
				
				monthShops.add(shopInfo);
			}
			resultMap.put("month", monthShops);
			
			jsonResult.setObj(resultMap);
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	/**
	 * 跳转上传多张图片
	 * @return
	 */
	@RequestMapping("uploadmore")
	public String uploadMore(){
		
		return "/shop/shopMoreImage";
	}
	
	/**
	 * 
	 */
	@ResponseBody
	@RequestMapping("uploadImages")
	public String updateShopImage(ShopParam param, String images){
		JsonResult jsonResult = new JsonResult();
		try {
			String[] str = images.split(",");
			for (String imageId : str) {
				if(StringUtils.isNotBlank(imageId)){
					TShopImage shopImage = new TShopImage(param.getId(), imageId);
					shopImageService.save(shopImage);
				}
			}
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setResult(true);
		} catch (Exception e) {
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setResult(false);
			jsonResult.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	/**
	 * 获取排行榜信息
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("isbbs")
	public String isbbs(ShopParam param){
		JsonResult jsonResult = new JsonResult();
		try {
			if (param.getCity() != null && !"0".equals(param.getCity().toString())) {
				param.setRows(20);
				List<TShopInfo> shops = shopService.isbbsShop(param);
				//排序
				shopSortByDist(param, shops);
				
				shops = pageShop(param, shops);
				if(shops == null){
					jsonResult.setMsg("没有数据了");
				}
				//加载商铺图片和足迹
				loadExtract(shops);
				jsonResult.setObj(shops);
			}else{//附近搜索
				List<TShopInfo> list = (List<TShopInfo>) shopService.findAll(TShopInfo.class);
				
				List<TShopInfo> source = new ArrayList<TShopInfo>();
				//判断是否在给定距离内
				if(Math.abs(param.getLatitude()) > 0 && Math.abs(param.getLongitude()) > 0){
					Integer sourceDist = 20000;
					if(param.getDist() != null){
						sourceDist = param.getDist();
					}
					
					//判断是否在给定距离内
					for (TShopInfo tShopInfo : list) {
						if (StringUtils.isNotEmpty(tShopInfo.getLatitude())
								&& StringUtils.isNotEmpty(tShopInfo.getLongitude())
								&& Math.abs(Double.valueOf(tShopInfo.getLatitude())) > 0
								&& Math.abs(Double.valueOf(tShopInfo.getLongitude())) > 0) {
							double dist = DistanceUtils.getDistance(Math.abs(param.getLongitude()),
									param.getLatitude(),
									Math.abs(Double.valueOf(tShopInfo.getLongitude())),
									Double.valueOf(tShopInfo.getLatitude()));
							if(dist <= sourceDist && isNeed(param, tShopInfo) && isDiscount(param, tShopInfo)){
								tShopInfo.setDist(dist + "");
								source.add(tShopInfo);
							}
						}
					}
					
					//按距离排序
					List<Double> dists = new ArrayList<Double>();
					Map<Double, TShopInfo> map = new HashMap<Double, TShopInfo>();
					for (TShopInfo tShopInfo : source) {
						Double dist = DistanceUtils.getDistance(param
								.getLongitude().doubleValue(), param.getLatitude()
								.doubleValue(), Double.valueOf(tShopInfo
								.getLongitude()), Double.valueOf(tShopInfo
								.getLatitude()));
						dists.add(dist + tShopInfo.getId());
						map.put(dist + tShopInfo.getId(), tShopInfo);
					}
					Collections.sort(dists);
					source.clear();
					for (Double double1 : dists) {
						source.add(map.get(double1));
					}
					
					source = pageShop(param, source);
				
					loadExtract(source);

				}
				jsonResult.setObj(source);
			}
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setMsg(e.getMessage());
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}

	/**
	 * @param param
	 * @param source
	 * @return
	 */
	private List<TShopInfo> pageShop(ShopParam param, List<TShopInfo> source) {
		int page = param.getPage();
		int rows = 20;
		int limit = 0;
		if((page - 1) * rows < source.size() && page * rows < source.size()){//
			limit = page * rows;
		}else if((page - 1) * rows < source.size() && page * rows > source.size()){
			limit = source.size();
		}else{
			return new ArrayList<TShopInfo>();
		}
		//分页
		source = source.subList((page - 1) * rows, limit);
		return source;
	}

	/**
	 * @param shops
	 */
	private void loadExtract(List<TShopInfo> shops) {
		for (TShopInfo tShopInfo : shops) {
			List<TFileInfo> images = shopImageService
					.findFileByShopId(tShopInfo.getId());
			tShopInfo.setImages(images);

			List<TShopWorktime> worktimes = shopWorktimeService
					.findWorktimeByShopId(tShopInfo.getId());
			tShopInfo.setWorktimes(worktimes);

			Long likeCount = shopService
					.findLikeCountByShopId(tShopInfo.getId());
			Integer count = likeCount != null ? likeCount.intValue()
					: 0;
			tShopInfo.setGood(count);
			tShopInfo.setLikeCount(count);
		}
	}

	/**
	 * @param param
	 * @param shops
	 */
	private void shopSortByDist(ShopParam param, List<TShopInfo> shops) {
		List<Double> dists = new ArrayList<Double>();
		Map<Double, TShopInfo> map = new HashMap<Double, TShopInfo>();
		for (TShopInfo tShopInfo : shops) {
			Double dist = DistanceUtils.getDistance(param
					.getLongitude().doubleValue(), param.getLatitude()
					.doubleValue(), Double.valueOf(tShopInfo
					.getLongitude()), Double.valueOf(tShopInfo
					.getLatitude()));
			tShopInfo.setDist(dist.longValue() + "");
			dists.add(dist);
			map.put(dist, tShopInfo);
		}
		Collections.sort(dists);
		shops.clear();
		for (Double double1 : dists) {
			shops.add(map.get(double1));
		}
	}

	private boolean isDiscount(ShopParam param, TShopInfo tShopInfo) {
		if(StringUtils.isBlank(param.getType()) || (StringUtils.isNotBlank(param.getType()) && "0".equals(param.getType())) ||
				("1".equals(tShopInfo.getIsDiscount() + ""))){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * @param param
	 * @param tShopInfo
	 * @return
	 */
	private boolean isNeed(ShopParam param, TShopInfo tShopInfo) {
		if(StringUtils.isBlank(param.getSubClass()) || 
				(StringUtils.isNotBlank(param.getSubClass()) && "0".equals(param.getSubClass()))){
			return true;
		}else{
			TSubCategory subCategory = (TSubCategory) subCategoryService.findById(param.getSubClass());
			if(subCategory != null){
				if(1 == subCategory.getStatus()){
					if(param.getSubClass().equals(tShopInfo.getSubclass())){
						return true;
					}
				}else{
					if((subCategory.getCategoryId() + "").equals(tShopInfo.getClass_())){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@RequestMapping("toWorktime")
	public String toWorktime(Integer shopId, ModelMap map){
		List<TShopWorktime> worktimes = shopWorktimeService.findWorktimeByShopId(shopId);
		map.addAttribute("worktimes", worktimes);
		return "/shop/shopWorktime";
	}
	
	/**
	 * 保存营业时间信息
	 */
	@ResponseBody
	@RequestMapping("savetime")
	public String savetime(ShopParam param){
		JsonResult jsonResult = new JsonResult();
		try {
			shopService.saveWorktime(param);
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setResult(false);
			jsonResult.setMsg(e.getMessage());
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	/**
	 * 获取商铺图片
	 * @Title shopImage
	 * @Description shopImage
	 * @param param
	 * @return
	 * @return String 
	 * @author heshaohua
	 * @date 2016年3月7日 下午4:21:18
	 */
	@ResponseBody
	@RequestMapping("shopimage")
	public String shopImage(ShopParam param){
		List<TFileInfo> images = shopImageService.findFileByShopId(param.getId());
		return JSONArray.fromObject(images).toString();
	}
	
	/**
	 * 删除商铺图片
	 * @Title shopImage
	 * @Description shopImage
	 * @param param
	 * @return
	 * @return String 
	 * @author heshaohua
	 * @date 2016年3月21日 上午9:49:12
	 */
	@ResponseBody
	@RequestMapping("deleteimage")
	public String deleteImage(String id){
		JsonResult jsonResult = new JsonResult();
		try {
			shopImageService.deleteByEncryption(id);
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setResult(false);
			jsonResult.setMsg(e.getMessage());
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
}
