package com.mvc.footprints.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.footprints.entity.TSubKind;

@Controller
public class TestController {

	@RequestMapping("/index")
	public String index(){
		return "/manage";
	}
	
	public static void main(String[] args) {
		TSubKind subkind = new TSubKind();
		if(1 == subkind.getStatus()){
			System.out.println("1");
		}else{
			System.out.println("null");
		}
	}
}
