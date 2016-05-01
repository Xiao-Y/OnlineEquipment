package com.xiaoy.background.systemmsg.menu.dao;

import java.util.List;

import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.MenuDto;

/**
 * 
 * @author XiaoY
 * @date: 2015年8月18日 下午8:10:27
 */
public interface MenuDao extends CommonDao<MenuDto>
{

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
	 * 根据条件统计菜单记录数
	 * 
	 * @param menu
	 *            查询条件
	 * @return long
	 */
	long countByCollection(MenuDto menu);

	/**
	 * 根据条件查询菜单
	 * 
	 * @param menu
	 *            查询条件
	 * @return List
	 */
	List<MenuDto> findCollectionByCondition(MenuDto menu, String start, String limit);

	/**
	 * 根据父id获取其下的子菜单集合
	 * 
	 * @param parentId
	 *            父id
	 * @return
	 */
	List<MenuDto> getChildMenuListByParentId(String parentId);
}
