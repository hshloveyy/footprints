package com.mvc.footprints.controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.footprints.constant.Constant;
import com.mvc.footprints.entity.TKind;
import com.mvc.footprints.entity.TSubKind;
import com.mvc.footprints.param.KindParam;
import com.mvc.footprints.resultmap.JsonResult;
import com.mvc.footprints.resultmap.PagerResult;
import com.mvc.footprints.service.IKindService;
import com.mvc.footprints.service.ISubKindService;

@Controller
@RequestMapping("kind")
public class KindController {

	@Autowired
	private IKindService kindService;
	
	@Autowired
	private ISubKindService subKindService;
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/list")
	public String index(KindParam param){
		PagerResult result = new PagerResult();
		List<TKind> list = (List<TKind>) kindService.findAll(TKind.class, param);
		result.setRows(list);
		
		int count = kindService.findAllCount(TKind.class, param);
		result.setTotal(count);
		return JSONObject.fromObject(result).toString();
	}
	
	@RequestMapping(value="/toAdd")
	public String toAdd(){

		return "/kind/kindAdd";
	}
	
	@ResponseBody
	@RequestMapping(value="/save")
	public String save(TKind kind){
		JsonResult jsonResult = new JsonResult();
		try {
			kindService.save(kind);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@RequestMapping(value="/toForm")
	public String toForm(){
		return "/kind/kindEdit";
	}
	
	@ResponseBody
	@RequestMapping(value="/toEdit")
	public String toEdit(String id){
		TKind kind = (TKind) kindService.findById(id);
		return JSONObject.fromObject(kind).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/update")
	public String update(TKind kind){
		JsonResult jsonResult = new JsonResult();
		try {
			kindService.update(kind);
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
			kindService.delete(id);
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
	@RequestMapping(value="subkinds")
	public String subclass(KindParam param){
		JsonResult result = new JsonResult();
		try {
			List<TKind> list = (List<TKind>) kindService.findAll(TKind.class, param);
			
			for (TKind tKind : list) {
				List<TSubKind> subKinds = subKindService.findByKindId(tKind.getId());
				tKind.setSubKinds(subKinds);
			}
			result.setObj(list);
			result.setCode(Constant.SUCCESS);
		} catch (Exception e) {
			result.setCode(Constant.FAILURE);
			e.printStackTrace();
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/all")
	public String list(KindParam param){
		JsonResult jsonResult = new JsonResult();
		try {
			List<TKind> list = kindService.findAll();
			
			for (TKind tKind : list) {
				List<TSubKind> subKinds = subKindService.findByKindId(tKind.getId());
				tKind.setSubKinds(subKinds);
			}
			
			jsonResult.setObj(list);
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
	@RequestMapping(value="kindChild")
	public String kindChild(KindParam param){
		JsonResult result = new JsonResult();
		try {
			TKind tKind = (TKind) kindService.findById(param.getId());
			
			if (tKind != null) {
				List<TSubKind> subKinds = subKindService.findByKindId(tKind.getId());
				tKind.setSubKinds(subKinds);
			}
			result.setObj(tKind);
			result.setCode(Constant.SUCCESS);
			result.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg(e.getMessage());
			result.setCode(Constant.FAILURE);
		}
		
		return JSONObject.fromObject(result).toString();
	}
}
