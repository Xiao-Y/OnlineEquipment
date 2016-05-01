package com.xiaoy.background.systemmsg.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.xiaoy.background.systemmsg.menu.dao.MenuDao;
import com.xiaoy.background.systemmsg.menu.service.MenuService;
import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.MenuDto;
import com.xiaoy.base.entities.PermissionDto;
import com.xiaoy.base.service.impl.CommonServiceImpl;
import com.xiaoy.permission.dao.PermissionDao;

@Service
public class MenuServiceImpl extends CommonServiceImpl<MenuDto> implements MenuService {

	private MenuDao menuDao;

	@Resource
	@Override
	public void setCommonDao(CommonDao<MenuDto> commonDao) {
		this.menuDao = (MenuDao) commonDao;
		super.commonDao = commonDao;
	}

	@Resource
	private PermissionDao permissionDao;

	@Override
	public List<MenuDto> getParentMenuList() {
		return menuDao.getParentMenuList();
	}

	@Override
	public List<MenuDto> getChildMenuList() {
		return menuDao.getChildMenuList();
	}

	@Override
	public List<MenuDto> findCollectionByCondition(MenuDto menu, String start, String limit) {
		List<MenuDto> list = menuDao.findCollectionByCondition(menu, start, limit);
		return list;
	}

	@Override
	public long countByCollection(MenuDto menu) {
		return menuDao.countByCollection(menu);
	}

	@Override
	public void updateMenu(MenuDto menu) {
		MenuDto obj = menuDao.findObjectById(menu.getId());
		obj.setMenuName(menu.getMenuName());
		obj.setMenuType(menu.getMenuType());
		obj.setMenuUrl(menu.getMenuUrl());
		obj.setParentId(menu.getParentId());
		obj.setRemark(menu.getRemark());
		obj.setSeq(menu.getSeq());
		obj.setUpdateTime(menu.getUpdateTime());
		// 更新权限
		PermissionDto permission = new PermissionDto();
		permission.setCreateTime(obj.getCreateTime());
		permission.setUpdateTime(obj.getUpdateTime());
		permission.setMenuCode(obj.getMenuCode());
		permission.setPermissionName(obj.getMenuName());
		permission.setUrl(obj.getMenuUrl());
		permission.setId(obj.getId());
		permissionDao.saveOrUpdate(permission);
	}

	@Override
	public List<MenuDto> getChildMenuListByParentId(String parentId) {
		if (!StringUtils.isEmpty(parentId)) {
			List<MenuDto> menus = menuDao.getChildMenuListByParentId(parentId);
			return menus;
		}
		return null;
	}

	@Override
	public void saveMenu(MenuDto menu) {
		MenuDto temp = new MenuDto();
		BeanUtils.copyProperties(menu, temp);
		menuDao.saveObject(menu);
		// 保存权限
		PermissionDto permission = new PermissionDto();
		permission.setCreateTime(menu.getCreateTime());
		permission.setUpdateTime(menu.getUpdateTime());
		permission.setMenuCode(temp.getMenuCode());
		permission.setPermissionName(temp.getMenuName());
		permission.setUrl(temp.getMenuUrl());
		permission.setId(temp.getId());
		permissionDao.saveObject(permission);
	}

	@Override
	public void deleteMenuByid(String id) {
		menuDao.deleteObjectByid(id);
		permissionDao.deleteObjectByid(id);
	}
}
