package com.mvc.footprints.controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.footprints.constant.Constant;
import com.mvc.footprints.entity.TAdvert;
import com.mvc.footprints.entity.TPageCity;
import com.mvc.footprints.entity.TYellowPageInfo;
import com.mvc.footprints.param.AdvertParam;
import com.mvc.footprints.resultmap.JsonResult;
import com.mvc.footprints.resultmap.PagerResult;
import com.mvc.footprints.service.IAdvertService;
import com.mvc.footprints.service.IPageCityService;
import com.mvc.footprints.service.IYellowPageInfoService;

@Controller
@RequestMapping("advert")
public class AdvertController {

	@Autowired
	private IAdvertService advertService;
	
	@Autowired
	private IPageCityService pageCityService;
	
	@Autowired
	private IYellowPageInfoService yellowPageInfoService;
	
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/list")
	public String index(AdvertParam param){
		PagerResult result = new PagerResult();
		List<TAdvert> list = (List<TAdvert>) advertService.findAll(TAdvert.class, param);
		
		for (TAdvert tAdvert : list) {
			TPageCity pageCity = (TPageCity) pageCityService.findById(tAdvert.getCityId().toString());
			if(pageCity != null){
				tAdvert.setCity(pageCity.getCityName());
			}
			
			
			TYellowPageInfo page = (TYellowPageInfo)yellowPageInfoService.findById(tAdvert.getPageId().toString());
			if(page != null){
				tAdvert.setPage(page.getName());
			}
		}
		
		result.setRows(list);
		
		int count = advertService.findAllCount(TAdvert.class, param);
		result.setTotal(count);
		return JSONObject.fromObject(result).toString();
	}
	
	@RequestMapping(value="/toAdd")
	public String toAdd(){

		return "/advert/advertAdd";
	}
	
	@ResponseBody
	@RequestMapping(value="/save")
	public String save(TAdvert advert){
		JsonResult jsonResult = new JsonResult();
		try {
			advertService.save(advert);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@RequestMapping(value="/toForm")
	public String toForm(){
		return "/advert/advertEdit";
	}
	
	@ResponseBody
	@RequestMapping(value="/toEdit")
	public String toEdit(String id){
		TAdvert advert = (TAdvert) advertService.findById(id);
		return JSONObject.fromObject(advert).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/update")
	public String update(TAdvert advert){
		JsonResult jsonResult = new JsonResult();
		try {
			advertService.update(advert);
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
			advertService.delete(id);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}

	@ResponseBody
	@RequestMapping(value="/ads")
	public String ads(AdvertParam param){
		JsonResult result = new JsonResult();
		try {
			List<TAdvert> list = (List<TAdvert>) advertService.findByParam(param);
			
			for (TAdvert tAdvert : list) {
				TPageCity pageCity = (TPageCity) pageCityService.findById(tAdvert.getCityId().toString());
				if(pageCity != null){
					tAdvert.setCity(pageCity.getCityName());
				}
				
				TYellowPageInfo page = (TYellowPageInfo)yellowPageInfoService.findById(tAdvert.getPageId().toString());
				if(page != null){
					tAdvert.setPage(page.getName());
				}
			}
			
			result.setObj(list);
			result.setResult(true);
			result.setCode(Constant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(Constant.FAILURE);
			result.setResult(false);
			result.setMsg(e.getMessage());
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@RequestMapping(value="/uploadmoreadvert")
	public String uploadmoreadvert(){
		
		return "advert/advertMoreImage";
	}
	
	@ResponseBody
	@RequestMapping(value="/uploadImages")
	public String uploadImages(AdvertParam param, String images){
		JsonResult jsonResult = new JsonResult();
		try {
			TAdvert advert = (TAdvert) advertService.findById(param.getId());
			advert.setImageId(images);
			advertService.update(advert);
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
}
