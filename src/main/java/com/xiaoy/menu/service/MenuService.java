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

}
