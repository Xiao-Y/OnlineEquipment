package com.xiaoy.background.usermsg.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xiaoy.background.usermsg.user.dao.UserDao;
import com.xiaoy.background.usermsg.user.service.UserService;
import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.User;
import com.xiaoy.base.service.impl.CommonServiceImpl;

@Service
public class UserServiceImpl extends CommonServiceImpl<User> implements UserService {
	private UserDao userDao;

	@Resource
	@Override
	public void setCommonDao(CommonDao<User> commonDao) {
		this.userDao = (UserDao) commonDao;
		super.commonDao = commonDao;
	}

	@Override
	public User findByName(String loginName) {
		User user = userDao.findByName(loginName);
		return user;
	}

	@Override
	public List<Object> getUserIdByName(String name) {
		List<Object> objs = userDao.getUserIdByName(name);
		// List<String> list = new ArrayList<>();
		// for (Object o : objs)
		// {
		// list.add((String) o);
		// }
		return objs;
	}

	@Override
	public List<User> findCollectionByCondition(User user, String start, String limit) {
		return userDao.findUsersByCondition(user, start, limit);
	}

	@Override
	public long countByCollection(User user) {
		return userDao.countByCollection(user);
	}

	@Override
	public void deleteUserById(String id) {
		userDao.deleteObjectByid(id);
	}

	@Override
	public void updateUser(User user) {
		if (user != null && !StringUtils.isEmpty(user.getId())) {
			User userObj = userDao.findObjectById(user.getId());
			userObj.setUsername(user.getUsername());
			userObj.setPassword(user.getPassword());
			userObj.setAddress(user.getAddress());
			userObj.setBirthday(user.getBirthday());
			userObj.setImageUrl(user.getImageUrl());
			userObj.setUpdateTime(user.getUpdateTime());
			userObj.setRoles(user.getRoles());
		}
	}

}
