package com.mvc.footprints.controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.footprints.constant.Constant;
import com.mvc.footprints.entity.TCity;
import com.mvc.footprints.entity.TProvince;
import com.mvc.footprints.param.ProvinceParam;
import com.mvc.footprints.resultmap.JsonResult;
import com.mvc.footprints.resultmap.PagerResult;
import com.mvc.footprints.service.ICityService;
import com.mvc.footprints.service.IProvinceService;

@Controller
@RequestMapping("province")
public class ProvinceController {
	
	@Autowired
	private IProvinceService provinceService;
	
	@Autowired
	private ICityService cityService;

	/**
	 * 省份列表
	 * @return
	 */
	@RequestMapping(value="/index")
	public String provinceIndex(ProvinceParam param){

		return "/province/province_manage";
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="list")
	public String list(ProvinceParam param){
		PagerResult result = new PagerResult();
		List<TProvince> list = (List<TProvince>) provinceService.findAll(TProvince.class, param);
		result.setRows(list);
		
		int count = provinceService.findAllCount(TProvince.class, param);
		result.setTotal(count);
		return JSONObject.fromObject(result).toString();
	}
	
	@RequestMapping(value="/toAdd")
	public String toAdd(){

		return "/province/provinceAdd";
	}
	
	@ResponseBody
	@RequestMapping(value="/save")
	public String save(TProvince province){
		JsonResult jsonResult = new JsonResult();
		try {
			provinceService.save(province);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@RequestMapping(value="/toForm")
	public String toForm(){
		return "/province/provinceEdit";
	}
	
	@ResponseBody
	@RequestMapping(value="/toEdit")
	public String toEdit(String id){
		TProvince province = (TProvince) provinceService.findById(id);
		return JSONObject.fromObject(province).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/update")
	public String update(TProvince province){
		JsonResult jsonResult = new JsonResult();
		try {
			provinceService.update(province);
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
			provinceService.delete(id);
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
	public String provinceWithCity(ProvinceParam param){
		JsonResult result = new JsonResult();
		try {
			List<TProvince> list = (List<TProvince>) provinceService.findAll(TProvince.class, param);
			
			for (TProvince tProvince : list) {
				List<TCity> citys = cityService.findByProvince(tProvince.getId());
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
			TProvince province = (TProvince) provinceService.findById(param.getId());
			
			if (province != null) {
				List<TCity> citys = cityService.findByProvince(province.getId());
				province.setCitys(citys);
			}
			
			result.setObj(province);
			result.setCode(Constant.SUCCESS);
			result.setResult(true);
		} catch (Exception e) {
			result.setCode(Constant.FAILURE);
			result.setResult(false);
			e.printStackTrace();
		}
		
		return JSONObject.fromObject(result).toString();
	}
}
