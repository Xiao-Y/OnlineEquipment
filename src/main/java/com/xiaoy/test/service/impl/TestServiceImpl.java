package com.xiaoy.test.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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
		return testDao.findCollectionByCondition("", null);
	}

	public void saveTest(Test t)
	{
		testDao.saveObject(t);
	}

	public void updateTest(Test t)
	{
		testDao.updateObject(t);
	}

	public void deleteTest(Test t)
	{
		testDao.deleteObjectByid(t.getId());
	}
}
