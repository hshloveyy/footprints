package com.mvc.footprints.controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.footprints.constant.Constant;
import com.mvc.footprints.entity.TReport;
import com.mvc.footprints.param.CityParam;
import com.mvc.footprints.resultmap.JsonResult;
import com.mvc.footprints.resultmap.PagerResult;
import com.mvc.footprints.service.IReportService;

@Controller
@RequestMapping("report")
public class ReportController {
	
	@Autowired
	private IReportService reportService;

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/list")
	public String index(CityParam param){
		PagerResult result = new PagerResult();
		List<TReport> list = (List<TReport>) reportService.findAll(TReport.class, param);
		result.setRows(list);
		
		int count = reportService.findAllCount(TReport.class, param);
		result.setTotal(count);
		return JSONObject.fromObject(result).toString();
	}
	
	@RequestMapping(value="/toAdd")
	public String toAdd(){

		return "/report/reportAdd";
	}
	
	@ResponseBody
	@RequestMapping(value="/save")
	public String save(TReport report){
		JsonResult jsonResult = new JsonResult();
		try {
			reportService.save(report);
			jsonResult.setCode(Constant.SUCCESS);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(Constant.FAILURE);
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@RequestMapping(value="/toForm")
	public String toForm(){
		return "/report/reportEdit";
	}
	
	@ResponseBody
	@RequestMapping(value="/toEdit")
	public String toEdit(String id){
		TReport report = (TReport) reportService.findById(id);
		return JSONObject.fromObject(report).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/update")
	public String update(TReport report){
		JsonResult jsonResult = new JsonResult();
		try {
			reportService.update(report);
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
			reportService.delete(id);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
}
