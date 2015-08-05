package com.mvc.footprints.controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.footprints.entity.PreUcenterMembers;
import com.mvc.footprints.entity.TMessage;
import com.mvc.footprints.param.CityParam;
import com.mvc.footprints.resultmap.JsonResult;
import com.mvc.footprints.resultmap.PagerResult;
import com.mvc.footprints.service.IMessageService;
import com.mvc.footprints.service.IUserService;

@Controller
@RequestMapping("message")
public class MessageController {
	
	@Autowired
	private IMessageService messageService;
	
	@Autowired
	private IUserService userService;

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/list")
	public String index(CityParam param){
		PagerResult result = new PagerResult();
		List<TMessage> list = (List<TMessage>) messageService.findAll(TMessage.class, param, null);
		result.setRows(list);
		
		for (TMessage tMessage : list) {
			PreUcenterMembers preUcenterMembers = userService.findUserById(tMessage.getUserId());
			if(preUcenterMembers != null){
				tMessage.setUserName(preUcenterMembers.getUsername());
			}
		}
		
		int count = messageService.findAllCount(TMessage.class, param);
		result.setTotal(count);
		return JSONObject.fromObject(result).toString();
	}
	
	@RequestMapping(value="/toAdd")
	public String toAdd(){

		return "/message/messageAdd";
	}
	
	@ResponseBody
	@RequestMapping(value="/save")
	public String save(TMessage message){
		JsonResult jsonResult = new JsonResult();
		try {
			messageService.save(message);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
	@RequestMapping(value="/toForm")
	public String toForm(){
		return "/message/messageEdit";
	}
	
	@ResponseBody
	@RequestMapping(value="/toEdit")
	public String toEdit(String id){
		TMessage message = (TMessage) messageService.findById(id);
		return JSONObject.fromObject(message).toString();
	}
	
	@ResponseBody
	@RequestMapping(value="/update")
	public String update(TMessage message){
		JsonResult jsonResult = new JsonResult();
		try {
			messageService.update(message);
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
			messageService.delete(id);
			jsonResult.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setResult(false);
		}
		return JSONObject.fromObject(jsonResult).toString();
	}
	
}
