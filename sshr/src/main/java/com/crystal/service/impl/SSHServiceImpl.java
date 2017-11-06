package com.crystal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crystal.dao.inte.BaseDaoI;
import com.crystal.entity.SSHR;
import com.crystal.service.inte.SSHService;
import com.crystal.tools.date.DATE;

@Service
@Transactional
public class SSHServiceImpl implements SSHService{
	
    @Autowired
    private BaseDaoI dao;

	@Override
	public void test(String action) {
		SSHR test = new SSHR();
		test.setCode("6379");
		test.setPhone("15063710827");
		test.setTime(DATE.getCurrentDateStr());
		dao.save(test);
	}

	

}
