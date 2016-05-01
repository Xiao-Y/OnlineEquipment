package com.xiaoy.background.systemmsg.menu.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.xiaoy.background.systemmsg.menu.dao.MenuDao;
import com.xiaoy.base.dao.impl.CommonDaoImpl;
import com.xiaoy.base.entities.MenuDto;
import com.xiaoy.util.DateHelper;

@Repository
public class MenuDaoImpl extends CommonDaoImpl<MenuDto> implements MenuDao
{
	public List<MenuDto> getParentMenuList()
	{
		String hqlWhere = " and parentId = -1 ";
		List<MenuDto> parentMenus = this.findCollectionByCondition(hqlWhere, null);
		return parentMenus;
	}

	@Override
	public List<MenuDto> getChildMenuList()
	{
		String hqlWhere = " and parentId <> -1 ";
		List<MenuDto> childMenus = this.findCollectionByCondition(hqlWhere, null);
		return childMenus;
	}

	@Override
	public long countByCollection(MenuDto menu)
	{
		StringBuffer hqlWhere = new StringBuffer("");
		Map<String, Object> paramsMapValue = this.appHql(hqlWhere, menu);
		return this.countByCollection(hqlWhere.toString(), paramsMapValue);
	}

	@Override
	public List<MenuDto> findCollectionByCondition(MenuDto menu, String start, String limit)
	{
		StringBuffer hqlWhere = new StringBuffer("");
		Map<String, Object> paramsMapValue = this.appHql(hqlWhere, menu);
		List<MenuDto> list = this.findCollectionByCondition(hqlWhere.toString(), paramsMapValue, start, limit);
		return list;
	}

	@Override
	public List<MenuDto> getChildMenuListByParentId(String parentId)
	{
		String hqlWhere = " and parentId = :parentId ";
		Map<String, Object> paramsMapValue = new HashMap<String, Object>();
		paramsMapValue.put("parentId", parentId);
		List<MenuDto> menus = this.findCollectionByCondition(hqlWhere, paramsMapValue);
		return menus;
	}

	/**
	 * 拼接查询语句，设置查询参数
	 * 
	 * @param where
	 *            查询语句
	 * @param menu
	 *            查询参数
	 * @return Map
	 */
	private Map<String, Object> appHql(StringBuffer where, MenuDto menu)
	{
		Map<String, Object> paramsMapValue = null;
		if (menu != null)
		{
			paramsMapValue = new HashMap<>();
			// 父级id
			if (!StringUtils.isEmpty(menu.getParentId()) && !menu.getParentId().equals("0"))
			{
				where.append(" and parentId = :parentId ");
				paramsMapValue.put("parentId", menu.getParentId());
			}

			// 菜单名
			if (!StringUtils.isEmpty(menu.getMenuName()))
			{
				where.append(" and menuName like :menuName ");
				paramsMapValue.put("menuName", "%" + menu.getMenuName() + "%");
			}

			// 菜单类型
			if (!StringUtils.isEmpty(menu.getMenuType()) && !menu.getMenuType().equals("-1"))
			{
				where.append(" and menuType = :menuType ");
				paramsMapValue.put("menuType", menu.getMenuType());
			}

			// 创建时间
			if (menu.getCreateTime() != null)
			{
				String strDate = DateHelper.dateConverString(menu.getCreateTime());
				where.append(" and createTime > timestamp(:createTiem1, '00 00:00:00') ");
				paramsMapValue.put("createTiem1", strDate);

				where.append(" and createTime < timestamp(:createTiem2, '01 00:00:00') ");
				paramsMapValue.put("createTiem2", strDate);
			}

			// 更新时间
			if (menu.getUpdateTime() != null)
			{
				String strDate = DateHelper.dateConverString(menu.getUpdateTime());
				where.append(" and updateTime > timestamp(:updateTime1, '00 00:00:00') ");
				paramsMapValue.put("updateTime1", strDate);

				where.append(" and updateTime < timestamp(:updateTime2, '01 00:00:00') ");
				paramsMapValue.put("updateTime2", strDate);
			}

			where.append(" order by updateTime desc");
		}
		return paramsMapValue;
	}
}
