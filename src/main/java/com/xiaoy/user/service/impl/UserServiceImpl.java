package com.xiaoy.user.service.impl;

import javax.annotation.Resource;

import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.User;
import com.xiaoy.base.service.impl.CommonServiceImpl;
import com.xiaoy.user.dao.UserDao;
import com.xiaoy.user.service.UserService;

public class UserServiceImpl extends CommonServiceImpl<User> implements UserService
{
	private UserDao userDao;

	@Resource
	@Override
	public void setCommonDao(CommonDao<User> commonDao)
	{
		this.userDao = (UserDao) commonDao;
		super.commonDao = commonDao;
	}

	@Override
	public User findByName(String loginName)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
