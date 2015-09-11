package com.xiaoy.background.systemmsg.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xiaoy.background.systemmsg.menu.dao.MenuDao;
import com.xiaoy.background.systemmsg.menu.service.MenuService;
import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.Menu;
import com.xiaoy.base.service.impl.CommonServiceImpl;

@Service
public class MenuServiceImpl extends CommonServiceImpl<Menu> implements MenuService
{

	private MenuDao menuDao;

	@Resource
	@Override
	public void setCommonDao(CommonDao<Menu> commonDao)
	{
		this.menuDao = (MenuDao) commonDao;
		super.commonDao = commonDao;
	}

	@Override
	public List<Menu> getParentMenuList()
	{
		return menuDao.getParentMenuList();
	}

	@Override
	public List<Menu> getChildMenuList()
	{
		return menuDao.getChildMenuList();
	}

	@Override
	public List<Menu> findCollectionByCondition(Menu menu, String start, String limit)
	{
		List<Menu> list = menuDao.findCollectionByCondition(menu, start, limit);
		return list;
	}

	@Override
	public long countByCollection(Menu menu)
	{
		return menuDao.countByCollection(menu);
	}

	@Override
	public void updateMenu(Menu menu)
	{
		Menu obj = menuDao.findObjectById(menu.getId());
		obj.setMenuName(menu.getMenuName());
		obj.setMenuType(menu.getMenuType());
		obj.setMenuUrl(menu.getMenuUrl());
		obj.setParentId(menu.getParentId());
		obj.setRemark(menu.getRemark());
		obj.setSeq(menu.getSeq());
		obj.setUpdateTime(menu.getUpdateTime());
	}

	@Override
	public List<Menu> getChildMenuListByParentId(String parentId)
	{
		if (!StringUtils.isEmpty(parentId))
		{
			List<Menu> menus = menuDao.getChildMenuListByParentId(parentId);
			return menus;
		}
		return null;
	}
}
