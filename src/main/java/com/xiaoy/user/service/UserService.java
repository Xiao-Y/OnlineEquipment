package com.xiaoy.user.service;

import java.util.List;

import com.xiaoy.base.entities.User;
import com.xiaoy.base.service.CommonService;

/**
 * 用户service
 * 
 * @author XiaoY
 * @date: 2015年8月16日 上午10:21:55
 */
public interface UserService extends CommonService<User>
{

	/**
	 * 通过用户名查找用户信息
	 * 
	 * @param loginName
	 * @return
	 */
	User findByName(String loginName);

	/**
	 * 通过名字查询出现用户的id
	 * 
	 * @param name
	 *            名字
	 * @return
	 */
	List<Object> getUserIdByName(String name);
}
