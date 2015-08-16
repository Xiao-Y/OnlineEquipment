package com.xiaoy.permission.service;

import java.util.Set;

/**
 * 角色服务
 * 
 * @author XiaoY
 * @date: 2015年8月16日 上午10:42:22
 */
public interface RoleService
{

	/**
	 * 通过用户的id查询出现角色信息
	 * 
	 * @param id
	 * @return
	 */
	Set<String> findRoleName(String id);

	/**
	 * 通过用户id获得权限集合
	 * 
	 * @param id
	 * @return
	 */
	Set<String> getPermissionNames(String id);

}
