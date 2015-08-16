package com.xiaoy.user.service;

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

}
