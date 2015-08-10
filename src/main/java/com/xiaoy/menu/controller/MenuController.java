package com.xiaoy.menu.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoy.base.entities.Menu;
import com.xiaoy.menu.service.MenuService;
import com.xiaoy.util.JsonResult;

/**
 * 菜单操作
 * 
 * @author XiaoY
 * @date: 2015年8月10日 下午9:29:11
 */
@Controller
@RequestMapping("/menu")
public class MenuController
{
	@Resource
	private MenuService menuService;
	
	@RequestMapping("/index")
	public String index()
	{
		return "menu/index";
	}

	/**
	 * 菜单列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/menuList")
	public JsonResult menuList(@ModelAttribute("menu") Menu menu)
	{
		List<Menu> menuList = menuService.findCollectionByCondition(menu);
		long total = menuService.countByCollection(menu);
		JsonResult json = new JsonResult();
		json.setSuccess(true);
		json.setRoot(menuList);
		json.setTotal(total);
		return json;
	}
}




















