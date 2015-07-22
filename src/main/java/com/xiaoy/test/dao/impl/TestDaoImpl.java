package com.xiaoy.test.dao.impl;

import org.springframework.stereotype.Repository;

import com.xiaoy.base.dao.impl.CommonImpl;
import com.xiaoy.base.entities.Test;
import com.xiaoy.test.dao.TestDao;

@Repository
public class TestDaoImpl extends CommonImpl<Test> implements TestDao
{
	
}
