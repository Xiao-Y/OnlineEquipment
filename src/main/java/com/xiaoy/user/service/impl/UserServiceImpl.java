package com.xiaoy.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.User;
import com.xiaoy.base.service.impl.CommonServiceImpl;
import com.xiaoy.user.dao.UserDao;
import com.xiaoy.user.service.UserService;

@Service
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
		User user = userDao.findByName(loginName);
		return user;
	}

	@Override
	public List<Object> getUserIdByName(String name)
	{
		List<Object> objs = userDao.getUserIdByName(name);
		// List<String> list = new ArrayList<>();
		// for (Object o : objs)
		// {
		// list.add((String) o);
		// }
		return objs;
	}

}
