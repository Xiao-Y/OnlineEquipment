package com.xiaoy.background.home.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaoy.background.home.service.HomeService;
import com.xiaoy.background.systemmsg.menu.service.MenuService;
import com.xiaoy.base.entities.MenuDto;

@Service(value = "com.xiaoy.background.home.service.impl.HomeServiceImpl")
public class HomeServiceImpl implements HomeService {

	@Resource
	private MenuService menuService;

	/**
	 * 获取父菜单的集合
	 * 
	 * @return
	 * @date 2015年10月2日 上午11:42:15
	 */
	private List<MenuDto> getParentMenuList() {
		List<MenuDto> parentMenus = menuService.getParentMenuList();
		return parentMenus;
	}

	/**
	 * 获取子菜单的集合
	 * 
	 * @return
	 * @date 2015年10月2日 上午11:42:26
	 */
	private List<MenuDto> getChildMenuList() {
		List<MenuDto> childMenus = menuService.getChildMenuList();
		return childMenus;
	}

	@Override
	public List<Object> buildTree() {
		// 获取父级菜单
		List<MenuDto> parentMenu = this.getParentMenuList();
		// 获取所有的子菜单
		List<MenuDto> childMenu = this.getChildMenuList();

		List<Object> json = new ArrayList<Object>();
		for (MenuDto parent : parentMenu) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", parent.getId());
			map.put("expanded", true);// 菜单折叠状态
			map.put("text", parent.getMenuName());// 菜单名称
			map.put("index", parent.getSeq());
			map.put("leaf", false);
			List<Object> list = new ArrayList<Object>();
			for (MenuDto child : childMenu) {
				if (parent.getId().equals(child.getParentId())) {
					Map<String, Object> childMap = new HashMap<>();
					childMap.put("id", child.getId());
					childMap.put("text", child.getMenuName());// 菜单名称
					childMap.put("index", child.getSeq());
					childMap.put("parentId", child.getParentId());
					childMap.put("url", "../" + child.getMenuUrl());
					childMap.put("leaf", true);
					list.add(childMap);
				}
			}
			map.put("children", list);
			json.add(map);
		}
		return json;
	}
}
