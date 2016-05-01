package com.xiaoy.background.usermsg.user.dao;

import java.util.List;

import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.UserDto;

public interface UserDao extends CommonDao<UserDto>
{

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
	UserDto findByName(String loginName);

	/**
	 * 根据条件查询用户信息
	 * 
	 * @param user
	 * @return
	 */
	List<UserDto> findUsersByCondition(UserDto user, String start, String limit);

	/**
	 * 根据条件查询用户记录总数
	 * 
	 * @param user
	 * @return
	 */
	long countByCollection(UserDto user);

}
