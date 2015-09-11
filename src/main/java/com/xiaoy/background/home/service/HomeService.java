package com.xiaoy.background.home.service;

import java.util.List;

import com.xiaoy.base.entities.Menu;

public interface HomeService
{

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
	 * 构建菜单树
	 * 
	 * @return
	 */
	public List<Object> buildTree();
}
