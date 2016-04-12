package com.mvc.footprints.controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.footprints.entity.TPageCity;
import com.mvc.footprints.entity.TPageProvince;
import com.mvc.footprints.param.CityParam;
import com.mvc.footprints.resultmap.JsonResult;
import com.mvc.footprints.resultmap.PagerResult;
import com.mvc.footprints.service.IPageCityService;
import com.mvc.footprints.service.IPageProvinceService;

@Controller
@RequestMapping("pagecity")
public class PageCityController {
	
	@Autowired
	private IPageCityService pageCityService;
	
	@Autowired
	private IPageProvinceService pageProvinceService;

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/list")
	public String index(CityParam param){
		PagerResult result = new PagerResult();
		List<TPageCity> list = (List<TPageCity>) pageCityService.findAll(TPageCity.class, param);
		
		for (TPageCity tPageCity : list) {
			TPageProvince pageProvince = (TPageProvince) pageProvinceService.findById(tPageCity.getProvinceId()+"");
			if(pageProvince != null){
				tPageCity.setEnName(pageProvince.getName());
			}else{
				tPageCity.setEnName("");
			}
		}
		
		result.setRows(list);
		
		int count = pageCityService.findAllCount(TPageCity.class, param);
		result.setTotal(count);
		return JSONObject.fromObject(result).toString();
	}
	
	@RequestMapping(value="/toAdd")
	public String toAdd(){

		return "/pagecity/pagecityAdd";
	}
	
	@ResponseBody
	@RequestMapping(value="/save")
	public String save(TPageCity pagecity){
		JsonResult jsonResult = new JsonResult();
		try {
			pageCityService.save(pagecity);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@RequestMapping(value="/toForm")
	public String toForm(){
		return "/pagecity/pagecityEdit";
	}
	
	@ResponseBody
	@RequestMapping(value="/toEdit")
	public String toEdit(String id){
		TPageCity pagecity = (TPageCity) pageCityService.findById(id);
		return JSONObject.fromObject(pagecity).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/update")
	public String update(TPageCity pagecity){
		JsonResult jsonResult = new JsonResult();
		try {
			pageCityService.update(pagecity);
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
			pageCityService.delete(id);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
}
