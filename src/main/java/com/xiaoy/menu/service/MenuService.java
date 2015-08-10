package com.xiaoy.menu.service;

import java.util.List;

import com.xiaoy.base.entities.Menu;
import com.xiaoy.base.service.CommonService;

public interface MenuService extends CommonService<Menu>
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
	 * 根据条件查询菜单
	 * 
	 * @param menu
	 *            查询条件
	 * @return List
	 */
	List<Menu> findCollectionByCondition(Menu menu);

	/**
	 * 根据条件统计菜单记录数
	 * 
	 * @param menu
	 *            查询条件
	 * @return long
	 */
	long countByCollection(Menu menu);

}
