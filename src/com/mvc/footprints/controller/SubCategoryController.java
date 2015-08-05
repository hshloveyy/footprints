package com.mvc.footprints.controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.footprints.entity.TCategory;
import com.mvc.footprints.entity.TSubCategory;
import com.mvc.footprints.param.CategoryParam;
import com.mvc.footprints.resultmap.JsonResult;
import com.mvc.footprints.resultmap.PagerResult;
import com.mvc.footprints.service.ISubCategoryService;

@Controller
@RequestMapping("subcategory")
public class SubCategoryController {

	@Autowired
	private ISubCategoryService subCategoryService;
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/list")
	public String index(CategoryParam param){
		PagerResult result = new PagerResult();
		List<TCategory> list = (List<TCategory>) subCategoryService.findAll(TSubCategory.class, param);
		result.setRows(list);
		
		int count = subCategoryService.findAllCount(TSubCategory.class, param);
		result.setTotal(count);
		return JSONObject.fromObject(result).toString();
	}
	
	@RequestMapping(value="/toAdd")
	public String toAdd(){

		return "/subcategory/subCategoryAdd";
	}
	
	@ResponseBody
	@RequestMapping(value="/save")
	public String save(TSubCategory subCategory){
		JsonResult jsonResult = new JsonResult();
		try {
			subCategoryService.save(subCategory);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@RequestMapping(value="/toForm")
	public String toForm(){
		return "/subcategory/subCategoryEdit";
	}
	
	@ResponseBody
	@RequestMapping(value="/toEdit")
	public String toEdit(String id){
		TSubCategory subCategory = (TSubCategory) subCategoryService.findById(id);
		return JSONObject.fromObject(subCategory).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/update")
	public String update(TSubCategory subCategory){
		JsonResult jsonResult = new JsonResult();
		try {
			subCategoryService.update(subCategory);
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
			subCategoryService.delete(id);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
}
