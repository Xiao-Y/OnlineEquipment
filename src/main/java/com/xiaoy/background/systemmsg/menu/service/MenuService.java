package com.xiaoy.background.systemmsg.menu.service;

import java.util.List;

import com.xiaoy.base.entities.MenuDto;
import com.xiaoy.base.service.CommonService;

public interface MenuService extends CommonService<MenuDto> {

	/**
	 * 获取父菜单的集合
	 * 
	 * @return
	 */
	List<MenuDto> getParentMenuList();

	/**
	 * 获取子菜单的集合
	 * 
	 * @return
	 */
	List<MenuDto> getChildMenuList();

	/**
	 * 根据父id获取其下的子菜单集合
	 * 
	 * @param parentId
	 *            父id
	 * @return
	 */
	List<MenuDto> getChildMenuListByParentId(String parentId);

	/**
	 * 根据条件查询菜单
	 * 
	 * @param menu
	 *            查询条件
	 * @return List
	 */
	List<MenuDto> findCollectionByCondition(MenuDto menu, String start, String limit);

	/**
	 * 根据条件统计菜单记录数
	 * 
	 * @param menu
	 *            查询条件
	 * @return long
	 */
	long countByCollection(MenuDto menu);

	/**
	 * 更新菜单
	 * 
	 * @param menu
	 * 
	 * @date 2015年8月11日上午11:15:14
	 */
	void updateMenu(MenuDto menu);

	/**
	 * 保存菜单和权限
	 * 
	 * @param menu
	 * @author XiaoY
	 * @date: 2016年5月1日 上午9:58:03
	 */
	void saveMenu(MenuDto menu);

	/**
	 * 删除菜单和权限
	 * 
	 * @param id
	 * @author XiaoY
	 * @date: 2016年5月1日 上午10:00:38
	 */
	void deleteMenuByid(String id);

}
