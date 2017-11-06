package com.crystal.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crystal.base.BaseController;
import com.crystal.service.inte.RedisService;

@Controller
@RequestMapping("/redis")
public class RedisController extends BaseController{
	
	@Autowired
	private RedisService service;
	
	/*
	 * save
	 * search
	 * update
	 * delete
	 */
	@RequestMapping("index")
	public void test(Map<String,Object> map,String action){
		service.todo(action);
	}
}
