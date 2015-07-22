package com.xiaoy.test.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoy.base.entities.Test;
import com.xiaoy.test.dao.TestDao;
import com.xiaoy.test.service.TestService;

@Service
public class TestServiceImpl implements TestService
{

	@Resource
	private TestDao testDao;
	
	public List<Test> getAllNewFile()
	{
		List<Test> list = testDao.findCollectionByCondition(null, null);
		return list;
	}

}
