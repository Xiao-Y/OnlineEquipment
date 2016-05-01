package com.xiaoy.foreground.systemmsg.menu.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiaoy.background.systemmsg.menu.service.MenuService;
import com.xiaoy.base.entities.MenuDto;

@Controller(value = "com.xiaoy.foreground.systemmsg.menu.controller.MenuController")
@RequestMapping("/foreground/systemmsg/menu")
public class MenuController {

	@Resource
	private MenuService menuService;

	@RequestMapping("/index")
	public String getMenuList(Model model) {
		MenuDto menu = new MenuDto();
		String start = menu.getStart();
		String limit = menu.getLimit();
		List<MenuDto> menuList = menuService.findCollectionByCondition(menu, start, limit);
		for (MenuDto m : menuList) {
			if (!"-1".equals(m.getParentId())) {
				MenuDto mu = menuService.findObjectById(m.getParentId());
				if (mu != null) {
					m.setParentName(mu.getMenuName());
				}
			}
			if ("0".equals(m.getMenuType())) {
				m.setMenuTypeName("树枝节点");
			} else {
				m.setMenuTypeName("叶子节点");
			}
		}
		model.addAttribute("menus", menuList);
		return "foreground/systemmsg/menu/menuList";
	}
}
