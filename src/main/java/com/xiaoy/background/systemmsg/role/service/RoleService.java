package com.xiaoy.background.systemmsg.role.service;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.xiaoy.base.entities.Menu;
import com.xiaoy.base.entities.Role;

/**
 * 角色服务
 * 
 * @author XiaoY
 * @date: 2015年8月16日 上午10:42:22
 */
public interface RoleService {

	/**
	 * 通过用户的id查询出现角色Code信息
	 * 
	 * @param id
	 * @return
	 */
	Set<String> findRoleCode(String id);

	/**
	 * 通过用户id获取角色id集合
	 * 
	 * @param id
	 *            用户id
	 * @return
	 * 
	 * @date 2015年8月20日下午6:02:13
	 */
	List<String> getRoleIdByUserId(String id);

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
	List<Role> getRoleList(HttpServletRequest request, Role role, String start, String limit);

	/**
	 * 构建菜单树
	 * 
	 * @return
	 * 
	 * @date 2015年8月27日下午6:05:16
	 */
	List<Object> buildTree();

	/**
	 * 获取父菜单的集合
	 * 
	 * @return
	 */
	List<Menu> getParentMenuList();

	/**
	 * 获取子菜单的集合
	 * 
	 * @return
	 */
	List<Menu> getChildMenuList();

	/**
	 * 获取角色信息
	 * 
	 * @return
	 */
	List<Role> getRoleList();
}
