package com.mvc.footprints.controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.footprints.constant.Constant;
import com.mvc.footprints.entity.TKind;
import com.mvc.footprints.entity.TPageCity;
import com.mvc.footprints.entity.TPageProvince;
import com.mvc.footprints.entity.TSubKind;
import com.mvc.footprints.entity.TYellowPageInfo;
import com.mvc.footprints.param.YellowPageParam;
import com.mvc.footprints.resultmap.JsonResult;
import com.mvc.footprints.resultmap.PagerResult;
import com.mvc.footprints.service.IKindService;
import com.mvc.footprints.service.IPageCityService;
import com.mvc.footprints.service.IPageProvinceService;
import com.mvc.footprints.service.ISubKindService;
import com.mvc.footprints.service.IYellowPageInfoService;

@Controller
@RequestMapping("page")
public class YellowPageController {
	
	@Autowired
	private IYellowPageInfoService yellowPageService;
	
	@Autowired
	private IPageProvinceService provinceService;
	
	@Autowired
	private IPageCityService cityService;
	
	@Autowired
	private IKindService kindService;
	
	@Autowired
	private ISubKindService subKindService;

	@ResponseBody
	@RequestMapping(value="/list")
	public String index(YellowPageParam param){
		PagerResult result = new PagerResult();
		List<TYellowPageInfo> list = (List<TYellowPageInfo>) yellowPageService.findAll(param);
		
		for (TYellowPageInfo tShopInfo : list) {
			TPageProvince province = (TPageProvince) provinceService.findById(tShopInfo.getProvince()+"");
			if(province != null){
				tShopInfo.setProvinceName(province.getName());
			}
			TPageCity city = (TPageCity) cityService.findById(tShopInfo.getCity()+"");
			if(city != null){
				tShopInfo.setCityName(city.getCityName());
			}
			TKind kind = (TKind) kindService.findById(tShopInfo.getKindId()+"");
			if(kind != null){
				tShopInfo.setKindName(kind.getKindName());
			}
			TSubKind subKind = (TSubKind) subKindService.findById(tShopInfo.getSubKindId()+"");
			if(subKind != null){
				tShopInfo.setSubKindName(subKind.getSubKindName());
			}
		}
		
		result.setRows(list);
		
		int count = yellowPageService.findAllCount(TYellowPageInfo.class, param);
		result.setTotal(count);
		return JSONObject.fromObject(result).toString();
	}
	
	@RequestMapping(value="/toAdd")
	public String toAdd(){

		return "/yellowPage/yellowPageAdd";
	}
	
	@ResponseBody
	@RequestMapping(value="/save")
	public String save(TYellowPageInfo yellowPage){
		JsonResult jsonResult = new JsonResult();
		try {
			yellowPageService.save(yellowPage);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@RequestMapping(value="/toForm")
	public String toForm(){
		return "/yellowPage/yellowPageEdit";
	}
	
	@ResponseBody
	@RequestMapping(value="/toEdit")
	public String toEdit(String id){
		TYellowPageInfo yellowPage = (TYellowPageInfo) yellowPageService.findById(id);
		return JSONObject.fromObject(yellowPage).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/update")
	public String update(TYellowPageInfo yellowPage){
		JsonResult jsonResult = new JsonResult();
		try {
			yellowPageService.update(yellowPage);
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
			yellowPageService.delete(id);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/pages")
	public String pages(YellowPageParam param){
		JsonResult jsonResult = new JsonResult();
		try {
			param.setRows(Constant.SHOP_LIST_ROWS);
			List<TYellowPageInfo> pageInfos = yellowPageService.findByParam(param);
			jsonResult.setObj(pageInfos);
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setMsg(e.getMessage());
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/mark")
	public String mark(YellowPageParam param){
		JsonResult jsonResult = new JsonResult();
		try {
			List<TYellowPageInfo> pageInfos = yellowPageService.findByMark(param.getMark());
			jsonResult.setObj(pageInfos);
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	
	@ResponseBody
	@RequestMapping(value="/get")
	public String get(YellowPageParam param){
		JsonResult jsonResult = new JsonResult();
		try {
			TYellowPageInfo pageInfo = (TYellowPageInfo) yellowPageService.findById(param.getId());
			jsonResult.setObj(pageInfo);
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
}
