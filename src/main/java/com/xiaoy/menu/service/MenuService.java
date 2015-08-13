package com.xiaoy.menu.service;

import java.util.List;

import com.xiaoy.base.entities.Menu;
import com.xiaoy.base.service.CommonService;

public interface MenuService extends CommonService<Menu> {

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
	 * 根据父id获取其下的子菜单集合
	 * @param parentId 父id
	 * @return
	 */
	List<Menu> getChildMenuListByParentId(String parentId);

	/**
	 * 根据条件查询菜单
	 * 
	 * @param menu
	 *            查询条件
	 * @return List
	 */
	List<Menu> findCollectionByCondition(Menu menu, String start, String limit);

	/**
	 * 根据条件统计菜单记录数
	 * 
	 * @param menu
	 *            查询条件
	 * @return long
	 */
	long countByCollection(Menu menu);

	/**
	 * 更新菜单
	 * 
	 * @param menu
	 *
	 * @date 2015年8月11日上午11:15:14
	 */
	void updateMenu(Menu menu);

}
