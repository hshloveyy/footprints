package com.mvc.footprints.controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.footprints.constant.Constant;
import com.mvc.footprints.entity.TCity;
import com.mvc.footprints.entity.TPageCity;
import com.mvc.footprints.entity.TPageProvince;
import com.mvc.footprints.entity.TProvince;
import com.mvc.footprints.param.ProvinceParam;
import com.mvc.footprints.resultmap.JsonResult;
import com.mvc.footprints.resultmap.PagerResult;
import com.mvc.footprints.service.IPageCityService;
import com.mvc.footprints.service.IPageProvinceService;

@Controller
@RequestMapping("pageprovince")
public class PageProvinceController {
	
	@Autowired
	private IPageProvinceService pageProvinceService;
	
	@Autowired
	private IPageCityService pageCityService;

	/**
	 * 省份列表
	 * @return
	 */
	@RequestMapping(value="/index")
	public String provinceIndex(ProvinceParam param){

		return "/pageprovince/pageprovince_manage";
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="list")
	public String list(ProvinceParam param){
		PagerResult result = new PagerResult();
		List<TPageProvince> list = (List<TPageProvince>) pageProvinceService.findAll(TPageProvince.class, param);
		result.setRows(list);
		
		int count = pageProvinceService.findAllCount(TPageProvince.class, param);
		result.setTotal(count);
		return JSONObject.fromObject(result).toString();
	}
	
	@RequestMapping(value="/toAdd")
	public String toAdd(){

		return "/pageprovince/pageprovinceAdd";
	}
	
	@ResponseBody
	@RequestMapping(value="/save")
	public String save(TPageProvince pageprovince){
		JsonResult jsonResult = new JsonResult();
		try {
			pageProvinceService.save(pageprovince);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@RequestMapping(value="/toForm")
	public String toForm(){
		return "/pageprovince/pageprovinceEdit";
	}
	
	@ResponseBody
	@RequestMapping(value="/toEdit")
	public String toEdit(String id){
		TPageProvince pageprovince = (TPageProvince) pageProvinceService.findById(id);
		return JSONObject.fromObject(pageprovince).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/update")
	public String update(TPageProvince pageprovince){
		JsonResult jsonResult = new JsonResult();
		try {
			pageProvinceService.update(pageprovince);
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
			pageProvinceService.delete(id);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	
	/**
	 * 获取省份城市列表
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="citys")
	public String pageprovinceWithCity(ProvinceParam param){
		JsonResult result = new JsonResult();
		try {
			List<TPageProvince> list = (List<TPageProvince>) pageProvinceService.findAll(TPageProvince.class, param);
			
			for (TPageProvince tProvince : list) {
				List<TPageCity> citys = pageCityService.findByProvince(tProvince.getId());
				tProvince.setCitys(citys);
			}
			
			result.setObj(list);
			result.setCode(Constant.SUCCESS);
			result.setResult(true);
		} catch (Exception e) {
			result.setCode(Constant.FAILURE);
			result.setResult(false);
			e.printStackTrace();
		}
		
		return JSONObject.fromObject(result).toString();
	}
	
	/**
	 * 某一个省份的城市列表
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="provinceChild")
	public String provinceChild(ProvinceParam param){
		JsonResult result = new JsonResult();
		try {
			TPageProvince province = (TPageProvince) pageProvinceService.findById(param.getId());
			
			if (province != null) {
				List<TPageCity> citys = pageCityService.findByProvince(province.getId());
				province.setCitys(citys);
			}
			
			result.setObj(province);
			result.setCode(Constant.SUCCESS);
			result.setResult(true);
		} catch (Exception e) {
			result.setCode(Constant.FAILURE);
			result.setResult(false);
			result.setMsg(e.getMessage());
			e.printStackTrace();
		}
		
		return JSONObject.fromObject(result).toString();
	}
}
