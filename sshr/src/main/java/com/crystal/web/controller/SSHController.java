package com.crystal.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crystal.base.BaseController;
import com.crystal.service.inte.SSHService;

@Controller
@RequestMapping("/sshr")
public class SSHController extends BaseController{
	
	@Autowired
	private SSHService service;
	
	@RequestMapping("index")
	public void test(){
		service.test("save");
	}
}
