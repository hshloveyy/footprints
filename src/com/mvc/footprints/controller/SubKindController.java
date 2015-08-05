package com.mvc.footprints.controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.footprints.entity.TKind;
import com.mvc.footprints.entity.TSubKind;
import com.mvc.footprints.param.KindParam;
import com.mvc.footprints.resultmap.JsonResult;
import com.mvc.footprints.resultmap.PagerResult;
import com.mvc.footprints.service.ISubKindService;

@Controller
@RequestMapping("subkind")
public class SubKindController {

	@Autowired
	private ISubKindService subKindService;
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/list")
	public String index(KindParam param){
		PagerResult result = new PagerResult();
		List<TKind> list = (List<TKind>) subKindService.findAll(TSubKind.class, param);
		result.setRows(list);
		
		int count = subKindService.findAllCount(TSubKind.class, param);
		result.setTotal(count);
		return JSONObject.fromObject(result).toString();
	}
	
	@RequestMapping(value="/toAdd")
	public String toAdd(){

		return "/subkind/subKindAdd";
	}
	
	@ResponseBody
	@RequestMapping(value="/save")
	public String save(TSubKind subKind){
		JsonResult jsonResult = new JsonResult();
		try {
			subKindService.save(subKind);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@RequestMapping(value="/toForm")
	public String toForm(){
		return "/subkind/subKindEdit";
	}
	
	@ResponseBody
	@RequestMapping(value="/toEdit")
	public String toEdit(String id){
		TSubKind subKind = (TSubKind) subKindService.findById(id);
		return JSONObject.fromObject(subKind).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/update")
	public String update(TSubKind subKind){
		JsonResult jsonResult = new JsonResult();
		try {
			subKindService.update(subKind);
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
			subKindService.delete(id);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
}
