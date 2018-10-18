package com.crystal.task;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.crystal.service.inte.SSHService;

/**
 * @描述 任务示例<br>
 * @author 赵赫
 * @版本 v1.0.0
 * @日期 2017-8-28
 */
@Component
public class TestTask {

	@Autowired
	SSHService service;
	
//	@Scheduled(fixedDelay=1000)
///	@Scheduled(cron="0 0 0 * * ?")
	public void start(){
		service.test("test");
	}
}
