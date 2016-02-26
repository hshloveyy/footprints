package com.mvc.footprints.controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.footprints.entity.TCity;
import com.mvc.footprints.entity.TProvince;
import com.mvc.footprints.param.CityParam;
import com.mvc.footprints.resultmap.JsonResult;
import com.mvc.footprints.resultmap.PagerResult;
import com.mvc.footprints.service.ICityService;
import com.mvc.footprints.service.IProvinceService;

@Controller
@RequestMapping("city")
public class CityController {
	
	@Autowired
	private ICityService cityService;
	
	@Autowired
	private IProvinceService provinceService;

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/list")
	public String index(CityParam param){
		PagerResult result = new PagerResult();
		List<TCity> list = (List<TCity>) cityService.findByProvinceIdPage(param);
		
		for (TCity tCity : list) {
			TProvince province = (TProvince) provinceService.findById(tCity.getProvinceId().toString());
			tCity.setProvinceName(province.getName());
		}
		
		result.setRows(list);
		
		int count = cityService.findAllCount(TCity.class, param);
		result.setTotal(count);
		return JSONObject.fromObject(result).toString();
	}
	
	@RequestMapping(value="/toAdd")
	public String toAdd(){

		return "/city/cityAdd";
	}
	
	@ResponseBody
	@RequestMapping(value="/save")
	public String save(TCity city){
		JsonResult jsonResult = new JsonResult();
		try {
			cityService.save(city);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@RequestMapping(value="/toForm")
	public String toForm(){
		return "/city/cityEdit";
	}
	
	@ResponseBody
	@RequestMapping(value="/toEdit")
	public String toEdit(String id){
		TCity city = (TCity) cityService.findById(id);
		return JSONObject.fromObject(city).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/update")
	public String update(TCity city){
		JsonResult jsonResult = new JsonResult();
		try {
			cityService.update(city);
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
			cityService.delete(id);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
}
