package com.xiaoy.menu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoy.base.entities.Menu;
import com.xiaoy.menu.service.MenuService;

@Controller
@RequestMapping("/home")
public class MenuController
{

	@Resource
	private MenuService menuService;

	/**
	 * 进入主页面
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String home()
	{
		return "home/index";
	}

	/**
	 * 构建菜单树
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/buildTree")
	public List<Object> buildTree()
	{
		// 获取父级菜单
		List<Menu> parentMenu = menuService.getParentMenuList();
		// 获取所有的子菜单
		List<Menu> childMenu = menuService.getChildMenuList();

		List<Object> json = new ArrayList<Object>();
		for (Menu parent : parentMenu)
		{
			Map<String, Object> map = new HashMap<>();
			map.put("id", parent.getId());
			map.put("expandabl", false);// 菜单折叠状态
			map.put("text", parent.getMenuName());// 菜单名称
			map.put("index", parent.getSeq());
			map.put("leaf", false);
			List<Object> list = new ArrayList<Object>();
			for (Menu child : childMenu)
			{
				if (parent.getId().equals(child.getParentId()))
				{
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
