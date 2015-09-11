package com.xiaoy.background.systemmsg.role.dao;

import java.util.List;

import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.Role;

public interface RoleDao extends CommonDao<Role> {

	/**
	 * 通过角色 id集合，获取角色信息
	 * 
	 * @param roleIds
	 *            角色 id集合
	 * @return
	 *
	 * @date 2015年8月20日下午6:06:01
	 */
	List<Role> getRoleListByRoleIds(List<String> roleIds);

	/**
	 * 根据条件查询角色信息
	 * 
	 * @param role
	 *            查询条件
	 * @return
	 *
	 * @date 2015年8月26日下午12:20:01
	 */
	List<Role> getRoleList(Role role, String start, String limit);

}