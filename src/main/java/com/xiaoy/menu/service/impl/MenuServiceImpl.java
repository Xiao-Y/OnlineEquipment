package com.xiaoy.menu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.Menu;
import com.xiaoy.base.service.impl.CommonServiceImpl;
import com.xiaoy.menu.dao.MenuDao;
import com.xiaoy.menu.service.MenuService;

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
		System.out.println("this.menuDao------------------>" + super.commonDao);
	}

	@Override
	public List<Menu> getParentMenuList()
	{
		String hqlWhere = " and parentId = -1 ";
		Map<String, Object> paramsMapValue = new HashMap<>();
		List<Menu> parentMenus = menuDao.findCollectionByCondition(hqlWhere, paramsMapValue);
		return parentMenus;
	}

	@Override
	public List<Menu> getChildMenuList()
	{
		String hqlWhere = " and parentId <> -1 ";
		Map<String, Object> paramsMapValue = new HashMap<>();
		List<Menu> childMenus = menuDao.findCollectionByCondition(hqlWhere, paramsMapValue);
		return childMenus;
	}
}
