package com.xiaoy.user.dao;

import java.util.List;

import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.User;

public interface UserDao extends CommonDao<User>
{

	/**
	 * 通过名字查询出现用户的id
	 * 
	 * @param name
	 *            名字
	 * @return
	 */
	List<Object> getUserIdByName(String name);

}
