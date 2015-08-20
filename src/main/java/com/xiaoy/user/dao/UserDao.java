package com.xiaoy.user.dao;

import java.util.List;

import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.User;

public interface UserDao extends CommonDao<User> {

	/**
	 * 通过名字查询出现用户的id
	 * 
	 * @param name
	 *            名字
	 * @return
	 */
	List<Object> getUserIdByName(String name);

	/**
	 * 通过登陆名查询出用户信息
	 * 
	 * @param loginName登陆名
	 * @return
	 *
	 * @date 2015年8月20日下午5:27:49
	 */
	User findByName(String loginName);

}
