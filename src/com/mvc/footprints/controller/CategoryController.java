package com.mvc.footprints.controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.footprints.constant.Constant;
import com.mvc.footprints.entity.TCategory;
import com.mvc.footprints.entity.TSubCategory;
import com.mvc.footprints.param.CategoryParam;
import com.mvc.footprints.resultmap.JsonResult;
import com.mvc.footprints.resultmap.PagerResult;
import com.mvc.footprints.service.ICategoryService;
import com.mvc.footprints.service.ISubCategoryService;

@Controller
@RequestMapping("category")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private ISubCategoryService subCategoryService;
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/list")
	public String index(CategoryParam param){
		PagerResult result = new PagerResult();
		List<TCategory> list = (List<TCategory>) categoryService.findAll(TCategory.class, param);
		result.setRows(list);
		
		int count = categoryService.findAllCount(TCategory.class, param);
		result.setTotal(count);
		return JSONObject.fromObject(result).toString();
	}
	
	@RequestMapping(value="/toAdd")
	public String toAdd(){

		return "/category/categoryAdd";
	}
	
	@ResponseBody
	@RequestMapping(value="/save")
	public String save(TCategory category){
		JsonResult jsonResult = new JsonResult();
		try {
			categoryService.save(category);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@RequestMapping(value="/toForm")
	public String toForm(){
		return "/category/categoryEdit";
	}
	
	@ResponseBody
	@RequestMapping(value="/toEdit")
	public String toEdit(String id){
		TCategory category = (TCategory) categoryService.findById(id);
		return JSONObject.fromObject(category).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/update")
	public String update(TCategory category){
		JsonResult jsonResult = new JsonResult();
		try {
			categoryService.update(category);
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
			categoryService.delete(id);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	/**
	 * 获取大类分类列表
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="subcategorys")
	public String subclass(CategoryParam param){
		JsonResult result = new JsonResult();
		try {
			List<TCategory> list = (List<TCategory>) categoryService.findAll(TCategory.class, param);
			
			for (TCategory tCategory : list) {
				List<TSubCategory> subCategorys = subCategoryService.findByCategoryId(tCategory.getId());
				tCategory.setSubCategorys(subCategorys);
			}
			result.setObj(list);
			result.setCode(Constant.SUCCESS);
			result.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(Constant.FAILURE);
		}
		
		return JSONObject.fromObject(result).toString();
	}
	
	/**
	 * 获取大类分类列表
	 * @param param
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="categoryChild")
	public String categoryChild(CategoryParam param){
		JsonResult result = new JsonResult();
		try {
			TCategory tCategory = (TCategory) categoryService.findById(param.getId());
			
			if (tCategory != null) {
				List<TSubCategory> subCategorys = subCategoryService.findByCategoryId(tCategory.getId());
				tCategory.setSubCategorys(subCategorys);
			}
			result.setObj(tCategory);
			result.setCode(Constant.SUCCESS);
			result.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(Constant.FAILURE);
		}
		
		return JSONObject.fromObject(result).toString();
	}
}
